package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import responder.Responder;

import java.io.IOException;

public class Main {
    static String unescape(String sentence) {
        return sentence.replaceAll("\\\\n", "\n")
                       .replaceAll("\\\\t", "\t");
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Prompt>");
            String prompt = reader.readLine();
            String response = new Responder().respond(prompt);
            System.out.println("\nResponse:\n");
            System.out.println(unescape(response));
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
}