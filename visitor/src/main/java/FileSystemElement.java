/**
 * Interface for all file system elements (File and Directory).
 * Defines the accept method for the visitor pattern.
 */
public interface FileSystemElement {
    /**
     * Accepts a visitor to perform operations on this element.
     *
     * @param visitor the FileSystemVisitor to accept
     */
    void accept(FileSystemVisitor visitor);

    /**
     * Gets the name of the element.
     *
     * @return the name of the element
     */
    String getName();
}

