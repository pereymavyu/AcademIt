package ru.academits.pereyma.range.main;

import ru.academits.pereyma.range.Range;

import java.util.Arrays;

public class RangeMain {
    public static void main(String[] args) {
        //Первая часть задачи
        double from = 0;
        double to = 10;

        Range range1 = new Range(from, to);

        double userNumber = 5;

        if (range1.isInside(userNumber)) {
            System.out.printf("Число %.2f принадлежит диапазону %s;%n", userNumber, range1.toString());
        } else {
            System.out.printf("Число %.2f не принадлежит диапазону %s;%n", userNumber, range1.toString());
        }

        System.out.printf("Длина диапазона: %.2f%n", range1.getLength());
        System.out.println();

        //Вторая часть задачи
        Range range2 = new Range(0, 7);
        Range range3 = new Range(1, 6);

        Range intersection = range2.getIntersection(range3);
        Range[] union = range2.getUnion(range3);
        Range[] difference = range2.getDifference(range3);

        System.out.printf("Результаты выполнения операций над диапазонами %s и %s: %n", range2, range3);
        System.out.println("Пересечение: " + intersection);
        System.out.println("Объединение: " + Arrays.toString(union));
        System.out.println("Разность: " + Arrays.toString(difference));
    }
}