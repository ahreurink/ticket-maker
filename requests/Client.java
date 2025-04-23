package requests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;
/*
 * The Responder to LLM prompts. Run respond to get the configured LLM to respond. 
 * Context will build in the Responder class
 */
public class Client {
    String address = "127.0.0.1";
    int port = 11434;

    private static final String GITHUB_BASE_URL = "https://api.github.com/repos/";

    HttpClient client = HttpClient.newHttpClient();

    public Optional<String> postQuery(String requestBody) {
    try {
            URI uri = new URI("http://" + address + ":" + port + "/api/generate");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
                System.err.println("Model returned error: " + response.statusCode() );
                return Optional.empty();
            }
            String[] responseObjects = response.body().split("\n");
            String responseText = Arrays.stream(responseObjects)
                .filter(str -> str.endsWith("\"done\":false}"))
                .map(s -> s.substring(0, s.length() - 15))
                .map(s -> s.replaceFirst("\\{.*\"response\":\"", ""))
                .collect(Collectors.joining());

            return Optional.of(responseText);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void postTicket(String owner, String repo, String token, String requestBody) {
    try {
            URI uri = new URI(GITHUB_BASE_URL + owner + "/" + repo + "/issues");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/vnd.github+json")
                    .header("Authorization", "Bearer " + token)
                    .header("X-GitHub-Api-Version", "2022-11-28")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200) {
                throw new RuntimeException("Github returned error: " + response.statusCode() );
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Github returned error: " + e.getMessage());
        }
    }
}