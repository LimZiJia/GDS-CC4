package balancer.logic.command;

import balancer.storage.Storage;
import balancer.storage.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Storage storage) {
        HashMap<String, Transaction> transactions = storage.getTransactions();
        ArrayList<Transaction> transactionList = new ArrayList<>(transactions.values());
        StringBuilder sb = new StringBuilder();
        for (Transaction t: transactionList) {
            sb.append(t.toString());
            sb.append("\n");
        }
        return new CommandResult(sb.toString());
    }
}
