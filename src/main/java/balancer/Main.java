package balancer;

import balancer.logic.Logic;
import balancer.logic.command.CommandResult;
import balancer.logic.parser.BalancerParser;
import balancer.storage.Storage;

import java.util.Scanner;

public class Main {
    private static BalancerParser balancerParser = new BalancerParser();
    private static Storage storage = new Storage();
    private static Logic logic = new Logic(balancerParser, storage);

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            CommandResult commandResult = logic.execute(input);
            System.out.println(commandResult.getFeedbackToUser());
        }

    }
    public static void main(String[] args) {
        run();
    }
}
