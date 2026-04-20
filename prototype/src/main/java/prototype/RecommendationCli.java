package prototype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecommendationCli {
    private final PrototypeRegistry registry;
    private final RecommendationRepository repository;
    private final Scanner scanner;

    public RecommendationCli(PrototypeRegistry registry, RecommendationRepository repository, Scanner scanner) {
        this.registry = registry;
        this.repository = repository;
        this.scanner = scanner;
    }

    public void initialize() {
        try {
            registry.replaceAll(repository.load());
        } catch (IOException exception) {
            System.out.println("Could not load saved recommendations: " + exception.getMessage());
        }

        if (registry.isEmpty()) {
            seedDefaultRecommendations();
        }
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String option = readLine("Choose an option: ");

            switch (option) {
                case "1" -> listRecommendations();
                case "2" -> createRecommendationFromScratch();
                case "3" -> cloneRecommendation();
                case "4" -> editExistingRecommendation();
                case "5" -> saveRecommendations();
                case "6" -> loadRecommendations();
                case "0" -> {
                    saveRecommendations();
                    running = false;
                }
                default -> System.out.println("Unknown option. Please choose a valid menu item.");
            }
        }

        System.out.println("Goodbye.");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Book Recommendation Prototype System ===");
        System.out.println("1. View recommendations");
        System.out.println("2. Create recommendation from scratch");
        System.out.println("3. Clone and modify an existing recommendation");
        System.out.println("4. Edit an existing recommendation");
        System.out.println("5. Save recommendations");
        System.out.println("6. Reload recommendations from file");
        System.out.println("0. Exit");
    }

    private void listRecommendations() {
        List<String> names = getSortedNames();
        if (names.isEmpty()) {
            System.out.println("No recommendation lists available.");
            return;
        }

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            Recommendation recommendation = registry.getTemplate(name);
            System.out.printf("%d) %s - %s%n", i + 1, name, recommendation.summary());
            List<Book> books = recommendation.getBooks();
            for (int j = 0; j < books.size(); j++) {
                System.out.printf("   %d.%d %s%n", i + 1, j + 1, books.get(j));
            }
        }
    }

    private void createRecommendationFromScratch() {
        String name = readLine("Enter a name for the recommendation list: ");
        String audience = readLine("Enter the target audience: ");

        Recommendation recommendation = new Recommendation(audience);
        editLoop(recommendation);
        registry.registerTemplate(name, recommendation);

        System.out.println("Recommendation created: " + name);
    }

    private void cloneRecommendation() {
        List<String> names = getSortedNames();
        if (names.isEmpty()) {
            System.out.println("Nothing to clone yet.");
            return;
        }

        String sourceName = chooseRecommendationName(names, "Choose a recommendation to clone");
        if (sourceName == null) {
            return;
        }

        String newName = readLine("Enter a name for the cloned recommendation: ");
        Recommendation clone = registry.createFromTemplate(sourceName);

        String newAudience = readLine("Target audience for clone (leave blank to keep current): ");
        if (!newAudience.isBlank()) {
            clone.setTargetAudience(newAudience);
        }

        editLoop(clone);
        registry.registerTemplate(newName, clone);
        System.out.println("Cloned recommendation saved as: " + newName);
    }

    private void editExistingRecommendation() {
        List<String> names = getSortedNames();
        if (names.isEmpty()) {
            System.out.println("No recommendation lists available.");
            return;
        }

        String name = chooseRecommendationName(names, "Choose a recommendation to edit");
        if (name == null) {
            return;
        }

        Recommendation recommendation = registry.getTemplate(name);
        editLoop(recommendation);
        registry.registerTemplate(name, recommendation);
        System.out.println("Updated recommendation: " + name);
    }

    private void editLoop(Recommendation recommendation) {
        boolean editing = true;
        while (editing) {
            System.out.println();
            System.out.println("Editing recommendation for: " + recommendation.getTargetAudience());
            System.out.println("1. Change target audience");
            System.out.println("2. Add a book");
            System.out.println("3. Remove a book by index");
            System.out.println("4. Remove books by title");
            System.out.println("5. Show current books");
            System.out.println("0. Done editing");

            String option = readLine("Choose an edit option: ");
            switch (option) {
                case "1" -> {
                    String newAudience = readLine("New target audience: ");
                    recommendation.setTargetAudience(newAudience);
                }
                case "2" -> recommendation.addBook(promptBook());
                case "3" -> removeBookByIndex(recommendation);
                case "4" -> removeBookByTitle(recommendation);
                case "5" -> printBooks(recommendation);
                case "0" -> editing = false;
                default -> System.out.println("Unknown edit option.");
            }
        }
    }

    private void removeBookByIndex(Recommendation recommendation) {
        List<Book> books = recommendation.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books to remove.");
            return;
        }

        printBooks(recommendation);
        String rawIndex = readLine("Enter book index to remove: ");

        try {
            int index = Integer.parseInt(rawIndex) - 1;
            Book removed = recommendation.removeBookAt(index);
            System.out.println("Removed: " + removed);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Invalid index.");
        }
    }

    private void removeBookByTitle(Recommendation recommendation) {
        String title = readLine("Enter title to remove (case-insensitive): ");
        boolean removed = recommendation.removeBookByTitle(title);
        if (removed) {
            System.out.println("Matching book(s) removed.");
        } else {
            System.out.println("No matching title found.");
        }
    }

    private void printBooks(Recommendation recommendation) {
        List<Book> books = recommendation.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books in this recommendation.");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, books.get(i));
        }
    }

    private Book promptBook() {
        String title = readLine("Book title: ");
        String author = readLine("Book author: ");
        String genre = readLine("Book genre: ");
        int year = readInt("Publication year: ");
        return new Book(author, title, genre, year);
    }

    private void saveRecommendations() {
        try {
            Map<String, Recommendation> snapshot = registry.snapshot();
            repository.save(snapshot);
            System.out.println("Recommendations saved.");
        } catch (IOException exception) {
            System.out.println("Save failed: " + exception.getMessage());
        }
    }

    private void loadRecommendations() {
        try {
            registry.replaceAll(repository.load());
            if (registry.isEmpty()) {
                seedDefaultRecommendations();
            }
            System.out.println("Recommendations loaded.");
        } catch (IOException exception) {
            System.out.println("Load failed: " + exception.getMessage());
        }
    }

    private int readInt(String prompt) {
        while (true) {
            String raw = readLine(prompt);
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String chooseRecommendationName(List<String> names, String prompt) {
        System.out.println(prompt + ":");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, names.get(i));
        }

        String raw = readLine("Enter number (or blank to cancel): ");
        if (raw.isBlank()) {
            return null;
        }

        try {
            int selected = Integer.parseInt(raw) - 1;
            if (selected < 0 || selected >= names.size()) {
                System.out.println("Selection out of range.");
                return null;
            }
            return names.get(selected);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid selection.");
            return null;
        }
    }

    private List<String> getSortedNames() {
        List<String> names = new ArrayList<>(registry.listTemplateNames());
        names.sort(String::compareToIgnoreCase);
        return names;
    }

    private void seedDefaultRecommendations() {
        Recommendation beginners = new Recommendation("New fantasy readers");
        beginners.addBook(new Book("J.R.R. Tolkien", "The Hobbit", "Fantasy", 1937));
        beginners.addBook(new Book("N.K. Jemisin", "The Fifth Season", "Fantasy", 2015));

        Recommendation sciFiFans = new Recommendation("Science-fiction fans");
        sciFiFans.addBook(new Book("Frank Herbert", "Dune", "Science Fiction", 1965));
        sciFiFans.addBook(new Book("Liu Cixin", "The Three-Body Problem", "Science Fiction", 2006));

        registry.registerTemplate("Fantasy Starter", beginners);
        registry.registerTemplate("Sci-Fi Essentials", sciFiFans);
    }
}

