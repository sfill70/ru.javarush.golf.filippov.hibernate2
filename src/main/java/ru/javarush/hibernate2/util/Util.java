package ru.javarush.hibernate2.util;

import java.util.concurrent.ThreadLocalRandom;

public class Util {
    final static String[] firstNameFilm = {"Что", "Где", "Когда", "Почему", "Зачем", "Откуда", "Куда", "Далее", "Затем"};
    final static String[] secondNameFilm = {" случилается", " произходит", " идет", " приходит", " возникает", " наступает", " возвращается", " кругом", " навсегда"};
    final static String[] lastNameFilm = {" кровь", " убийство", " герой", " смерть", " любовь", " жизнь", " драмма", " ужас", " трагедия", " секс", " хаос", " апокалипсис"};
    final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    final static String[] endings = {"@gmail.com", "@amazon.com", "@rambler.ru", "@yandex.ru"};

    public Util() {
    }

    public static String getFilmName() {
        int randomElementIndex
                = ThreadLocalRandom.current().nextInt(firstNameFilm.length);
        String nameFilm = firstNameFilm[randomElementIndex];
        randomElementIndex = ThreadLocalRandom.current().nextInt(secondNameFilm.length);
        nameFilm = nameFilm + secondNameFilm[randomElementIndex];
        randomElementIndex = ThreadLocalRandom.current().nextInt(lastNameFilm.length);
        nameFilm = nameFilm + lastNameFilm[randomElementIndex];
        return nameFilm;
    }

    public static String randomString(int lenght, boolean upCase) {
        StringBuilder randomString = new StringBuilder("" + alphabet.charAt(ThreadLocalRandom.current()
                .nextInt(0, 26)));

        if (upCase) {
            randomString = new StringBuilder(randomString.toString().toUpperCase());
        }

        for (int i = 0; i < lenght; i++) {
            randomString.append(alphabet.charAt(ThreadLocalRandom.current().nextInt(0, 26)));
        }
        return randomString.toString();
    }

    public static String randomEmail() {
        return randomString(ThreadLocalRandom.current().nextInt(5, 10), false)
                + endings[ThreadLocalRandom.current().nextInt(0, 4)];
    }

    public static String randomAddress() {
        return ThreadLocalRandom.current().nextInt(1, 200) + " "
                + randomString(ThreadLocalRandom.current().nextInt(5, 9), true);
    }

    public static String randomCity() {
        return "" + randomString(ThreadLocalRandom.current().nextInt(4, 9), true);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(endings[ThreadLocalRandom.current().nextInt(0, 4)]);
            System.out.println(("" + alphabet.charAt(ThreadLocalRandom.current().nextInt(0, 26))).toUpperCase());
        }
    }
}
