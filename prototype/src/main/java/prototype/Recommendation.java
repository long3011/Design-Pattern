package prototype;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Recommendation implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String targetAudience;
    private final List<Book> books;

    public Recommendation(String targetAudience) {
        this(targetAudience, List.of());
    }

    public Recommendation(String targetAudience, List<Book> books) {
        this.targetAudience = requireAudience(targetAudience);
        this.books = new ArrayList<>();
        for (Book book : Objects.requireNonNull(books, "books must not be null")) {
            this.books.add(Objects.requireNonNull(book, "book must not be null").clone());
        }
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = requireAudience(targetAudience);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void addBook(Book book) {
        books.add(Objects.requireNonNull(book, "book must not be null").clone());
    }

    public boolean removeBookByTitle(String title) {
        if (title == null || title.isBlank()) {
            return false;
        }
        String normalized = title.trim();
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(normalized));
    }

    public Book removeBookAt(int index) {
        return books.remove(index);
    }

    @Override
    public Recommendation clone() {
        return new Recommendation(targetAudience, books);
    }

    public String summary() {
        return "Audience: %s (%d books)".formatted(targetAudience, books.size());
    }

    private static String requireAudience(String targetAudience) {
        if (targetAudience == null || targetAudience.isBlank()) {
            throw new IllegalArgumentException("targetAudience must not be blank");
        }
        return targetAudience.trim();
    }
}

