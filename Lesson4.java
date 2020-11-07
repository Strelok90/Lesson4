package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    //1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
    //2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
    //3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек.

    // Переменные - параметры игры
    public static final int SIZE_X = 5; //Координата X
    public static final int SIZE_Y = 5; // Координата Y
    public static final int DOTS_TO_WIN = 4; //Кол-во заполн. для победы
    public static final int LINE_LIMIT = 10; //Отступы до игрового поля

    // Константы • X O
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    // Переменные - игровое поле, работа с клавиатурой, случайные числа
    public static char[][] map;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    // Основной метод
    public static void main(String[] args) {

        // Инициализация игрового поля
        initMap();
        // Вывод игрового поля
        printMap();
        // Главный игровой цикл
        while (true){

            // Ход игрока
            humanTurn();
            // Вывод игрового поля
            printMap();
            // Проверка победителя
            if (checkWin(DOT_X)){
                System.out.println("Человечество одержало победу!!!");
                break;
            }
            // Проверка полностью заполненного поля
            if (mapIsFull()){
                System.out.println("Победила дружба");
                break;
            }
            // Ход ИИ
            aiTurn();
            // Вывод игрового поля
            printMap();
            // Проверка победителя
            if (checkWin(DOT_O)){
                System.out.println("Скайнет победил!!!");
                break;
            }
            // Проверка заполненности карты
            if (mapIsFull()){
                System.out.println("Победила дружба");
                break;
            }
        }
        // Игра закончена
        System.out.println("Игра завершена!!!");
        // Закрываем консоль
        scanner.close();
    }

    // Проверка полностью заполненного поля
    public static boolean mapIsFull(){
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (map[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    // Проверка победителя
    public static boolean checkWin(char symbol){
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (checkLine(i, j, 0, 1, symbol)) return true; // проверим линию по х
                if (checkLine(i, j, 1,1, symbol)) return true; // проверим по диагонали х у
                if (checkLine(i, j, 1, 0, symbol)) return true; // проверим линию по у
                if (checkLine(i, j, -1, 1, symbol)) return true; // проверим по диагонали х -у
            }
        }
        return false;
    }
    public static boolean checkLine(int x, int y, int vx, int vy, char symbol){
        int winX = x + (DOTS_TO_WIN - 1) * vx;
        int winY = y + (DOTS_TO_WIN - 1) * vy;
        if (winX < 0 || winY < 0 || winX > SIZE_X - 1 || winY > SIZE_Y - 1) return  false;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int wY = y + i * vy;
            int wX = x + i * vx;
            if (map[wY][wX] != symbol) return false;
        }
        return true;
    }

    // Ход ИИ
    public static void aiTurn(){
        int x, y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (isCellValid(x, y));
        map[y][x] = DOT_O;
    }

    // Предоставляем ход игроку
    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Введите координаты в формате x y в диапазоне [1.." + SIZE_X + "]");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    //Проверка не занятой координаты
    public static boolean isCellValid(int x, int y){
        if (x < 0 || x >= SIZE_X) return true;
        if (y < 0 || y >= SIZE_Y) return true;
        return map[y][x] != DOT_EMPTY;
    }

    // Вывод игрового поля
    public static void printMap(){

    // Сделали отступ
        for (int i = 0; i < LINE_LIMIT; i++) {
            System.out.println();
        }

    // Верхняя "Легенда"
        for (int i = 0; i <= SIZE_X ; i++) {
            System.out.print(i + " ");
        }
        System.out.println("X");

    // Выводим игровое поле
        for (int y = 0; y < SIZE_Y; y++) {
            System.out.print((y + 1) + " ");
            for (int x = 0; x < SIZE_X; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("Y");
        System.out.println();
    }

    // Инициализация игрового поля
    public static void initMap(){
        map = new char[SIZE_X][SIZE_Y];
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}