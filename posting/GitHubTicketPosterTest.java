package posting;

public class GitHubTicketPosterTest {
    public static void main(String[] args) {
        String owner = "ahreurink";
        String repo = "ticket-maker";

        GitHubTicketPoster poster = new GitHubTicketPoster(owner, repo);
        
        String summary = "Test GitHub Issue";
        String description = "This is a test issue created via the API";
        String assignee = "ahreurink";
        String[] labels = {"Test"};
        
        poster.post(summary, description, assignee, labels);
    }
} 