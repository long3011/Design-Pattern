/**
 * Interface for visitors that operate on file system elements.
 * Defines visit methods for different types of file system elements.
 */
public interface FileSystemVisitor {
    /**
     * Visit a file element.
     *
     * @param file the File to visit
     */
    void visitFile(File file);

    /**
     * Visit a directory element.
     *
     * @param directory the Directory to visit
     */
    void visitDirectory(Directory directory);
}

