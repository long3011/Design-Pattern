package mediator.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChatRoomMediator implements ChatMediator {
    private final Map<String, ChatClient> clientsByUsername = new LinkedHashMap<>();

    @Override
    public void registerClient(ChatClient client) {
        ChatClient checkedClient = Objects.requireNonNull(client, "client must not be null");
        String username = checkedClient.getUsername();

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Client username must not be blank");
        }

        if (clientsByUsername.containsKey(username)) {
            throw new IllegalArgumentException("Username already registered: " + username);
        }

        clientsByUsername.put(username, checkedClient);
    }

    @Override
    public void sendPrivateMessage(String senderUsername, String recipientUsername, String content) {
        String sender = requireNonBlank(senderUsername, "senderUsername");
        String recipient = requireNonBlank(recipientUsername, "recipientUsername");
        String cleanedContent = requireNonBlank(content, "content");

        ChatClient senderClient = clientsByUsername.get(sender);
        if (senderClient == null) {
            throw new IllegalArgumentException("Unknown sender: " + sender);
        }

        ChatClient recipientClient = clientsByUsername.get(recipient);
        if (recipientClient == null) {
            throw new IllegalArgumentException("Unknown recipient: " + recipient);
        }

        ChatMessage message = new ChatMessage(sender, recipient, cleanedContent, LocalDateTime.now());
        senderClient.receive(message);

        if (!sender.equals(recipient)) {
            recipientClient.receive(message);
        }
    }

    @Override
    public List<String> getRegisteredUsernames() {
        return List.copyOf(new ArrayList<>(clientsByUsername.keySet()));
    }

    private static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }

        return value.trim();
    }
}

