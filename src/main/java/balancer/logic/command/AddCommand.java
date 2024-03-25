package balancer.logic.command;

import java.io.IOException;

import balancer.storage.Storage;

/**
 * Represents a {@code AddCommand} to add a transaction for a specific individual.
 * Upon execution, adds the transaction to the storage and saves the changes.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final String ADD_COMMAND_SUCCESS = "'s transaction has been successfully added!";
    private final String name;
    private final int amount;

    /**
     * Constructs an {@code AddCommand} with the specified name and amount.
     *
     * @param name   the name of the individual involved in the transaction.
     * @param amount the amount of the transaction.
     */
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
