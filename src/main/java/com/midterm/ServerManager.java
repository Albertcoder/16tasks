package main.java.com.midterm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {
    private static ServerSocket serverSocket;
    private static boolean isRunning = false;
    private static Thread serverThread;

    // Задача 5: Старт сервера
    public static void startServer() {
        if (isRunning) {
            AppLogger.log("Сервер уже запущен!");
            return;
        }

        try {
            int port = 8080;
            // Задача 10: Bind (привязка к порту) и открытие сокета
            serverSocket = new ServerSocket(port);
            isRunning = true;
            AppLogger.log("Сервер успешно запущен и привязан (bind) к порту " + port);

            // Запускаем сервер в отдельном потоке, чтобы не блокировать консоль
            serverThread = new Thread(() -> {
                while (isRunning) {
                    try {
                        // Открытие клиентского сокета при подключении
                        Socket clientSocket = serverSocket.accept(); 
                        
                        // Задача 10: Чтение (receive) и отправка (send) данных через потоки сокета
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                        String clientMessage = reader.readLine();
                        AppLogger.log("Получено от клиента (receive): " + clientMessage);

                        writer.println("Эхо от сервера: " + clientMessage); // Отправка (send)

                        clientSocket.close(); // Задача 10: Закрытие клиентского сокета
                    } catch (Exception e) {
                        if (!isRunning) break; // Ожидаемое закрытие при остановке сервера
                    }
                }
            });
            serverThread.start();

        } catch (Exception e) {
            AppLogger.log("Не удалось запустить сервер: " + e.getMessage());
        }
    }

    // Задача 5: Стоп сервера
    public static void stopServer() {
        if (!isRunning) {
            AppLogger.log("Сервер и так не запущен.");
            return;
        }

        try {
            isRunning = false;
            if (serverSocket != null) {
                serverSocket.close(); // Задача 10: Закрытие серверного сокета
            }
            AppLogger.log("Сервер остановлен.");
        } catch (Exception e) {
            AppLogger.log("Ошибка при остановке сервера: " + e.getMessage());
        }
    }
}