package responder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * The Responder to LLM prompts. Run respond to get the configured LLM to respond. 
 * Context will build in the Responder class
 */
public class Responder {
    String address = "127.0.0.1";
    int port = 11434;

    String model = "gemma3:1b";

    // Create the HttpClient
    static HttpClient client = HttpClient.newHttpClient();

    public String respond(String input) {
        String jsonRequest = String.format("{\"model\": \"%s\", \"prompt\": \"%s\"}", model, input);

        try {
            // Build the URI
            URI uri = new URI("http://" + address + ":" + port + "/api/generate");

            // Build the HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Return the response body
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}