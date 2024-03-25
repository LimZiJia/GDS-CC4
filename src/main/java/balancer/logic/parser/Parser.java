package balancer.logic.parser;

import balancer.logic.command.Command;
import balancer.logic.parser.exceptions.ParserException;

public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     */
    T parse(String userInput) throws ParserException;
}
