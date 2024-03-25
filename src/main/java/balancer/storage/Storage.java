package balancer.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private HashMap<String, Transaction> transactions = new HashMap<>();
    private static Path pathDir;
    private static Path pathFile;

    public Storage(String dir, String name) {
        String userDir = System.getProperty("user.dir");
        pathDir = Paths.get(userDir + dir);
        pathFile = Paths.get(userDir + dir + name);
    }

    public void addTransaction(String name, int amount) {
        Transaction current = transactions.get(name);
        if (current == null) {
            current = new Transaction(name, amount);
        } else {
            current.update(amount);
        }
        transactions.put(name, current);
    }

    public HashMap<String, Transaction> getTransactions() {
        return this.transactions;
    }

    public float getAmount(String name) {
        return transactions.get(name).getAmount();
    }

    public void deleteTransaction(String name) {
        transactions.remove(name);
    }

    public void deleteAll() {
        transactions.clear();
    }

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
        List<String> saveString = transactionList.stream().map(Transaction::toString).toList();
        Files.write(pathFile, saveString);
    }

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
