package solutions.jokes;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONObject; // add Maven dependency for JSON.simple
import org.json.simple.parser.JSONParser; // add Maven dependency for JSON.simple

public class JokeClient {
    public String getRandomJoke() throws Exception {
        String jsonResult = getJsonFromApi("https://api.chucknorris.io/jokes/random");
        return extractJokeFromJson(jsonResult);
    }

    private String getJsonFromApi(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } finally {
            con.disconnect();
        }
    }

    private String extractJokeFromJson(String json) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        return (String) jsonObject.get("value");
    }

    public static void main(String[] args) {
        JokeClient client = new JokeClient();
        try {
            String jokeText = client.getRandomJoke();
            System.out.println(jokeText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}