package balancer.logic.parser;

import balancer.logic.command.Command;
import balancer.logic.parser.Exceptions.ParserException;

/**
 * Interface for command parsers.
 *
 * @param <T> The type of command returned after parsing the {@code String}.
 */
public interface Parser<T extends Command> {
    /**
     * Parses {@code userInput} into a command and returns it.
     */
    T parse(String userInput) throws ParserException;
}
