package llm;

public class ResponderTest {
    public static void main(String[] args) {
        Responder responder = new Responder();
        String prompt1 = "Show the user a list of 5 flowers";
        String response1 = responder.createResponse(prompt1);

        if (response1.contains("```markdown")) {
            System.out.println("Test passed!");
            System.exit(0);
        } else {
            System.out.println("Test failed!");
            System.out.println("LLM does does not respond in markdown");
            System.exit(1);
        }
    }
}