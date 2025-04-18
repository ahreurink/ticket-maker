package responder;

import http.Client;

/*
 * The Responder to LLM prompts. Run respond to get the configured LLM to respond. 
 * Context will build in the Responder class
 */
public class Responder {
    String model = "gemma3:1b";

    public String respond(String input) {
        String jsonRequest = String.format("{\"model\": \"%s\", \"prompt\": \"%s\"}", model, input);
        // Send the request and get the response
        var client = new Client();
        String response = client.post(jsonRequest);
        return response;
    }
}