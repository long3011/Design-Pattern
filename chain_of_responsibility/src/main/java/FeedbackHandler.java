public abstract class FeedbackHandler {

    private FeedbackHandler nextHandler;

    public FeedbackHandler setNext(FeedbackHandler next) {
        this.nextHandler = next;
        return next;
    }
    public final void handle(Message message) {
        if (canHandle(message)) {
            System.out.println(">> " + getClass().getSimpleName()
                    + " is handling: " + message);
            process(message);
            System.out.println();
        } else if (nextHandler != null) {
            nextHandler.handle(message);
        } else {
            System.out.println("[!] No handler found for message: " + message);
            System.out.println();
        }
    }

    protected abstract boolean canHandle(Message message);

    protected abstract void process(Message message);
}

