package posting;

public class JsonBuilder {
    private StringBuilder jsonBody = new StringBuilder();

    public JsonBuilder addField(String fieldName, String fieldValue) {
        if (jsonBody.length() > 0) {
            jsonBody.append(",");
        }
        jsonBody.append("\"")
                .append(fieldName.replace("\"", "\\\""))
                .append("\": \"")
                .append(fieldValue.replace("\"", "\\\""))
                .append("\"");
        return this;
    }

    public JsonBuilder addArray(String fieldName, String[] array) {
        if (jsonBody.length() > 0) {
            jsonBody.append(",");
        }
        jsonBody.append("\"")
                .append(fieldName.replace("\"", "\\\""))
                .append("\": [");
        for (int i = 0; i < array.length; i++) {
            jsonBody.append("\"")
                    .append(array[i].replace("\"", "\\\""))
                    .append("\"");
            if (i != array.length - 1) {
                jsonBody.append(",");
            }
        }
        jsonBody.append("]");
        return this;
    }

    public String toString() {
        return new StringBuilder(jsonBody)
            .insert(0, "{")
            .append("}")
            .toString();
    }
}