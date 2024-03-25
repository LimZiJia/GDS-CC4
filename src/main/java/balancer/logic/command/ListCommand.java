package balancer.logic.command;

import java.util.ArrayList;
import java.util.HashMap;

import balancer.storage.Storage;
import balancer.storage.Transaction;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String EMPTY_STRING_REPLY = "List is empty!";

    @Override
    public CommandResult execute(Storage storage) {
        HashMap<String, Transaction> transactions = storage.getTransactions();
        ArrayList<Transaction> transactionList = new ArrayList<>(transactions.values());
        StringBuilder sb = new StringBuilder();
        for (Transaction t: transactionList) {
            sb.append(t.toString());
            sb.append("\n");
        }

        String list = sb.isEmpty() ? EMPTY_STRING_REPLY : sb.toString();
        return new CommandResult(list);
    }
}
