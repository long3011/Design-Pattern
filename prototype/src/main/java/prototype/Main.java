package prototype;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path storagePath = Path.of("prototype", "recommendations.ser");
        PrototypeRegistry registry = new PrototypeRegistry();
        RecommendationRepository repository = new FileRecommendationRepository(storagePath);

        try (Scanner scanner = new Scanner(System.in)) {
            RecommendationCli cli = new RecommendationCli(registry, repository, scanner);
            cli.initialize();
            cli.run();
        }
    }
}

