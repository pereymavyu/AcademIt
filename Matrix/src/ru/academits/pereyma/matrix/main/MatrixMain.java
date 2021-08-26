package ru.academits.pereyma.matrix.main;

import ru.academits.pereyma.matrix.Matrix;
import ru.academits.pereyma.matrix.Vector;

import java.util.Arrays;

public class MatrixMain {
    public static void main(String[] args) {
        /*
        Проверка конструкторов
         */

        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{1, 2}),
                new Vector(new double[]{5, 6, 7, 8}),
                new Vector(new double[]{9, 10, 11, 12}),
                new Vector(new double[]{13, 0, 0, 0})
        });

        Matrix matrix2 = new Matrix(matrix1);

        Matrix matrix3 = new Matrix(new double[][]{{2, 2, 2, 2},
                new double[]{2, 2, 2, 2},
                new double[]{5, 5, 12, 5}}
        );

        /*
        Проверка нестатик методов
         */

        System.out.println(Arrays.toString(matrix1.getSizes())); // [4, 4]
        System.out.println(matrix1.getRowsNumber()); // 4
        System.out.println(matrix1.getColumnsNumber()); // 4

        System.out.println("===");

        System.out.println(matrix1);
        System.out.println(matrix1.getRow(0));
        matrix1.setRow(0, new Vector(new double[]{2, 2, 0, 0}));
        System.out.println(matrix1.getRow(0));
        System.out.println(matrix1.getColumn(3));

        System.out.println("===");

        System.out.println(matrix3);
        matrix3.transpose();
        System.out.println(matrix3);
        matrix3.multiply(2);
        System.out.println(matrix3);

        System.out.println("===");

        System.out.println(matrix1.getDeterminant());
        System.out.println(matrix3.getVectorProduct(new Vector(new double[]{2, 2, 2})));

        System.out.println("===");

        System.out.println(matrix2);
        System.out.println(matrix1);
        matrix2.sum(matrix1);
        System.out.println(matrix2);
        matrix2.subtract(matrix1);
        System.out.println(matrix2);

        System.out.println("===");

        /*
        Проверка статик методов
         */

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(Matrix.getSum(matrix1, matrix2));
        System.out.println(Matrix.getSub(matrix1, matrix2));

        System.out.println("===");

        Matrix matrix4 = new Matrix(new Vector[]{new Vector(new double[]{1, 2}),
                new Vector(new double[]{5, 6, 7, 8}),
                new Vector(new double[]{9, 10, 11, 12})
        });
        Matrix matrix5 = new Matrix(matrix4);
        matrix5.transpose();

        System.out.println(Matrix.getProduct(matrix4, matrix5));
    }
}