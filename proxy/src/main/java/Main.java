import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        AccessControlService accessControlService = AccessControlService.getInstance();

        library.addUnprotectedDocument(
                "public-001",
                LocalDateTime.now().minusDays(7),
                "Public handbook. This can be read by everyone."
        );

        library.addProtectedDocument(
                "secret-001",
                LocalDateTime.now().minusDays(2),
                "Quarterly financial details for management only."
        );

        library.addProtectedDocument(
                "secret-002",
                LocalDateTime.now().minusHours(12),
                "Product roadmap draft."
        );

        User alice = new User("alice");
        User bob = new User("bob");
        User charlie = new User("charlie");

        accessControlService.grantAccess("secret-001", "alice");
        accessControlService.grantAccess("secret-002", "bob");

        printContent(library, "public-001", alice);
        printContent(library, "public-001", bob);

        printContent(library, "secret-001", alice);
        printContent(library, "secret-001", bob);

        printContent(library, "secret-002", bob);
        printContent(library, "secret-002", charlie);

        System.out.println("Granting charlie access to secret-002 and retrying...");
        accessControlService.grantAccess("secret-002", "charlie");
        printContent(library, "secret-002", charlie);
    }

    private static void printContent(Library library, String documentId, User user) {
        try {
            LibraryDocument document = library.getDocument(documentId);
            System.out.println("----");
            System.out.println("User: " + user.getUsername());
            System.out.println("Document ID: " + documentId);
            System.out.println("Created: " + document.getCreationDate());
            System.out.println("Content: " + library.getDocumentContent(documentId, user));
        } catch (AccessDeniedException ex) {
            System.out.println("----");
            System.out.println("User: " + user.getUsername());
            System.out.println("Document ID: " + documentId);
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}

