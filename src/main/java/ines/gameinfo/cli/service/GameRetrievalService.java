package ines.gameinfo.cli.service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GameRetrievalService {

    public static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public String getGamesFor(String userId){
        System.out.println(PS_URI.formatted(userId));
        HttpRequest request =HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(userId)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not call the API");
        }
    }
}
