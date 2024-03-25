package balancer.logic.command;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    public static final String NO_TRANSACTIONS_REPLY = "All good! No transactions required!";

    @Override
    public CommandResult execute(Storage storage) {
        HashMap<String, Transaction> transactions = storage.getTransactions();

        // Remove all people that contributed exactly the average amount and transforms the amounts to their difference
        // from the average. +ve means then are owed money and -ve means that they owe money.
        List<Transaction> processed = preprocess(transactions);

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
    public List<Transaction> preprocess(HashMap<String, Transaction> hashmap) {
        List<Transaction> current = new ArrayList<>(hashmap.values());
        double total = 0;
        for (Transaction t: current) {
            total += t.getAmount();
        }
        double average = total / current.size();

        // Changing transactions to be difference from average
        current.replaceAll(x -> x.update(-average));

        // Removing all people who have to do nothing (people who are not owed or owe any money)
        current = current.stream().filter(t -> !t.isEmpty()).collect(Collectors.toList());

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
    public String greedy(List<Transaction> processed) {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder();
        TransactionComparator comparator = new TransactionComparator();
        int numberOfTransactions = 0;

        while (!processed.isEmpty()) {
            // Sort the list
            processed.sort(comparator);

            // Find the largest amount for giver and receiver
            Transaction biggestGiver = processed.get(0);
            Transaction biggestReceiver = processed.get(processed.size() - 1);

            // Make the largest possible transaction from biggestGiver to biggestReceiver
            double amountToTransfer = Math.min(-biggestGiver.getAmount(), biggestReceiver.getAmount());
            sb.append(String.format("%s has to pay %s $%s\n",
                    biggestGiver.getPerson(), biggestReceiver.getPerson(), df.format(amountToTransfer)));
            numberOfTransactions++;

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

        return sb.length() == 0
                ? NO_TRANSACTIONS_REPLY
                : sb.append(String.format("\nNumber of transactions: %d", numberOfTransactions)).toString();
    }
}
