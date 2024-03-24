package balancer.storage;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return Float.compare(t1.getAmount(), t2.getAmount());
    }
}
