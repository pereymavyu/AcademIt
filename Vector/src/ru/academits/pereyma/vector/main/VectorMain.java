package ru.academits.pereyma.vector.main;

import ru.academits.pereyma.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        /*
        Проверка конструкторов
         */

        Vector vector1 = new Vector(4);
        System.out.println("vector1(4) = " + vector1);

        Vector vector2 = new Vector(new double[]{1, 2, 3});
        System.out.println("vector2([1, 2, 3]) = " + vector2);

        Vector vector3 = new Vector(5, new double[]{1, 2, 3});
        System.out.println("vector3(5, [1, 2, 3]) = " + vector3);

        Vector vector4 = new Vector(vector3);
        System.out.println("vector4(vector3) = " + vector4);

        System.out.println();

        /*
        Проверка метода getSize();
         */

        System.out.println("Размерность vector4 = " + vector4.getSize());

        System.out.println();

        /*
        Проверка нестатических методов;
         */

        System.out.println(vector1.add(vector2)); // [1, 2, 3, 0]

        System.out.println(vector4.equals(vector3)); // true

        System.out.println(vector4.subtract(vector3.invert().multiply(-1))); // [0, 0, 0, 0, 0]

        Vector vector5 = new Vector(new double[]{0, 0, 2});

        System.out.println(vector5.getLength()); // 2
        System.out.println(vector5.getComponent(2)); // 2

        vector5.setComponent(0, 2);
        System.out.println(vector5.getComponent(0)); // 2

        System.out.println();

        /*
        Проверка статических методов;
         */

        System.out.println(Vector.getSum(
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{1, 2, 3, 0})
        )); // [2, 4, 6, 0]

        System.out.println(Vector.getDifference(
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{1, 2, 4, 0})
        )); // [0, 0, -1, 0]

        System.out.println(Vector.getScalarProduct(
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{1, 2, 3, 0})
        )); // 14
    }
}
