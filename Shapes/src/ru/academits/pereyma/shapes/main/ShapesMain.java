package ru.academits.pereyma.shapes.main;

import ru.academits.pereyma.shapes.*;

import java.util.Arrays;

public class ShapesMain {
    private static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        if (shapes.length < 2) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(2),
                new Circle(100),
                new Square(1),
                new Rectangle(1, 2),
                new Triangle(0, 0, 100, 0, 0, 100),
                new Circle(1),
                new Square(50),
                new Triangle(0, 0, 0, 0, 0, 0)
        };

        System.out.println("Фигура с наибольшей площадью: " + getMaxAreaShape(shapes));
        System.out.println("Фигура со вторым по величине периметром: " + getSecondMaxPerimeterShape(shapes));
    }
}
