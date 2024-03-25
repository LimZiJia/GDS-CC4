package balancer.logic.command;

import java.io.IOException;

import balancer.storage.Storage;

/**
 * Represents a {@code DeleteCommand} that deletes entries of a person or of every person.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String DELETE_COMMAND_SUCCESS = "Delete was successful!";
    private String name;
    private boolean isAll;

    /**
     * Constructor for deleting a single person from the transaction list.
     *
     * @param name Name of person to delete
     */
    public DeleteCommand(String name) {
        this.name = name;
        this.isAll = false;
    }

    /**
     * Constructor for deleting every person from the transaction list.
     *
     * @param isAll If true, delete every person.
     */
    public DeleteCommand(boolean isAll) {
        this.name = null;
        this.isAll = isAll;
    }


    @Override
    public CommandResult execute(Storage storage) throws IOException {
        if (isAll) {
            storage.deleteAll();
        } else {
            storage.deleteTransaction(name);
        }
        storage.save();
        return new CommandResult(DELETE_COMMAND_SUCCESS);
    }
}
