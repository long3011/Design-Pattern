package prototype;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public final class Book implements Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String author;
    private final String title;
    private final String genre;
    private final int publicationYear;

    public Book(String author, String title, String genre, int publicationYear) {
        this.author = requireText(author, "author");
        this.title = requireText(title, "title");
        this.genre = requireText(genre, "genre");
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public Book clone() {
        return new Book(author, title, genre, publicationYear);
    }

    @Override
    public String toString() {
        return "%s by %s (%s, %d)".formatted(title, author, genre, publicationYear);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book other)) {
            return false;
        }
        return publicationYear == other.publicationYear
                && author.equals(other.author)
                && title.equals(other.title)
                && genre.equals(other.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, genre, publicationYear);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value.trim();
    }
}

