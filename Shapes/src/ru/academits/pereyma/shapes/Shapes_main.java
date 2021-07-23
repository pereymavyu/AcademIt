package ru.academits.pereyma.shapes;

import java.util.Arrays;

public class Shapes_main {
    private static Shape getMaxAreaShape(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getSecondMaxPerimeterShape(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(2),
                new Circle(2),
                new Square(1),
                new Rectangle(1, 2),
                new Triangle(0, 0, 1, 0, 0, 1),
                new Circle(1),
                new Square(50),
                new Triangle(0, 0, 0, 0, 0, 0)
        };

        System.out.println("Фигура с наибольшей площадью: " + getMaxAreaShape(shapes));
        System.out.println("Фигура со вторым по величине периметром: " + getSecondMaxPerimeterShape(shapes));
    }
}
