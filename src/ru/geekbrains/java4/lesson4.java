package ru.geekbrains.java4;

import java.util.Random;
import java.util.Scanner;

public class lesson4 {
    private static char[][] map = new char[5][5]; // игровое поле
    private static Scanner sc = new Scanner(System.in); // Scanner для чтения консоли
    private static Random rand = new Random(); // генератор случайных чисел

    public static void initMap() { // инициализируем массив map(игровое поле)
        for (int i = 0; i < 5; i++) { // двойным циклом проходимся по всему массиву
            for (int j = 0; j < 5; j++) {
                map[i][j] = '*'; // и заполняем каждую ячейку *
            }
        }
    }

    public static void printMap() { // выводим игровое поле в консоль
        System.out.println("  1 2 3 4 5"); // первая строка с координатами
        for (int i = 0; i < 5; i++) { // начинаем печатать поле
            System.out.print((i + 1) + " "); // ставим номер строки 1-5
            for (int j = 0; j < 5; j++) { // начинаем печатать строку
                System.out.print(map[i][j] + " "); // посимвольно печатаем содержимое каждой ячейки поля
            }
            System.out.println(); // после распечатки строки, делаем перевод каретки
        }
        System.out.println(); // делаем дополнительный перевод строки
    }

    public static void main(String[] args) {
        initMap(); // инициализируем поле
        printMap(); // печатаем в консоль

        while (true) { // запускаем игровой цикл
            humanTurn(); // ход человека
            printMap(); // печать поля
            if (isCheckWin('X')) { // проверка победы человека
                System.out.println("Победил игрок");
                break;
            }
            if (isMapFull()) break; // если поле заполнилось, завершаем игру
            aiTurn(); // ход компьютера
            printMap(); // печать поля
            if (isCheckWin('O')) { // проверка победы компьютера
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) break;
        }
        System.out.println("Game Over");
    }

    public static boolean isCheckWin(char c) {
        int countV;
        int countH;
        int countDiagonalA = 0;
        int countDiagonalB = 0;
        for (int i = 0; i <= 5 - 1; i++) {
            countH = 0;
            countV = 0;
            for (int j = 0; j <= 5 - 1; j++) {
                //tested horizontal check
                if (map[i][j] == c) {
                    countH++;
                    if (countH == 5) return true;
                }

                //tested vertical check
                if (map[j][i] == c) {
                    countV++;
                    if (countV == 5) return true;
                }
            }

            // tested diagonal A "\" check
            if (map[i][i] == c) {
                countDiagonalA++;
                if (countDiagonalA == 5) return true;
            } else countDiagonalA = 0;

            // tested diagonal B "/" check
            if (map[i][5 - 1 - i] == c) {
                countDiagonalB++;
                if (countDiagonalB == 5) return true;
            } else countDiagonalB = 0;
        }
        return false;
    }

    private static void humanTurn() { // ход человека
        int x, y;
        do {
            System.out.println("Введите координаты в формате Х и У");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;


        } while (!isCellEmpty(x, y)); // пользователь вводит координаты до тех пор, пока не укажет на свободную ячейку
        map[y][x] = 'X'; // после чего ставим туда Х
    }

    private static boolean isMapFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == '*') return false; // если на поле нашли хотя бы одну *, значит поле еще не заполнилось
            }
        }
        return true;
    }

    private static void aiTurn() {
        int x, y;
        do { // компьютер пытается случайно выбрать незанятое поле для хода
            x = rand.nextInt(5);
            y = rand.nextInt(5);
        } while (!isCellEmpty(x, y));
        map[y][x] = 'O'; // как только ячейка найдена, ставим туда О
    }

    public static boolean isCellEmpty(int x, int y) { // проверка является ли ячейка свободной
        if (x < 0 || x > 4 || y < 0 || y > 4) return false; // если указаны неверные координаты, считаем что ячейка условно занята
        if (map[y][x] != '*') return false; // если в ячейке не *, значит занята
        return true; // ячейка свободна
    }
}
