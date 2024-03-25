package balancer;

import balancer.logic.Logic;
import balancer.logic.command.CommandResult;
import balancer.logic.parser.BalancerParser;
import balancer.storage.Storage;

import java.util.Scanner;

/**
 * The main entry point of the application.
 */
public class Main {
    private static final String SAVE_DIRECTORY_NAME = "/data/";
    private static final String SAVE_FILE_NAME = "transactions.txt";
    private static final String INITIAL_LOAD_SUCCESS = "Successfully loaded transaction list from " + SAVE_FILE_NAME;
    private static BalancerParser balancerParser = new BalancerParser();
    private static Storage storage = new Storage(SAVE_DIRECTORY_NAME, SAVE_FILE_NAME);
    private static Logic logic = new Logic(balancerParser, storage);

    /**
     * Loads transaction list from one that is saved and prints success message if successful.
     */
    private static void startUp() {
        try {
            storage.load();
            System.out.println(INITIAL_LOAD_SUCCESS);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The main loop of the program that takes user inputs and executes them.
     */
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

    /**
     * The abstracted flow of the application. It does a start-up sequence and runs.
     */
    public static void main(String[] args) {
        startUp();
        run();
    }
}
