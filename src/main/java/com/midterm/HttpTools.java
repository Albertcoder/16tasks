package main.java.com.midterm;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTools {
    // Задача 11: Сделать HTTP запрос
    public static void makeHttpRequest(String urlString) {
        try {
            AppLogger.log("Отправка HTTP GET запроса на: " + urlString);
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Задача 12: Получить HTTP ответ и отделить заголовки от тела
            System.out.println("\n--- HTTP ЗАГОЛОВКИ (HEADERS) ---");
            response.headers().map().forEach((key, values) -> {
                System.out.println(key + ": " + String.join(", ", values));
            });

            System.out.println("\n--- HTTP ТЕЛО (BODY) ---");
            System.out.println(response.body());
            System.out.println("--------------------------------\n");

        } catch (Exception e) {
            AppLogger.log("Ошибка HTTP запроса: " + e.getMessage());
        }
    }
}