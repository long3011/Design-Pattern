package prototype;

import java.io.IOException;
import java.util.Map;

public interface RecommendationRepository {
    void save(Map<String, Recommendation> recommendations) throws IOException;

    Map<String, Recommendation> load() throws IOException;
}

