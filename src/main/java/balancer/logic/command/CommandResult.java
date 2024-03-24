package balancer.logic.command;

public class CommandResult {
    private final String feedbackToUser;

    /** The application should exit. */
    private final boolean exit;

    public CommandResult(String feedbackToUser, boolean exit) {
        this.feedbackToUser = feedbackToUser;
        this.exit = exit;
    }

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
