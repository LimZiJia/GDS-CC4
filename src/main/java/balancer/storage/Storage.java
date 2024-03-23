package balancer.storage;

import java.util.HashMap;

public class Storage {
    private HashMap<String, Transaction> transactions = new HashMap<>();

    public Storage() {}

    public void addTransaction(String name, int amount) {
        Transaction current = transactions.get(name);
        if (current == null) {
            current = new Transaction(name, amount);
        } else {
            current.update(amount);
        }
    }

    public HashMap<String, Transaction> getTransactions() {
        return this.transactions;
    }
}
