package prototype;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PrototypeRegistry {
    private final Map<String, Recommendation> templates = new LinkedHashMap<>();

    public void registerTemplate(String name, Recommendation recommendation) {
        String normalizedName = normalizeName(name);
        if (recommendation == null) {
            throw new IllegalArgumentException("recommendation must not be null");
        }
        templates.put(normalizedName, recommendation.clone());
    }

    public Recommendation createFromTemplate(String name) {
        Recommendation template = templates.get(normalizeName(name));
        if (template == null) {
            throw new IllegalArgumentException("No recommendation template found with name: " + name);
        }
        return template.clone();
    }

    public Recommendation getTemplate(String name) {
        Recommendation template = templates.get(normalizeName(name));
        if (template == null) {
            throw new IllegalArgumentException("No recommendation template found with name: " + name);
        }
        return template.clone();
    }

    public boolean removeTemplate(String name) {
        return templates.remove(normalizeName(name)) != null;
    }

    public Set<String> listTemplateNames() {
        return Set.copyOf(templates.keySet());
    }

    public Map<String, Recommendation> snapshot() {
        Map<String, Recommendation> copy = new LinkedHashMap<>();
        for (Map.Entry<String, Recommendation> entry : templates.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    public void replaceAll(Map<String, Recommendation> replacements) {
        templates.clear();
        for (Map.Entry<String, Recommendation> entry : replacements.entrySet()) {
            registerTemplate(entry.getKey(), entry.getValue());
        }
    }

    public boolean isEmpty() {
        return templates.isEmpty();
    }

    private static String normalizeName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("template name must not be blank");
        }
        return name.trim();
    }
}

