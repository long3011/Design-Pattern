public class CompensationClaimHandler extends FeedbackHandler {

    @Override
    protected boolean canHandle(Message message) {
        return message.getType() == MessageType.COMPENSATION_CLAIM;
    }

    @Override
    protected void process(Message message) {
        String content = message.getContent().toLowerCase();

        if (content.contains("small") || content.contains("minor")
                || content.contains("slight")) {
            System.out.println("   [COMPENSATION] Auto-approved: low-impact claim.");
            System.out.println("   Action : Compensation coupon e-mailed to "
                    + message.getSenderEmail());
        } else if (content.contains("fraud") || content.contains("legal")
                || content.contains("lawsuit")) {
            System.out.println("   [COMPENSATION] Claim escalated to legal team.");
            System.out.println("   Action : Legal department notified; response within 5 business days.");
        } else {
            System.out.println("   [COMPENSATION] Forwarded to Billing Department for manual review.");
            System.out.println("   Action : Case #" + generateCaseNumber()
                    + " opened. Reply sent to " + message.getSenderEmail());
        }
    }

    private int generateCaseNumber() {
        return (int) (Math.random() * 90_000) + 10_000;
    }
}

