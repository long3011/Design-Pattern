package prototype;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileRecommendationRepository implements RecommendationRepository {
    private final Path storagePath;

    public FileRecommendationRepository(Path storagePath) {
        this.storagePath = storagePath;
    }

    @Override
    public void save(Map<String, Recommendation> recommendations) throws IOException {
        Path parent = storagePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(storagePath))) {
            output.writeObject(cloneMap(recommendations));
        }
    }

    @Override
    public Map<String, Recommendation> load() throws IOException {
        if (!Files.exists(storagePath)) {
            return new LinkedHashMap<>();
        }

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(storagePath))) {
            Object value = input.readObject();
            if (!(value instanceof Map<?, ?> rawMap)) {
                throw new IOException("Invalid recommendation file format");
            }

            Map<String, Recommendation> result = new LinkedHashMap<>();
            for (Map.Entry<?, ?> entry : rawMap.entrySet()) {
                if (!(entry.getKey() instanceof String key) || !(entry.getValue() instanceof Recommendation recommendation)) {
                    throw new IOException("Invalid recommendation entry format");
                }
                result.put(key, recommendation.clone());
            }
            return result;
        } catch (ClassNotFoundException exception) {
            throw new IOException("Unable to read recommendation file", exception);
        }
    }

    private static Map<String, Recommendation> cloneMap(Map<String, Recommendation> source) {
        Map<String, Recommendation> copy = new LinkedHashMap<>();
        for (Map.Entry<String, Recommendation> entry : source.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }
}

