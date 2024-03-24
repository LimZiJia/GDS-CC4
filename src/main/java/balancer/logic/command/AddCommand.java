package balancer.logic.command;

import balancer.storage.Storage;

public class AddCommand extends Command {
    private static final String ADD_COMMAND_SUCCESS = "'s transaction has been successfully added!";
    public static final String COMMAND_WORD = "add";
    private final String name;
    private final int amount;

    public AddCommand(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public CommandResult execute(Storage storage) {
        storage.addTransaction(name, amount);
        return new CommandResult(name + ADD_COMMAND_SUCCESS);
    }
}
