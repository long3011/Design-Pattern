import java.util.List;

/**
 * Main program for the Chain of Responsibility – Customer Feedback demo.
 *
 * <p>The chain is assembled as:
 * <pre>
 *   CompensationClaimHandler
 *       → ContactRequestHandler
 *           → DevelopmentSuggestionHandler
 *               → GeneralFeedbackHandler
 * </pre>
 *
 * <p>Each incoming {@link Message} travels down the chain until a handler
 * claims responsibility for its {@link MessageType}.
 */
public class Main {

    public static void main(String[] args) {

        // ----------------------------------------------------------------
        // 1. Build the handler chain
        // ----------------------------------------------------------------
        FeedbackHandler compensationHandler = new CompensationClaimHandler();
        FeedbackHandler contactHandler      = new ContactRequestHandler();
        FeedbackHandler suggestionHandler   = new DevelopmentSuggestionHandler();
        FeedbackHandler generalHandler      = new GeneralFeedbackHandler();

        // Link the handlers together (fluent API)
        compensationHandler
                .setNext(contactHandler)
                .setNext(suggestionHandler)
                .setNext(generalHandler);

        // ----------------------------------------------------------------
        // 2. Create a variety of customer feedback messages
        // ----------------------------------------------------------------
        List<Message> messages = List.of(

            // --- Compensation claims ---
            new Message(MessageType.COMPENSATION_CLAIM,
                    "I had a minor delay with my last order and would like a small refund.",
                    "alice@example.com"),

            new Message(MessageType.COMPENSATION_CLAIM,
                    "My package never arrived. I am considering legal action if this is not resolved.",
                    "bob@example.com"),

            new Message(MessageType.COMPENSATION_CLAIM,
                    "The product I received was defective and I want full compensation.",
                    "carol@example.com"),

            // --- Contact requests ---
            new Message(MessageType.CONTACT_REQUEST,
                    "I'm experiencing a critical bug in your mobile app causing crashes on login.",
                    "dave@example.com"),

            new Message(MessageType.CONTACT_REQUEST,
                    "I'd like to discuss upgrade options and pricing for the enterprise plan.",
                    "eve@example.com"),

            new Message(MessageType.CONTACT_REQUEST,
                    "Could someone call me to discuss my account situation?",
                    "frank@example.com"),

            // --- Development suggestions ---
            new Message(MessageType.DEVELOPMENT_SUGGESTION,
                    "Many users have requested dark mode – this is a blocker for some accessibility needs.",
                    "grace@example.com"),

            new Message(MessageType.DEVELOPMENT_SUGGESTION,
                    "It would be great to have a feature for bulk-exporting reports.",
                    "henry@example.com"),

            new Message(MessageType.DEVELOPMENT_SUGGESTION,
                    "Maybe add a fun Easter egg somewhere in the app.",
                    "iris@example.com"),

            // --- General feedback ---
            new Message(MessageType.GENERAL_FEEDBACK,
                    "I absolutely love your service – amazing support team, thank you!",
                    "jack@example.com"),

            new Message(MessageType.GENERAL_FEEDBACK,
                    "The last update was terrible and I am very disappointed with the result.",
                    "kate@example.com"),

            new Message(MessageType.GENERAL_FEEDBACK,
                    "The new interface is interesting. Some parts are better, others need more work.",
                    "leo@example.com")
        );

        // ----------------------------------------------------------------
        // 3. Send each message through the chain and print the result
        // ----------------------------------------------------------------
        System.out.println("=".repeat(70));
        System.out.println("         CUSTOMER FEEDBACK PROCESSING SYSTEM");
        System.out.println("         Chain of Responsibility Pattern Demo");
        System.out.println("=".repeat(70));
        System.out.println();

        for (int i = 0; i < messages.size(); i++) {
            System.out.printf("Message %2d: %s%n", i + 1, messages.get(i));
            compensationHandler.handle(messages.get(i));
        }

        System.out.println("=".repeat(70));
        System.out.println("All messages processed.");
    }
}



