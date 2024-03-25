package balancer.logic.command;

import balancer.storage.Storage;

/**
 * Represents a {@code ExitCommand} to exit the application.
 */
public class ExitCommand extends Command {
    public static final String EXIT_COMMAND_SUCCESS = "Your transactions has been saved. Good bye!";
    public static final String COMMAND_WORD = "exit";

    @Override
    public CommandResult execute(Storage storage) {
        return new CommandResult(EXIT_COMMAND_SUCCESS, true);
    }
}
