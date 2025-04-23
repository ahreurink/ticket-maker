package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import llm.Responder;

import posting.TicketPoster;
import posting.GitHubTicketPoster;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

import java.time.LocalDateTime;

public class Main {
    static final int TIMEOUT_SECONDS = 10;
    static String unescape(String sentence) {
        return sentence.replaceAll("\\\\n", "\n")
                       .replaceAll("\\\\t", "\t")
                       .replaceAll("\\\\\"", "\"");
    }

    static String cleanMarkdownBlock(String sentence) {
        return sentence.substring(sentence.indexOf("```markdown") + 11, sentence.lastIndexOf("```"));
    }

    static void printLoading(Future<String> responseFuture) throws InterruptedException {
        LocalDateTime timeOut = LocalDateTime.now().plusSeconds(TIMEOUT_SECONDS);
        Thread.sleep(100);
        while(!responseFuture.isDone()) {
            if(LocalDateTime.now().isAfter(timeOut)) {
                throw new RuntimeException("Timeout while waiting for response (" + TIMEOUT_SECONDS + " seconds)");
            }
            System.out.print(".");
            Thread.sleep(200);
            System.out.print(".");
            Thread.sleep(200);
            System.out.print(".");
            Thread.sleep(200);
            System.out.print("\b\b\b   \b\b\b");
            Thread.sleep(200);
        }
    }
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newSingleThreadExecutor()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Prompt>");
            String prompt = reader.readLine();
            Future<String> responseFuture = service.submit(() -> new Responder().respond(prompt));
            printLoading(responseFuture);

            String response = responseFuture.get();
            System.out.println(cleanMarkdownBlock(unescape(response)));
            System.out.print("Want to post to GitHub? (y/n) ");
            String check = reader.readLine();
            if(check.equals("y")) {
                String[] labels = {"test"};
                new GitHubTicketPoster("ahreurink", "ticket-maker").post("Default title", response, "ahreurink", labels);
            }
        } catch (IOException | ExecutionException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting for response");
        }
    }
}