package posting;

public class JsonBuilderTest {
    public static void main(String[] args) {
        testAddField();
        testAddArray();
        testToString();
    }

    private static void testAddField() {
        JsonBuilder builder = new JsonBuilder();
        builder.addField("name", "John Doe");
        String expected = "{\"name\": \"John Doe\"}";
        String actual = builder.toString();

        if (expected.equals(actual)) {
            System.out.println("testAddField passed.");
        } else {
            System.out.println("testAddField failed.");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }

    private static void testAddArray() {
        JsonBuilder builder = new JsonBuilder();
        builder.addArray("items", new String[]{"item1", "item2", "item3"});
        String expected = "{\"items\": [\"item1\",\"item2\",\"item3\"]}";
        String actual = builder.toString();

        if (expected.equals(actual)) {
            System.out.println("testAddArray passed.");
        } else {
            System.out.println("testAddArray failed.");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }

    private static void testToString() {
        JsonBuilder builder = new JsonBuilder();
        builder.addField("key", "value").addArray("list", new String[]{"a", "b", "c"});
        String expected = "{\"key\": \"value\",\"list\": [\"a\",\"b\",\"c\"]}";
        String actual = builder.toString();

        if (expected.equals(actual)) {
            System.out.println("testToString passed.");
        } else {
            System.out.println("testToString failed.");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }
}