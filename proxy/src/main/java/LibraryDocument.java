import java.time.LocalDateTime;

public interface LibraryDocument {
    String getIdentifier();

    LocalDateTime getCreationDate();

    String getContent(User user) throws AccessDeniedException;
}

