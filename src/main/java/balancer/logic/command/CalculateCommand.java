package balancer.logic.command;

import java.util.ArrayList;
import java.util.HashMap;

import balancer.storage.Storage;
import balancer.storage.Transaction;
import balancer.storage.TransactionComparator;

/**
 * Represents a {@code CalculateCommand} to generate the minimal list of transactions needed to balance expenses.
 * Uses a greedy algorithm where every step, at least one giver or receiver will give or receive what they owe or are
 * owed.
 */
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

    /**
     * Preprocesses the {@code HashMap} that is received from {@code storage}. Calculates the average (baseline) that
     * everybody should end up spending and transforms the {@code HashMap} into an {@code ArrayList<Transaction>}
     * of how much participants owe or are owed. It also removes everyone not involved in the final list of transactions
     * (they already contributed exactly the average).
     *
     * @param hashmap Taken straight from the storage. Maps names to {@code Transaction}.
     * @return        An {@code ArrayList<Transaction>} of the processed {@code HashMap}.
     */
    private ArrayList<Transaction> preprocess(HashMap<String, Transaction> hashmap) {
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

    /**
     * The main algorithm that takes in an {@code ArrayList<Transaction>} and generates the minimum number of
     * transactions needed to balance the payments.
     *
     * @param processed The preprocessed {@code ArrayList<Transaction>}.
     * @return          The String representation of the list of transactions that need to occur in the form of
     *                  {@code person1 has to pay person2 $X.XX}.
     */
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
            sb.append(String.format("%s has to pay %s $%.2f\n",
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
