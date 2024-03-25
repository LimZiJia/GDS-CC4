package balancer.logic.parser;

import balancer.logic.command.AddCommand;
import balancer.logic.parser.exceptions.ParserException;

/**
 * Parses commands starting with "add" and generates a {@code AddCommand}.
 */
public class AddParser implements Parser<AddCommand> {
    @Override
    public AddCommand parse(String userInput) throws ParserException {
        // Input will be of the form 'Name amount' and should be non-null
        if (userInput == null) {
            throw new ParserException(BalancerParser.PARSE_ERROR_MESSAGE);
        }
        String[] arguments = userInput.split(" ");
        String name = arguments[0];
        int amount = Integer.parseInt(arguments[1]);
        return new AddCommand(name, amount);
    }
}
