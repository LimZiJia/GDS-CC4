package balancer.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the in-memory version of the transaction list.
 */
public class Storage {
    /** The path to the directory of the save file */
    private static Path pathDir;
    /** The path to the save file */
    private static Path pathFile;
    private HashMap<String, Transaction> transactions = new HashMap<>();

    /**
     * Constructor for the {@code Storage} that specifies the save locations.
     */
    public Storage(String dir, String name) {
        String userDir = System.getProperty("user.dir");
        pathDir = Paths.get(userDir + dir);
        pathFile = Paths.get(userDir + dir + name);
    }

    /**
     * Adds or updates transaction to the {@code HashMap}.
     *
     * @param name   Name of the person to add or update.
     * @param amount The initial or update transaction amount.
     */
    public void addTransaction(String name, double amount) {
        Transaction current = transactions.get(name);
        if (current == null) {
            current = new Transaction(name, amount);
        } else {
            current.update(amount);
        }
        transactions.put(name, current);
    }

    public HashMap<String, Transaction> getTransactions() {
        // Deep copy so that this.transactions cannot be altered
        HashMap<String, Transaction> copy = new HashMap<>();
        for (Map.Entry<String, Transaction> entry : this.transactions.entrySet()) {
            String person = entry.getValue().getPerson();
            double amount = entry.getValue().getAmount();
            copy.put(entry.getKey(), new Transaction(person, amount));
        }
        return copy;
    }

    public double getAmount(String name) {
        return transactions.get(name).getAmount();
    }

    public void deleteTransaction(String name) {
        transactions.remove(name);
    }

    public void deleteAll() {
        transactions.clear();
    }

    /**
     * Saves a copy of the {@code HashMap} to a file.
     *
     * @throws IOException
     */
    public void save() throws IOException {
        assert pathDir != null : "Your directory is missing!";
        // Create directory if it does not exist
        if (!Files.exists(pathDir)) {
            Files.createDirectories(pathDir);
        }

        assert pathFile != null : "Your file directory is missing!";
        // Delete file if exists because we want to write fresh
        if (Files.exists(pathFile)) {
            Files.delete(pathFile);
        }

        Files.createFile(pathFile);
        // Writing to the file
        List<Transaction> transactionList = new ArrayList<>(transactions.values());
        List<String> saveString = transactionList.stream().map(Transaction::toString).collect(Collectors.toList());
        Files.write(pathFile, saveString);
    }

    /**
     * Loads {@code HashMap} converted from a file. Used to initialise the application.
     *
     * @throws IOException
     */
    public void load() throws IOException {
        assert pathDir != null : "Your directory is missing!";
        // Create directory if it does not exist
        if (!Files.exists(pathDir)) {
            Files.createDirectories(pathDir);
        }

        assert pathFile != null : "Your file directory is missing!";
        // Delete file if exists because we want to write fresh
        if (!Files.exists(pathFile)) {
            Files.createFile(pathFile);
        }
        List<String> read = Files.readAllLines(pathFile);
        this.transactions = read.stream()
                .map(Transaction::savedStringToTransaction)
                .collect(Collectors.toMap(Transaction::getPerson, x -> x, (prev, next) -> next, HashMap::new));
    }
}
