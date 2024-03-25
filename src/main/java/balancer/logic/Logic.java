package balancer.logic;

import balancer.logic.command.Command;
import balancer.logic.command.CommandResult;
import balancer.logic.parser.BalancerParser;
import balancer.storage.Storage;

public class Logic {
    private BalancerParser balancerParser;
    private Storage storage;

    public Logic(BalancerParser balancerParser, Storage storage) {
        this.balancerParser = balancerParser;
        this.storage = storage;
    }

    public CommandResult execute(String commandString) {
        CommandResult commandResult;
        try {
            Command command = balancerParser.parseCommand(commandString);
            commandResult = command.execute(storage);
        } catch (Exception e) {
            commandResult = new CommandResult(e.getMessage());
        }
        return commandResult;
    }

}
