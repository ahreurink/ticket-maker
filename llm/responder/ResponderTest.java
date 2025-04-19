package responder;

public class ResponderTest {
    public static void main(String[] args) {
        Responder responder = new Responder();
        String prompt1 = "Respond to me in JSON";
        String response1 = responder.respond(prompt1);
        
        String prompt2 = "What are some attributes of flowers?";
        String response2 = responder.respond(prompt1 + prompt2);

        if (response2.contains("John")) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed!");
            System.out.println("LLM does not know my name");
        }
    }
}