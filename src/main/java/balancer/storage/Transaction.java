package balancer.storage;

import java.text.DecimalFormat;

/**
 * Represents a transaction.
 */
public class Transaction {
    private String person;
    private double amount;

    /**
     * Constructs a transaction.
     *
     * @param person Name of the person involved in the transaction.
     * @param amount Amount of the transaction.
     */
    public Transaction(String person, double amount) {
        this.person = person;
        this.amount = amount;
    }

    /**
     * Updates an existing transaction.
     *
     * @param amount Amount to update. Can be negative.
     * @return       Updated {@code Transaction}.
     */
    public Transaction update(double amount) {
        this.amount += amount;
        return this;
    }

    public String getPerson() {
        return person;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Checks if amount of this transaction is 0.
     *
     * @return Ture if amount is 0.
     */
    public boolean isEmpty() {
        double epsilon = 0.00001; // Had to choose an arbitrarily small value due to double equality being bad.
        return Math.abs(amount) < epsilon;
    }

    /**
     * Translates a saved string to a {@code Transaction}.
     *
     * @param savedString Line that is in the saved file in the form "NAME AMOUNT".
     * @return            Translated {@code Transaction}.
     */
    public static Transaction savedStringToTransaction(String savedString) {
        String[] split = savedString.split(" ");
        if (split.length != 2) {
            throw new UnsupportedOperationException();
        } else {
            String newName = split[0];
            double newAmount = Double.parseDouble(split[1]);
            return new Transaction(newName, newAmount);
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.format("%s %s", person, df.format(amount));
    }

}
