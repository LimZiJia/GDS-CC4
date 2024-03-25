package balancer.logic.parser;

import balancer.logic.command.DeleteCommand;
import balancer.logic.parser.Exceptions.ParserException;

/**
 * Parses commands starting with "delete" and generates a {@code DeleteCommand}.
 */
public class DeleteParser implements Parser<DeleteCommand> {
    public static final String WORD_FOR_DELETE_ALL = "all!";
    @Override
    public DeleteCommand parse(String userInput) throws ParserException {
        if (userInput == null) {
            throw new ParserException(BalancerParser.PARSE_ERROR_MESSAGE);
        }

        // Can either be a NAME or 'all!'
        if (userInput.equals(WORD_FOR_DELETE_ALL)) {
            return new DeleteCommand(true);
        } else {
            return new DeleteCommand(userInput);
        }
    }
}
