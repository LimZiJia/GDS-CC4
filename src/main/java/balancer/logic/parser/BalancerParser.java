package balancer.logic.parser;

import balancer.logic.command.AddCommand;
import balancer.logic.command.Command;
import balancer.logic.parser.Exceptions.ParserException;

public class BalancerParser {
    private final String PARSE_ERROR_MESSAGE = "The command you entered is not recognised or of the wrong format.";

    public Command parseCommand(String userInput) throws ParserException {
        String[] split = userInput.split(" ", 1);
        String commandWord = split[0];
        String arguments = split[1];
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddParser().parse(arguments);
        default:
            throw new ParserException(PARSE_ERROR_MESSAGE);
        }
    }
}
