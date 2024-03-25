package balancer.logic.command;

import balancer.storage.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract CommandResult execute(Storage storage) throws IOException;
}
