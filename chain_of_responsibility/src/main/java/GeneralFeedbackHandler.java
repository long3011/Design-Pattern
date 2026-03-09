
public class GeneralFeedbackHandler extends FeedbackHandler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.GENERAL_FEEDBACK;
    }

    @Override
    protected void process(Message message) {
        String content   = message.getContent().toLowerCase();
        String sentiment = analyzeSentiment(content);

        System.out.println("   [GENERAL FEEDBACK] Sentiment analysis: " + sentiment);

        switch (sentiment) {
            case "POSITIVE" ->
                System.out.println("   Action : Feedback recorded as positive. "
                        + "Thank-you e-mail sent to " + message.getSenderEmail() + ".");
            case "NEGATIVE" -> {
                System.out.println("   Action : Feedback flagged for Customer Experience team review.");
                System.out.println("            Apology + follow-up e-mail dispatched to "
                        + message.getSenderEmail() + ".");
            }
            default ->
                System.out.println("   Action : Feedback archived. "
                        + "Acknowledgement e-mail sent to " + message.getSenderEmail() + ".");
        }
    }

    private String analyzeSentiment(String content) {
        boolean positive = content.contains("great") || content.contains("love")
                || content.contains("excellent") || content.contains("happy")
                || content.contains("amazing") || content.contains("thank");

        boolean negative = content.contains("bad")  || content.contains("terrible")
                || content.contains("awful") || content.contains("disappoint")
                || content.contains("angry") || content.contains("worst");

        if (positive && !negative) return "POSITIVE";
        if (negative && !positive) return "NEGATIVE";
        return "NEUTRAL";
    }
}


