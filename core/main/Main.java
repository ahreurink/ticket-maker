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

    static String cleanMarkdownBlock(String sentence) {
        return sentence.substring(sentence.indexOf("```markdown") + 11, sentence.lastIndexOf("```"));
    }
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Prompt>");
            String prompt = reader.readLine();
            String response = new Responder().respond(prompt);
            System.out.println("\nResponse:\n");
            System.out.println(cleanMarkdownBlock(unescape(response)));
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
}