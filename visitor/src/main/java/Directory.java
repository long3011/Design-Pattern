import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system.
 * A directory can contain both files and other directories.
 */
public class Directory implements FileSystemElement {
    private String name;
    private List<FileSystemElement> elements;

    /**
     * Creates a Directory with the specified name.
     *
     * @param name the name of the directory
     */
    public Directory(String name) {
        this.name = name;
        this.elements = new ArrayList<>();
    }

    /**
     * Gets the name of the directory.
     *
     * @return the directory name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Adds a file system element (file or directory) to this directory.
     *
     * @param element the FileSystemElement to add
     */
    public void addElement(FileSystemElement element) {
        elements.add(element);
    }

    /**
     * Removes a file system element from this directory.
     *
     * @param element the FileSystemElement to remove
     */
    public void removeElement(FileSystemElement element) {
        elements.remove(element);
    }

    /**
     * Gets all elements contained in this directory.
     *
     * @return a list of FileSystemElement objects
     */
    public List<FileSystemElement> getElements() {
        return new ArrayList<>(elements);
    }

    /**
     * Accepts a visitor for performing operations on this directory and its contents.
     *
     * @param visitor the FileSystemVisitor to accept
     */
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }

    @Override
    public String toString() {
        return "Directory: " + name;
    }
}

