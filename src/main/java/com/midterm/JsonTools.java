package main.java.com.midterm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class JsonTools {
    public static void demonstrateJson() {
        Gson gson = new Gson();

        // Задача 13: Изучить JSON, сделать JSON из словаря (Map) и наоборот
        Map<String, Object> myMap = new HashMap<>();
        myMap.put("student", "Ivan");
        myMap.put("course", 3);
        myMap.put("passed", true);

        // Словарь в JSON строку
        String jsonString = gson.toJson(myMap);
        System.out.println("[JSON] Словарь превращен в JSON: " + jsonString);

        // JSON строка обратно в словарь (Map)
        Map<?, ?> parsedBackMap = gson.fromJson(jsonString, Map.class);
        System.out.println("[JSON] Извлечено из словаря после парсинга: студент = " + parsedBackMap.get("student"));

        // Задача 14: Использование открытого API (Open API)
        try {
            String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";
            AppLogger.log("Запрос к открытому API: " + apiUrl);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Задача 15: Техники парсинга и извлечения данных из ответа
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            
            int id = jsonObject.get("id").getAsInt();
            String title = jsonObject.get("title").getAsString();
            boolean completed = jsonObject.get("completed").getAsBoolean();

            System.out.println("\n--- РЕЗУЛЬТАТ ПАРСИНГА ОТКРЫТОГО API ---");
            System.out.printf("ID задачи: %d%n", id);
            System.out.printf("Заголовок: %s%n", title);
            System.out.printf("Статус выполнения: %b%n", completed);
            System.out.println("---------------------------------------\n");

        } catch (Exception e) {
            AppLogger.log("Ошибка при работе с Open API: " + e.getMessage());
        }
    }
}