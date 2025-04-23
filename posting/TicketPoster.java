package posting;

import requests.Client;

public abstract class TicketPoster {
    protected Client client = new Client();

    abstract public void post(String title, String body, String assignee, String[] labels);
} 