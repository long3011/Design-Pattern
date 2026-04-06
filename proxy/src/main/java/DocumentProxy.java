import java.time.LocalDateTime;

public class DocumentProxy implements LibraryDocument {
    private final Document protectedDocument;
    private final AccessControlService accessControlService;

    public DocumentProxy(Document protectedDocument) {
        this.protectedDocument = protectedDocument;
        this.accessControlService = AccessControlService.getInstance();
    }

    @Override
    public String getIdentifier() {
        return protectedDocument.getIdentifier();
    }

    @Override
    public LocalDateTime getCreationDate() {
        return protectedDocument.getCreationDate();
    }

    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (user == null) {
            throw new AccessDeniedException("Anonymous users cannot access protected documents.");
        }

        if (accessControlService.isAllowed(getIdentifier(), user.getUsername())) {
            return protectedDocument.getContent(user);
        }

        throw new AccessDeniedException(
                "Access denied for user '" + user.getUsername() + "' to document '" + getIdentifier() + "'."
        );
    }
}

