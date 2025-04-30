package llm;

import requests.Client;
import java.util.Optional;
/*
 * The Responder to LLM prompts. Run respond to get the configured LLM to respond. 
 * Context will build in the Responder class
 */
public class Responder {
    static final String model = "gemma3:1b";
    static final String ERROR_LITERAL = "\\\"I_CANNOT_ANSWER_YOUR_REQUEST\\\"";
    static final String TICKET_DIVIDER = "\\\"#########\\\"";

    static final String prePrompt = "For the following question: Restructure into the format of a Jira Ticket.\\n" 
    + "Do not make up extra details, but if you feel some information is missing, add a placeholder.\\n" 
    + "Do not ask clarifying questions, just give the plain answer.\\n"
    + "IMPORTANT: Restrict yourself to the following fields: 1. summary, 2. description, 3. priority\\n"
    + "DO NOT give any introduction to the answer, don't aknowledge the request in any way, just answer.\\n";

    static final String titlePrePrompt = "The following is an issue text. Can you summarize it in a title?\\n"
    + "DO NOT give any introduction to the answer, don't aknowledge the request in any way, just answer.\\n"
    + "Only a single sentence\\n";

    private StringBuilder createPrompt(String input) {
        StringBuilder prompt = new StringBuilder(prePrompt);
        prompt.append(input);
        return prompt;
    }
    
    private StringBuilder createTitlePrompt(String input) {
        StringBuilder prompt = new StringBuilder(titlePrePrompt);
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

    public String createResponse(String input) {
        StringBuilder prompt = createPrompt(input);

        String jsonRequest = createJsonRequest(model, prompt).toString();
        Optional<String> response = new Client().postQuery(jsonRequest);
        if(response.isEmpty()) {
            throw new RuntimeException("Did not receive a response from the LLM");
        }
        return response.get();
    }

    public String createTitle(String input) {
        StringBuilder prompt = createTitlePrompt(input);

        String jsonRequest = createJsonRequest(model, prompt).toString();
        Optional<String> response = new Client().postQuery(jsonRequest);
        if(response.isEmpty()) {
            throw new RuntimeException("Did not receive a response from the LLM");
        }
        return response.get();
    }
}