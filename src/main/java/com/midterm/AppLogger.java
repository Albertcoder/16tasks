package main.java.com.midterm;

import java.time.LocalTime;

public class AppLogger {
    // Задача 7: Видеть логи
    public static void log(String message) {
        System.out.printf("[%s] [LOG]: %s%n", LocalTime.now(), message);
    }

    // Задача 8: Показать команды CLI и их смысл
    public static void showHelp() {
        System.out.println("\n=== СПИСОК ДОСТУПНЫХ CLI КОМАНД ===");
        System.out.println("  help              - Показать это меню");
        System.out.println("  ping <ip>         - Проверить доступность IP-адреса");
        System.out.println("  server start      - Запустить локальный сервер");
        System.out.println("  server stop       - Остановить локальный сервер");
        System.out.println("  http <url>        - Сделать HTTP-запрос");
        System.out.println("  json              - Демо работы с JSON");
        System.out.println("  exit              - Выход из программы");
        System.out.println("=====================================\n");
    }
}
