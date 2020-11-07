package ru.geekbrains;


import java.util.Random;
import java.util.Scanner;

public class TwoGame {

   public static void gamePlay1() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        do {
            int answer = random.nextInt(10);
            System.out.println("У Вас 3 попытки");
            for (int i = 3; i > 0; i--) {
                System.out.println("Введите число");
                int num = scanner.nextInt();
                if (num == answer) {
                    System.out.println("Вы угадали число!");
                    break;
                }
                if (num > answer) System.out.println("Вы ввели слишком большое число. ");
                else System.out.println("Вы ввели слишком маленькое число. ");

                if((i-1) > 0) System.out.println("У Вас осталось " + (i-1) + " попыток.");
                else System.out.println("Вы проиграли. Правильный ответ: " + answer);

            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        } while (scanner.nextInt() == 1);
    }


    public static void gamePlay2(){
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
        "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
        "pear", "pepper", "pineapple", "pumpkin", "potato"};

        String player;
        String word = words[random.nextInt(words.length)];
        do {
            System.out.print("Введите слово: ");
            player = scanner.next();
            if (word.equals(player)) {
                System.out.println("Поздравляю!! Вы угадали слово.");
                break;
            }
            for (int i = 0; i < 15; i++)
                if (i < word.length() && i < player.length() && word.charAt(i) == player.charAt(i))
                    System.out.print(word.charAt(i));
                else System.out.print("#");
            System.out.println();
        } while (true);
    }

    public static void main(String[] args) {
        System.out.println("Начнем игру - Угадате число от 0 до 9. ");
        gamePlay1();
        System.out.println("Есть еще одна игра.\nУгадай слово:");
        System.out.println("apple, orange, lemon, banana, apricot, avocado, broccoli, carrot, cherry,\ngarlic, grape, melon, leak, kiwi, mango, mushroom, nut, olive, pea,\npeanut,pear, pepper, pineapple, pumpkin, potato.");
        gamePlay2();

    }
}


