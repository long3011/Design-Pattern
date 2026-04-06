import java.time.LocalDateTime;

public class Document implements LibraryDocument {
    private final String identifier;
    private final LocalDateTime creationDate;
    private final String content;

    public Document(String identifier, LocalDateTime creationDate, String content) {
        if (identifier == null || identifier.isBlank()) {
            throw new IllegalArgumentException("Identifier must not be blank");
        }
        if (creationDate == null) {
            throw new IllegalArgumentException("Creation date must not be null");
        }
        this.identifier = identifier;
        this.creationDate = creationDate;
        this.content = content == null ? "" : content;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String getContent(User user) {
        return content;
    }
}

