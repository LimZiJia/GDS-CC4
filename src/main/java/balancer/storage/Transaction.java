package balancer.storage;

public class Transaction {
    private String person;
    private float amount;

    public Transaction(String person, float amount) {
        this.person = person;
        this.amount = amount;
    }

    public void update(int amount) {
        this.amount += amount;
    }

    @Override
    public String toString() {
        return person + " " + amount;
    }
}
