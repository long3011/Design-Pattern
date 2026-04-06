/**
 * Example usage of the file system with the Visitor pattern.
 * Demonstrates how to create a directory structure and use visitors to perform operations.
 */
public class FileSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== File System Visitor Pattern Demo ===\n");

        // Create a directory structure
        Directory root = new Directory("root");

        // Create subdirectories
        Directory documents = new Directory("Documents");
        Directory projects = new Directory("Projects");
        Directory downloads = new Directory("Downloads");

        root.addElement(documents);
        root.addElement(projects);
        root.addElement(downloads);

        // Add files to Documents
        documents.addElement(new File("resume.pdf", 0.5));
        documents.addElement(new File("cover_letter.docx", 0.3));
        documents.addElement(new File("notes.txt", 0.1));

        // Create nested directories in Projects
        Directory javaProject = new Directory("JavaProject");
        Directory pythonProject = new Directory("PythonProject");

        projects.addElement(javaProject);
        projects.addElement(pythonProject);

        // Add files to Java Project
        javaProject.addElement(new File("Main.java", 0.02));
        javaProject.addElement(new File("Utils.java", 0.015));
        javaProject.addElement(new File("config.xml", 0.005));

        // Add files to Python Project
        pythonProject.addElement(new File("main.py", 0.03));
        pythonProject.addElement(new File("config.py", 0.02));

        // Add files to Downloads
        downloads.addElement(new File("video.mp4", 500));
        downloads.addElement(new File("image.jpg", 5));
        downloads.addElement(new File("archive.zip", 250));

        System.out.println("Directory structure created.\n");
        System.out.println("=== Using SizeCalculatorVisitor ===\n");

        // Use SizeCalculatorVisitor to calculate total size
        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        System.out.println("\nTotal size of all files: " + sizeCalculator.getTotalSize() + " MB\n");

        System.out.println("=== Using SearchVisitor (Search for .java files) ===\n");

        // Use SearchVisitor to find all Java files
        SearchVisitor javaFileFinder = new SearchVisitor("java");
        root.accept(javaFileFinder);
        System.out.println("Found " + javaFileFinder.getMatchCount() + " Java files:");
        for (File file : javaFileFinder.getMatchedFiles()) {
            System.out.println("  - " + file.getName());
        }

        System.out.println("\n=== Using SearchVisitor (Search for .txt files) ===\n");

        // Use SearchVisitor to find all text files
        SearchVisitor textFileFinder = new SearchVisitor("txt");
        root.accept(textFileFinder);
        System.out.println("Found " + textFileFinder.getMatchCount() + " text files:");
        for (File file : textFileFinder.getMatchedFiles()) {
            System.out.println("  - " + file.getName());
        }

        System.out.println("\n=== Using SearchVisitor (Search by name contains 'config') ===\n");

        // Use SearchVisitor to find all files with 'config' in the name
        SearchVisitor configFileFinder = new SearchVisitor("config", SearchVisitor.SearchType.BY_NAME_CONTAINS);
        root.accept(configFileFinder);
        System.out.println("Found " + configFileFinder.getMatchCount() + " files containing 'config':");
        for (File file : configFileFinder.getMatchedFiles()) {
            System.out.println("  - " + file.getName());
        }

        System.out.println("\n=== Using SearchVisitor (Search for .py files) ===\n");

        // Use SearchVisitor to find all Python files
        SearchVisitor pythonFileFinder = new SearchVisitor("py");
        root.accept(pythonFileFinder);
        System.out.println("Found " + pythonFileFinder.getMatchCount() + " Python files:");
        for (File file : pythonFileFinder.getMatchedFiles()) {
            System.out.println("  - " + file.getName());
        }
    }
}

