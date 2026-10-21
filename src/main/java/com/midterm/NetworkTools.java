package main.java.com.midterm;

import java.net.InetAddress;
import java.net.Socket;

public class NetworkTools {
    // Задача 9: Подключение к IP, ping, проверка портов и протоколов
    public static void pingHost(String host) {
        try {
            AppLogger.log("Пытаемся достучаться до хоста: " + host);
            InetAddress address = InetAddress.getByName(host);
            
            // Проверка доступности (аналог ping на уровне Java, таймаут 3 секунды)
            boolean reachable = address.isReachable(3000);
            System.out.printf("Хост %s (%s) доступен (reachable): %b%n", 
                address.getHostName(), address.getHostAddress(), reachable);

            // Проверка конкретного порта (например, стандартный порт 80 для HTTP)
            int targetPort = 80;
            try (Socket socket = new Socket(address, targetPort)) {
                // Если сокет создался, значит порт открыт и протокол TCP работает
                System.out.printf("Порт %d на хосте %s открыт (протокол TCP активен).%n", targetPort, host);
            } catch (Exception e) {
                System.out.printf("Порт %d закрыт или недоступен: %s%n", targetPort, e.getMessage());
            }

        } catch (Exception e) {
            AppLogger.log("Ошибка сети: " + e.getMessage());
        }
    }
}