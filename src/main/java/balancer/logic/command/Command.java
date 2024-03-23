package balancer.logic.command;

import balancer.storage.Storage;

public abstract class Command {
    public abstract CommandResult execute(Storage storage);
}
