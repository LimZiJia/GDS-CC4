package balancer.logic.command;

import balancer.storage.Storage;
import balancer.storage.Transaction;
import balancer.storage.TransactionComparator;

import java.util.ArrayList;
import java.util.HashMap;

public class CalculateCommand extends Command {
    public static final String COMMAND_WORD = "calculate";

    @Override
    public CommandResult execute(Storage storage) {
        HashMap<String, Transaction> transactions = storage.getTransactions();

        // Remove all people that contributed exactly the average amount and transforms the amounts to their difference
        // from the average. +ve means then are owed money and -ve means that they owe money.
        ArrayList<Transaction> processed = preprocess(transactions);

        // Run the greedy algorithm to get the minimum number of transactions
        String result = greedy(processed);

        return new CommandResult(result);
    }

    private ArrayList<Transaction> preprocess (HashMap<String, Transaction> hashmap) {
        ArrayList<Transaction> current = new ArrayList<>(hashmap.values());
        float total = 0;
        for (Transaction t: current) {
            total += t.getAmount();
        }
        float average = total / current.size();

        // Changing transactions to be difference from average
        current.replaceAll(x -> x.update(-average));

        // Removing all people who have to do nothing (people who are not owed or owe any money)
        current.forEach(x -> {
            int index = current.indexOf(x);
            if (x.isEmpty()) {
                current.remove(index);
            }
        });

        return current;
    }

    private String greedy(ArrayList<Transaction> processed) {
        StringBuilder sb = new StringBuilder();
        TransactionComparator comparator = new TransactionComparator();
        while (!processed.isEmpty()) {
            // Sort the list
            processed.sort(comparator);

            // Find the largest amount for giver and receiver
            Transaction biggestGiver = processed.get(0);
            Transaction biggestReceiver = processed.get(processed.size() - 1);

            // Make the largest possible transaction from biggestGiver to biggestReceiver
            float amountToTransfer = Math.min(-biggestGiver.getAmount(), biggestReceiver.getAmount());
            sb.append(String.format("%s has to pay %s $%f\n",
                    biggestGiver.getPerson(), biggestReceiver.getPerson(), amountToTransfer));

            // Update transactions and transaction list
            biggestGiver.update(amountToTransfer);
            biggestReceiver.update(-amountToTransfer);
            if (biggestGiver.isEmpty()) {
                processed.remove(0);
            } else {
                processed.set(0, biggestGiver);
            }
            if (biggestReceiver.isEmpty()) {
                processed.remove(processed.size() - 1);
            } else {
                processed.set(processed.size() - 1, biggestReceiver);
            }
        }

        return sb.toString();
    }
}
