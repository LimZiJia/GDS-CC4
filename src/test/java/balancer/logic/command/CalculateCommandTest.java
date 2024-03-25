package balancer.logic.command;

import balancer.storage.Transaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculateCommandTest {
    CalculateCommand cc = new CalculateCommand();

    @Test
    public void one_person_pay_two_persons() {
        HashMap<String, Transaction> transactions = new HashMap<>();
        transactions.put("Alice", new Transaction("Alice", 40));
        transactions.put("Bob", new Transaction("Bob", 40));
        transactions.put("Charlie", new Transaction("Charlie", 10));
        List<Transaction> transactionList = cc.preprocess(transactions);

        String expected = "Charlie has to pay Alice $10.00\n" +
                "Charlie has to pay Bob $10.00\n" +
                "\n" +
                "Number of transactions: 2";
        String actual = cc.greedy(transactionList);
        assertEquals(expected, actual);
    }

    @Test
    public void one_person_pay_two_persons_2dp() {
        HashMap<String, Transaction> transactions = new HashMap<>();
        transactions.put("Alice", new Transaction("Alice", 40.105));
        transactions.put("Bob", new Transaction("Bob", 40.105));
        transactions.put("Charlie", new Transaction("Charlie", 10));
        List<Transaction> transactionList = cc.preprocess(transactions);

        String expected = "Charlie has to pay Alice $10.04\n" +
                "Charlie has to pay Bob $10.03\n" +
                "\n" +
                "Number of transactions: 2";
        String actual = cc.greedy(transactionList);
        assertEquals(expected, actual);
    }

    @Test
    public void four_persons_1() {
        HashMap<String, Transaction> transactions = new HashMap<>();
        transactions.put("Alice", new Transaction("Alice", 10));
        transactions.put("Bob", new Transaction("Bob", 20));
        transactions.put("Charlie", new Transaction("Charlie", 0));
        transactions.put("Don", new Transaction("Don", 10));
        List<Transaction> transactionList = cc.preprocess(transactions);

        String expected = "Charlie has to pay Bob $10.00\n" +
                "\n" +
                "Number of transactions: 1";
        String actual = cc.greedy(transactionList);
        assertEquals(expected, actual);
    }

    @Test
    public void four_persons_2() {
        HashMap<String, Transaction> transactions = new HashMap<>();
        transactions.put("Alice", new Transaction("Alice", 40));
        transactions.put("Bob", new Transaction("Bob", 40));
        transactions.put("Charlie", new Transaction("Charlie", 10));
        transactions.put("Don", new Transaction("Don", 10));
        List<Transaction> transactionList = cc.preprocess(transactions);

        String expected = "Don has to pay Alice $15.00\n" +
                "Charlie has to pay Bob $15.00\n" +
                "\n" +
                "Number of transactions: 2";
        String actual = cc.greedy(transactionList);
        assertEquals(expected, actual);
    }

    @Test
    public void four_persons_with_one_significantly_more() {
        HashMap<String, Transaction> transactions = new HashMap<>();
        transactions.put("Alice", new Transaction("Alice", 200));
        transactions.put("Bob", new Transaction("Bob", 80));
        transactions.put("Charlie", new Transaction("Charlie", 50));
        transactions.put("Don", new Transaction("Don", 20));
        List<Transaction> transactionList = cc.preprocess(transactions);

        String expected = "Don has to pay Alice $67.50\n" +
                "Charlie has to pay Alice $37.50\n" +
                "Bob has to pay Alice $7.50\n" +
                "\n" +
                "Number of transactions: 3";
        String actual = cc.greedy(transactionList);
        assertEquals(expected, actual);
    }

    @Test
    public void four_persons_with_two_significantly_more() {
        HashMap<String, Transaction> transactions = new HashMap<>();
        transactions.put("Alice", new Transaction("Alice", 160));
        transactions.put("Bob", new Transaction("Bob", 120));
        transactions.put("Charlie", new Transaction("Charlie", 50));
        transactions.put("Don", new Transaction("Don", 20));
        List<Transaction> transactionList = cc.preprocess(transactions);

        String expected = "Don has to pay Alice $67.50\n" +
                "Charlie has to pay Bob $32.50\n" +
                "Charlie has to pay Alice $5.00\n" +
                "\n" +
                "Number of transactions: 3";
        String actual = cc.greedy(transactionList);
        assertEquals(expected, actual);
    }

    

}