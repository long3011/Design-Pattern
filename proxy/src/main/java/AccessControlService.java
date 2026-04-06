import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class AccessControlService {
    private static final AccessControlService INSTANCE = new AccessControlService();

    private final Map<String, Set<String>> allowedDocumentsByUser;

    private AccessControlService() {
        this.allowedDocumentsByUser = new HashMap<>();
    }

    public static AccessControlService getInstance() {
        return INSTANCE;
    }

    public synchronized void grantAccess(String documentIdentifier, String username) {
        allowedDocumentsByUser
                .computeIfAbsent(username, ignored -> new HashSet<>())
                .add(documentIdentifier);
    }

    public synchronized void revokeAccess(String documentIdentifier, String username) {
        Set<String> allowedDocs = allowedDocumentsByUser.get(username);
        if (allowedDocs == null) {
            return;
        }
        allowedDocs.remove(documentIdentifier);
        if (allowedDocs.isEmpty()) {
            allowedDocumentsByUser.remove(username);
        }
    }

    public synchronized boolean isAllowed(String documentIdentifier, String username) {
        Set<String> allowedDocs = allowedDocumentsByUser.get(username);
        return allowedDocs != null && allowedDocs.contains(documentIdentifier);
    }
}

