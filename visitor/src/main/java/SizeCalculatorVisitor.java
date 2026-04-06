/**
 * Visitor that calculates the total size of all files in the file system.
 * It traverses the entire directory structure and accumulates the sizes of all files.
 */
public class SizeCalculatorVisitor implements FileSystemVisitor {
    private double totalSize;

    /**
     * Creates a SizeCalculatorVisitor with initial total size of 0.
     */
    public SizeCalculatorVisitor() {
        this.totalSize = 0;
    }

    /**
     * Visit a file and add its size to the total.
     *
     * @param file the File to visit
     */
    @Override
    public void visitFile(File file) {
        totalSize += file.getSize();
        System.out.println("Visiting file: " + file.getName() + " (" + file.getSize() + " MB)");
    }

    /**
     * Visit a directory and traverse all its contents.
     *
     * @param directory the Directory to visit
     */
    @Override
    public void visitDirectory(Directory directory) {
        System.out.println("Entering directory: " + directory.getName());
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }

    /**
     * Gets the total size accumulated during traversal.
     *
     * @return the total size in megabytes
     */
    public double getTotalSize() {
        return totalSize;
    }

    /**
     * Resets the total size to 0.
     */
    public void reset() {
        totalSize = 0;
    }
}

