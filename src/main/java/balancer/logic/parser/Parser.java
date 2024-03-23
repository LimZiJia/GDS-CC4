package balancer.logic.parser;

import balancer.logic.command.Command;
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     */
    T parse(String userInput);
}
