/**
 * Represents a file in the file system.
 * A file has a name and a size in megabytes.
 */
public class File implements FileSystemElement {
    private String name;
    private double size; // in megabytes

    /**
     * Creates a File with the specified name and size.
     *
     * @param name the name of the file
     * @param size the size of the file in megabytes
     */
    public File(String name, double size) {
        this.name = name;
        this.size = size;
    }

    /**
     * Gets the name of the file.
     *
     * @return the file name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the size of the file.
     *
     * @return the file size in megabytes
     */
    public double getSize() {
        return size;
    }

    /**
     * Gets the file extension (e.g., "txt", "java", "pdf").
     *
     * @return the file extension or empty string if no extension
     */
    public String getExtension() {
        int lastDot = name.lastIndexOf('.');
        if (lastDot > 0 && lastDot < name.length() - 1) {
            return name.substring(lastDot + 1).toLowerCase();
        }
        return "";
    }

    /**
     * Accepts a visitor for performing operations on this file.
     *
     * @param visitor the FileSystemVisitor to accept
     */
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }

    @Override
    public String toString() {
        return "File: " + name + " (" + size + " MB)";
    }
}

