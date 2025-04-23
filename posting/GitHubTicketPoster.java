package posting;

public class GitHubTicketPoster extends TicketPoster {
    private final String token;
    private final String owner;
    private final String repo;

    public GitHubTicketPoster(String owner, String repo) {
        this.token = System.getenv("GITHUB_TOKEN");
        this.owner = owner;
        this.repo = repo;
    }

    @Override
    public void post(String title, String body, String assignee, String[] labels) {
        StringBuilder jsonBody = new StringBuilder();
        jsonBody.append("{")
                .append("\"title\": \"").append(title.replace("\"", "\\\"")).append("\",")
                .append("\"body\": \"").append(body.replace("\"", "\\\"")).append("\"");
        
        jsonBody.append(",\"assignee\": \"")
                .append(assignee)
                .append("\"");
        
        jsonBody.append(",\"labels\": [");
        for (int i = 0; i < labels.length; i++) {

            jsonBody.append("\"")
                .append(labels[i])
                .append("\"");
            if (i != labels.length - 1)
                jsonBody.append(",");
        }
        jsonBody.append("]");
        
        jsonBody.append("}");

        System.out.println("Posting to GitHub: " + jsonBody.toString());
        
        client.postTicket(owner, repo, token, jsonBody.toString());
    }
} 