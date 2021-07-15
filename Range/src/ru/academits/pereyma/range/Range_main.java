package ru.academits.pereyma.range;

import java.util.Arrays;

public class Range_main {
    public static void main(String[] args) {
        //Первая часть задачи
        double from = 0;
        double to = 10;

        Range range = new Range(from, to);

        double userNumber = 5;

        if (range.isInside(userNumber)) {
            System.out.printf("Число %.2f принадлежит диапазону %s;%n", userNumber, range.toString());
        } else {
            System.out.printf("Число %.2f не принадлежит диапазону %s;%n", userNumber, range.toString());
        }

        System.out.printf("Длина диапазона: %.2f%n", range.getLength());
        System.out.println();

        //Вторая часть задачи
        Range range1 = new Range(0, 5);
        Range range2 = new Range(6, 8);

        Range intersection = range1.getIntersection(range2);
        Range[] union = range1.getUnion(range2);
        Range[] difference = range1.getDifference(range2);

        System.out.printf("Результаты выполнения операций над диапазонами %s и %s: %n", range1.toString(), range2.toString());
        System.out.println("Пересечение: " + intersection);
        System.out.println("Объединение: " + Arrays.toString(union));
        System.out.println("Разность: " + Arrays.toString(difference));
    }
}