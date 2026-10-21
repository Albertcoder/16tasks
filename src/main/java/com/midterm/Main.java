package main.java.com.midterm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppLogger.showHelp();

        // Цикл для интерактивного ввода команд
        while (true) {
            System.out.print("user@midterm-cli> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                AppLogger.log("Завершение работы программы.");
                break;
            } else if (input.equalsIgnoreCase("help")) {
                AppLogger.showHelp();
            } else if (input.startsWith("ping ")) {
                String ip = input.substring(5);
                NetworkTools.pingHost(ip);
            } else if (input.equalsIgnoreCase("server start")) {
                ServerManager.startServer();
            } else if (input.equalsIgnoreCase("server stop")) {
                ServerManager.stopServer();
            } else if (input.startsWith("http ")) {
                String url = input.substring(5);
                HttpTools.makeHttpRequest(url);
            } else if (input.equalsIgnoreCase("json")) {
                JsonTools.demonstrateJson();
            } else if (input.isEmpty()) {
                continue;
            } else {
                // Форматированная строка (Задание 16)
                String errorMsg = String.format("Неизвестная команда: '%s'. Введите 'help' для справки.", input);
                AppLogger.log(errorMsg);
            }
        }
        scanner.close();
    }
}