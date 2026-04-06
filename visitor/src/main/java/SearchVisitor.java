import java.util.ArrayList;
import java.util.List;

/**
 * Visitor that searches for files matching a specified criterion.
 * It accumulates files that match the search criteria as it traverses the file system.
 * Can search by file extension or by name pattern.
 */
public class SearchVisitor implements FileSystemVisitor {
    private List<File> matchedFiles;
    private String searchCriterion; // Can be a file extension (e.g., "txt", "java") or a name pattern
    private SearchType searchType;

    /**
     * Enum for different types of search.
     */
    public enum SearchType {
        BY_EXTENSION,
        BY_NAME_CONTAINS
    }

    /**
     * Creates a SearchVisitor that searches by file extension.
     *
     * @param extension the file extension to search for (e.g., "txt", "java")
     */
    public SearchVisitor(String extension) {
        this(extension, SearchType.BY_EXTENSION);
    }

    /**
     * Creates a SearchVisitor with a specified search type.
     *
     * @param criterion the search criterion
     * @param searchType the type of search to perform
     */
    public SearchVisitor(String criterion, SearchType searchType) {
        this.matchedFiles = new ArrayList<>();
        this.searchCriterion = criterion.toLowerCase();
        this.searchType = searchType;
    }

    /**
     * Visit a file and check if it matches the search criteria.
     *
     * @param file the File to visit
     */
    @Override
    public void visitFile(File file) {
        boolean matches = false;

        switch (searchType) {
            case BY_EXTENSION:
                matches = file.getExtension().equals(searchCriterion);
                break;
            case BY_NAME_CONTAINS:
                matches = file.getName().toLowerCase().contains(searchCriterion);
                break;
        }

        if (matches) {
            matchedFiles.add(file);
            System.out.println("Found matching file: " + file.getName());
        }
    }

    /**
     * Visit a directory and traverse all its contents.
     *
     * @param directory the Directory to visit
     */
    @Override
    public void visitDirectory(Directory directory) {
        System.out.println("Searching in directory: " + directory.getName());
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }

    /**
     * Gets all files that matched the search criteria.
     *
     * @return a list of matching File objects
     */
    public List<File> getMatchedFiles() {
        return new ArrayList<>(matchedFiles);
    }

    /**
     * Gets the count of matched files.
     *
     * @return the number of matched files
     */
    public int getMatchCount() {
        return matchedFiles.size();
    }

    /**
     * Resets the matched files list.
     */
    public void reset() {
        matchedFiles.clear();
    }
}

