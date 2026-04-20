package mediator.domain;

import java.util.List;

public interface ChatMediator {
    void registerClient(ChatClient client);

    void sendPrivateMessage(String senderUsername, String recipientUsername, String content);

    List<String> getRegisteredUsernames();
}

