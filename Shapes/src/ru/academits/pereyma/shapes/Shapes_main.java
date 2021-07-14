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
        Shape[] shapes = new Shape[5];

        shapes[0] = new Circle(20);
        shapes[1] = new Square(7);
        shapes[2] = new Rectangle(4, 8);
        shapes[3] = new Rectangle(4, 8);
        shapes[4] = new Circle(50);

        for(int i = shapes.length - 1; i >= 0; --i){
            System.out.println(getMaxAreaShape(shapes, i));
            System.out.println(shapes[i].getArea());
        }
    }
}
