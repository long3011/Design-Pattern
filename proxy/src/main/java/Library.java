import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private final Map<String, LibraryDocument> documents;

    public Library() {
        this.documents = new HashMap<>();
    }

    public void addUnprotectedDocument(String identifier, LocalDateTime creationDate, String content) {
        addDocument(new Document(identifier, creationDate, content));
    }

    public void addProtectedDocument(String identifier, LocalDateTime creationDate, String content) {
        Document protectedDocument = new Document(identifier, creationDate, content);
        addDocument(new DocumentProxy(protectedDocument));
    }

    public void addDocument(LibraryDocument document) {
        String id = document.getIdentifier();
        if (documents.containsKey(id)) {
            throw new IllegalArgumentException("Document with identifier '" + id + "' already exists.");
        }
        documents.put(id, document);
    }

    public LibraryDocument getDocument(String identifier) {
        return documents.get(identifier);
    }

    public String getDocumentContent(String identifier, User user) throws AccessDeniedException {
        LibraryDocument document = documents.get(identifier);
        if (document == null) {
            throw new IllegalArgumentException("Document with identifier '" + identifier + "' was not found.");
        }
        return document.getContent(user);
    }

    public Collection<LibraryDocument> getAllDocuments() {
        return documents.values();
    }
}

