package ines.gameinfo.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GameRetrievalService {

    public static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();
    public List<APICourse> getGamesFor(String userId){
        System.out.println(PS_URI.formatted(userId));
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(userId)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch (response.statusCode()){
                case 200 -> toApiCourses(response);
                case 404 -> List.of();
                default -> throw new RuntimeException("API failed with status code " + response.statusCode());
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Could not call the API");
        }
    }

    private List<APICourse> toApiCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType = OBJECT_MAPPER.getTypeFactory()
                        .constructCollectionType(List.class, APICourse.class);
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
}
