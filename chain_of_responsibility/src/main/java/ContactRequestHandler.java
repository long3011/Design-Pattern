public class ContactRequestHandler extends FeedbackHandler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.CONTACT_REQUEST;
    }

    @Override
    protected void process(Message message) {
        String content = message.getContent().toLowerCase();
        String department;

        if (content.contains("bug") || content.contains("error")
                || content.contains("technical") || content.contains("crash")) {
            department = "Technical Support";
        } else if (content.contains("price") || content.contains("purchase")
                || content.contains("discount") || content.contains("upgrade")) {
            department = "Sales";
        } else {
            department = "Customer Relations";
        }

        System.out.println("   [CONTACT REQUEST] Forwarded to the " + department + " department.");
        System.out.println("   Action : An agent from " + department
                + " will contact " + message.getSenderEmail()
                + " within 1 business day.");
    }
}

