package responder;

import http.Client;
import java.util.Optional;
/*
 * The Responder to LLM prompts. Run respond to get the configured LLM to respond. 
 * Context will build in the Responder class
 */
public class Responder {
    static final String model = "gemma3:1b";
    static final String ERROR_LITERAL = "\"I_CANNOT_ANSWER_YOUR_REQUEST\"";

    static final String prePrompt = "For the following question: Restructure into the format of a Jira Ticket.\\n" 
    + "Do not make up extra details, but if you feel some information is missing, add a placeholder.\\n" 
    + "Do not ask clarifying questions, just give the plain answer.\\n"
    + "If for any reason you cannot answer the question, respond with " + ERROR_LITERAL + ".";

    private StringBuilder createPrompt(String input) {
        StringBuilder prompt = new StringBuilder(prePrompt);
        prompt.append(input);
        return prompt;
    }

    private StringBuilder createJsonRequest(String model, StringBuilder prompt) {
        StringBuilder jsonRequest = new StringBuilder();
        jsonRequest.append("{\"model\": \"")
              .append(model)
              .append("\", \"prompt\": \"")
              .append(prompt)
              .append("\"}");
        return jsonRequest;
    }

    public String respond(String input) {
        StringBuilder prompt = createPrompt(input);

        String jsonRequest = createJsonRequest(model, prompt).toString();
        System.out.println(" json : " + jsonRequest);
        Optional<String> response = new Client().post(jsonRequest);
        if(response.isEmpty()) {
            throw new RuntimeException("Did not receive a response from the LLM");
        }
        String responseText = response.get();
        if(responseText.contains(ERROR_LITERAL)) {
            throw new RuntimeException("Response of the LLM contained the error literal");
        }
        return responseText;
    }
}