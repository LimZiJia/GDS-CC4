package balancer;

import java.util.Scanner;

import balancer.logic.Logic;
import balancer.logic.command.CommandResult;
import balancer.logic.parser.BalancerParser;
import balancer.storage.Storage;

public class Main {
    private static final String SAVE_DIRECTORY_NAME = "/data/";
    private static final String SAVE_FILE_NAME = "transactions.txt";
    private static BalancerParser balancerParser = new BalancerParser();
    private static Storage storage = new Storage(SAVE_DIRECTORY_NAME, SAVE_FILE_NAME);
    private static Logic logic = new Logic(balancerParser, storage);

    private static void startUp() {
        try {
            storage.load();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            CommandResult commandResult = logic.execute(input);
            System.out.println(commandResult.getFeedbackToUser());
            if (commandResult.isExit()) {
                break;
            }
        }

    }
    public static void main(String[] args) {
        startUp();
        run();
    }
}
