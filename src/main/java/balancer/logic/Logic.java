package balancer.logic;

import balancer.logic.command.Command;
import balancer.logic.command.CommandResult;
import balancer.logic.parser.BalancerParser;
import balancer.logic.parser.Exceptions.ParserException;
import balancer.storage.Storage;

/**
 * The runs the logic of the application. Takes in the user input and gets the {@code CommandResult}.
 */
public class Logic {
    BalancerParser balancerParser;
    Storage storage;

    /**
     * The constructor for {@code Logic}.
     *
     * @param balancerParser Used to parse user inputs.
     * @param storage        Where all commands execute on.
     */
    public Logic(BalancerParser balancerParser, Storage storage) {
        this.balancerParser = balancerParser;
        this.storage = storage;
    }

    /**
     * Executes user input command and returns the result.
     *
     * @param commandString The command entered by user.
     * @return              The result of the command execution.
     */
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
