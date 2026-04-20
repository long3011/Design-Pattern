package mediator.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public record ChatMessage(
        String senderUsername,
        String recipientUsername,
        String content,
        LocalDateTime sentAt
) {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ChatMessage {
        senderUsername = requireNonBlank(senderUsername, "senderUsername");
        recipientUsername = requireNonBlank(recipientUsername, "recipientUsername");
        content = requireNonBlank(content, "content");
        sentAt = Objects.requireNonNull(sentAt, "sentAt must not be null");
    }

    public String toDisplayLine() {
        return "[" + sentAt.format(TIME_FORMAT) + "] "
                + senderUsername + " -> " + recipientUsername + ": " + content;
    }

    private static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }
}

