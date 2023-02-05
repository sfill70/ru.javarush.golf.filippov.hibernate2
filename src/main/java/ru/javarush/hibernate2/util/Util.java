package ru.javarush.hibernate2.util;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    final static String[] firstNameFilm = {"Что", "Где", "Когда", "Почему", "Зачем", "Откуда", "Куда", "Далее", "Затем"};
    final static String[] secondNameFilm = {" случилается", " произходит", " идет", " приходит", " возникает", " наступает", " возвращается", " кругом", " навсегда"};
    final static String[] lastNameFilm = {" кровь", " убийство", " герой", " смерть", " любовь", " жизнь", " драмма", " ужас", " трагедия", " секс", " хаос", " апокалипсис"};

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

    public static void main(String[] args) {
        double co = Math.random();
        int x = (int)(co*1000000000);
        double y = (co*10000000000L);
        System.out.println(co);
        System.out.println(x);
        System.out.println(String.valueOf(y));
    }
}
