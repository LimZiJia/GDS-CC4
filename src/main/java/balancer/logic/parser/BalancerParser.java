package balancer.logic.parser;

import balancer.logic.command.AddCommand;
import balancer.logic.command.CalculateCommand;
import balancer.logic.command.Command;
import balancer.logic.command.DeleteCommand;
import balancer.logic.command.ExitCommand;
import balancer.logic.command.ListCommand;
import balancer.logic.parser.exceptions.ParserException;

/**
 * A class that parses inputs of the user and gets the relevant {@code Command}.
 */
public class BalancerParser {
    public static final String PARSE_ERROR_MESSAGE =
            "The command you entered is not recognised or of the wrong format.";

    /**
     * Parses user inputs and produces corresponding {@code Command} or exceptions.
     *
     * @param userInput        The user's input.
     * @return                 A {@code Command}.
     * @throws ParserException For when a command is of the wrong format or unrecognised.
     */
    public Command parseCommand(String userInput) throws ParserException {
        String[] split = userInput.split(" ", 2);
        String commandWord = split[0];
        String arguments = null;
        if (split.length == 2) {
            arguments = split[1];
        }
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case CalculateCommand.COMMAND_WORD:
            return new CalculateCommand();
        case DeleteCommand.COMMAND_WORD:
            return new DeleteParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ParserException(PARSE_ERROR_MESSAGE);
        }
    }
}
