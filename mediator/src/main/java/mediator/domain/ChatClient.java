package mediator.domain;

public interface ChatClient {
    String getUsername();

    void receive(ChatMessage message);
}

