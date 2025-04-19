package posting;

import http.Client;
import java.util.Optional;

abstract class TicketPoster {
    protected Client client = new Client();

    abstract public void post(String title, String body, String assignee, String[] labels);
} 