package balancer.logic.command;

import java.io.IOException;

import balancer.storage.Storage;

public abstract class Command {
    public abstract CommandResult execute(Storage storage) throws IOException;
}
