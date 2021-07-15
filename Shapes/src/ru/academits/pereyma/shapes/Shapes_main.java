package ru.academits.pereyma.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class Shapes_main {
    private static Shape getMaxAreaShape(Shape[] shapes) {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getArea);
        Arrays.sort(shapes, comparator);
        return shapes[shapes.length - 1];
    }

    private static Shape getMaxAreaShape(Shape[] shapes, int maxIndex) {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getArea);
        Arrays.sort(shapes, 0, maxIndex, comparator);
        return shapes[maxIndex];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(20),
                new Square(7),
                new Rectangle(4, 8),
                new Triangle(0, 0, 50,0, 0,50),
                new Circle(10),
                new Square(50),
        };

        System.out.println("Расположение фигур по возрастанию площади:");

        for(int i = shapes.length - 1; i >= 0; --i){
            System.out.println(getMaxAreaShape(shapes, i));
            System.out.println(shapes[i].getArea());
        }
    }
}