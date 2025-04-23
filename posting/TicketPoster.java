package posting;

import requests.Client;

abstract class TicketPoster {
    protected Client client = new Client();

    abstract public void post(String title, String body, String assignee, String[] labels);
} 