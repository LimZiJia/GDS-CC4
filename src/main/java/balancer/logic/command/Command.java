package balancer.logic.command;

import java.io.IOException;

import balancer.storage.Storage;

/**
 * Represents a command to be executed by the application.
 */
public abstract class Command {
    /**
     * Executes a command and returns a result message or exit encapsulated in {@code CommandResult}.
     *
     * @param storage      {@code Storage} which the command should operate on.
     * @return             Feedback to the user or exit encapsulated by {@code CommandResult}
     * @throws IOException Some {@code Command} need to save to file and might throw this exception.
     */
    public abstract CommandResult execute(Storage storage) throws IOException;
}
