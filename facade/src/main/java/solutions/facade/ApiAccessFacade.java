package solutions.facade;

import java.io.IOException;


public class ApiAccessFacade {
    private final HttpRequestHandler httpRequestHandler;
    private final JsonResponseParser jsonResponseParser;
    private final JsonAttributeExtractor jsonAttributeExtractor;

    public ApiAccessFacade() {
        this.httpRequestHandler = new HttpRequestHandler();
        this.jsonResponseParser = new JsonResponseParser();
        this.jsonAttributeExtractor = new JsonAttributeExtractor();
    }

    public String getAttributeValueFromJson(String urlString, String attributeName)
            throws IllegalArgumentException, IOException {
        if (urlString == null || urlString.isBlank()) {
            throw new IllegalArgumentException("URL must not be null or blank.");
        }
        if (attributeName == null || attributeName.isBlank()) {
            throw new IllegalArgumentException("Attribute name must not be null or blank.");
        }

        String responseBody = httpRequestHandler.sendGet(urlString);
        Object parsedJson = jsonResponseParser.parse(responseBody);
        String attributeValue = jsonAttributeExtractor.findFirstAttributeValue(parsedJson, attributeName);

        if (attributeValue == null) {
            throw new IllegalArgumentException("Attribute '" + attributeName + "' was not found.");
        }
        return attributeValue;
    }
}

