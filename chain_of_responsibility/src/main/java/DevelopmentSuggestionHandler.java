public class DevelopmentSuggestionHandler extends FeedbackHandler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.DEVELOPMENT_SUGGESTION;
    }

    @Override
    protected void process(Message message) {
        String content  = message.getContent().toLowerCase();
        String priority = determinePriority(content);

        System.out.println("   [DEVELOPMENT SUGGESTION] Logged with priority: " + priority);
        System.out.println("   Action : Suggestion added to the product backlog (ticket #"
                + generateTicketNumber() + ").");
        System.out.println("   Acknowledgement e-mail sent to " + message.getSenderEmail() + ".");
    }

    private String determinePriority(String content) {
        if (content.contains("critical") || content.contains("urgent")
                || content.contains("many users") || content.contains("blocker")) {
            return "HIGH  ⬆";
        } else if (content.contains("improve") || content.contains("better")
                || content.contains("enhance") || content.contains("feature")) {
            return "MEDIUM ➡";
        } else {
            return "LOW   ⬇";
        }
    }

    private int generateTicketNumber() {
        return (int) (Math.random() * 9000) + 1000;
    }
}

