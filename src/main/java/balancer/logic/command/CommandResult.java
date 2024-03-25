package balancer.logic.command;

/**
 * Encapsulates the result of executing a command.
 */
public class CommandResult {
    private final String feedbackToUser;

    /** The application should exit. */
    private final boolean exit;

    /**
     * Constructs the result of {@code Command} execution.
     *
     * @param feedbackToUser A String to be shown to the user.
     * @param exit           If true, application exits.
     */
    public CommandResult(String feedbackToUser, boolean exit) {
        this.feedbackToUser = feedbackToUser;
        this.exit = exit;
    }

    /**
     * This constructor is used for results that are only feedback to user (not {@code ExitCommand}).
     *
     * @param feedbackToUser A String to be shown to the user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.exit = false;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isExit() {
        return exit;
    }
}
