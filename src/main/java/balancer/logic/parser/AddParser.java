package balancer.logic.parser;

import balancer.logic.command.AddCommand;

public class AddParser implements Parser<AddCommand> {

    @Override
    public AddCommand parse(String userInput) {
        // Input will be of the form 'Name amount'
        String[] arguments = userInput.split(" ");
        String name = arguments[0];
        int amount = Integer.parseInt(arguments[1]);
        return new AddCommand(name, amount);
    }
}
