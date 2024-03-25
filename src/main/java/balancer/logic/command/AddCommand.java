package balancer.logic.command;

import java.io.IOException;

import balancer.storage.Storage;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final String ADD_COMMAND_SUCCESS = "'s transaction has been successfully added!";
    private final String name;
    private final int amount;

    public AddCommand(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Storage storage) throws IOException {
        storage.addTransaction(name, amount);
        storage.save();
        return new CommandResult(String.format("%s%s %s has now contributed $%.2f",
                name, ADD_COMMAND_SUCCESS, name, storage.getAmount(name)));
    }
}
