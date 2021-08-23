package ru.academits.pereyma.vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector1 = new Vector(10);

        System.out.println(vector1.toString());

        Vector vector2 = new Vector(new double[]{0});

        System.out.println(vector2.toString());

        Vector vector3 = new Vector(7, new double[]{1, 3, 7});

        System.out.println(vector3.toString());

        Vector vector4 = new Vector(vector3);

        System.out.println(vector4.toString());

        System.out.println(vector4.getSize());

        vector4.multiply(6);
        System.out.println(vector4);

        vector4.invert();
        System.out.println(vector4);

        System.out.println(vector4.getLength());

        System.out.println(vector4.getComponent(2));

        vector4.setComponent(-1, 17);
        System.out.println(vector4.getComponent(2));
    }
}
