package balancer.storage;

import java.util.Comparator;

public class Transaction {
    private String person;
    private float amount;

    public Transaction(String person, float amount) {
        this.person = person;
        this.amount = amount;
    }

    public Transaction update(float amount) {
        this.amount += amount;
        return this;
    }

    public String getPerson() {
        return person;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isEmpty() {
        return amount == 0;
    }

    @Override
    public String toString() {
        return person + " " + amount;
    }

}
