package main.java.com.midterm;

public class CheckLibrary {
    public static void main(String[] args) {
        try {
            Class.forName("com.google.gson.Gson");
            System.out.println("Библиотека Gson найдена и успешно подключена!");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: класс Gson не найден.");
        }
    }
}