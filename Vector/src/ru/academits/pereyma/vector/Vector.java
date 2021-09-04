package ru.academits.pereyma.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0. Current size value is " + size + ".");
        }

        components = new double[size];
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Number of vector components must be greater than 0. Current number of vector components is " + components.length + ".");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0. Current size value is " + size + ".");
        }

        this.components = Arrays.copyOf(components, size);
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");

        for (int i = 0; i < components.length - 1; i++) {
            sb.append(components[i]).append(", ");
        }

        return sb.append(components[components.length - 1]).append(" }").toString();
    }

    public Vector add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] += vector.components[i];
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] -= vector.components[i];
        }

        return this;
    }

    public Vector multiply(double multiplier) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= multiplier;
        }

        return this;
    }

    public Vector invert() {
        return multiply(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double e : components) {
            squaresSum += e * e;
        }

        return Math.sqrt(squaresSum);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double value) {
        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        return result.add(vector2);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);

        return result.subtract(vector2);
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minVectorSize = Math.min(vector1.components.length, vector2.components.length);
        double result = 0;

        for (int i = 0; i < minVectorSize; ++i) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}