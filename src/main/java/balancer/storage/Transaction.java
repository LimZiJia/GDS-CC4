package balancer.storage;

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
        float epsilon = 0.00001f;
        return Math.abs(amount) < epsilon;
    }

    public static Transaction savedStringToTransaction(String savedString) {
        String[] split = savedString.split(" ");
        if (split.length != 2) {
            throw new UnsupportedOperationException();
        } else {
            String newName = split[0];
            float newAmount = Float.parseFloat(split[1]);
            return new Transaction(newName, newAmount);
        }
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", person, amount);
    }

}
