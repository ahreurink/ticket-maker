package responder;

public class ResponderTest {
    public static void main(String[] args) {
        Responder responder = new Responder();
        String input = "Hello, world!";
        String expected = "Response to: Hello, world!";
        String actual = responder.respond(input);

        if (expected.equals(actual)) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed!");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }
}