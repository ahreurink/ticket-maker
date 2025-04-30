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
        JsonBuilder jsonBuilder = new JsonBuilder()
                                        .addField("title", title)
                                        .addField("body", body);
                                        //.addField("assignee", assignee)
                                        //.addArray("labels", labels);
        System.out.println("Posting to GitHub: " + jsonBuilder.toString());

        client.postTicket(owner, repo, token, jsonBuilder.toString()); //
    }
} 