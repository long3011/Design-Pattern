package solutions.facade;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class JsonAttributeExtractor {
    String findFirstAttributeValue(Object jsonNode, String attributeName) {
        if (jsonNode instanceof JSONObject jsonObject) {
            if (jsonObject.containsKey(attributeName)) {
                Object value = jsonObject.get(attributeName);
                return value == null ? "null" : String.valueOf(value);
            }

            for (Object value : jsonObject.values()) {
                String found = findFirstAttributeValue(value, attributeName);
                if (found != null) {
                    return found;
                }
            }
            return null;
        }

        if (jsonNode instanceof JSONArray jsonArray) {
            for (Object item : jsonArray) {
                String found = findFirstAttributeValue(item, attributeName);
                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }
}

