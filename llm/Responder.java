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
    + "Restrict yourself to the following fields: summary, description, priority\\n"
    + "If you cannot reasonably answer the question, respond with " + ERROR_LITERAL + ".\\n"
    + "Can you put the text in a markdown block?\\n";
    // + "Divide the text of the ticket from your response to the question introducing the text of the ticket with the text " + TICKET_DIVIDER + ".\\n";

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
        Optional<String> response = new Client().postQuery(jsonRequest);
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