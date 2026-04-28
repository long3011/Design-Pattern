package solutions.facade;

import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class JsonResponseParser {
    private final JSONParser parser = new JSONParser();

    Object parse(String json) throws IOException {
        try {
            return parser.parse(json);
        } catch (ParseException e) {
            throw new IOException("Failed to parse JSON response.", e);
        }
    }
}

