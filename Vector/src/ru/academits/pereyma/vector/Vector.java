package ru.academits.pereyma.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("vector dimensionality must be greater than 0");
        }

        components = new double[size];
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("number of vector components must be greater than 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("vector dimensionality must be greater than 0");
        }

        if (size < components.length) {
            throw new IllegalArgumentException("number of vector components must not exceed vector dimensionality");
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

        for (int i = 0; ; i++) {
            sb.append(components[i]);
            if (i == components.length - 1) {
                return sb.append(" }").toString();
            }

            sb.append(", ");
        }
    }

    public Vector sum(Vector vector) {
        if (components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; ++i) {
                components[i] += vector.components[i];
            }
        } else {
            components = Arrays.copyOf(components, vector.components.length);

            for (int i = 0; i < components.length; ++i) {
                components[i] += vector.components[i];
            }
        }

        return this;
    }

    public Vector subtract(Vector vector) {
        if (components.length >= vector.components.length) {
            for (int i = 0; i < vector.components.length; ++i) {
                components[i] -= vector.components[i];
            }
        } else {
            components = Arrays.copyOf(components, components.length);

            for (int i = 0; i < components.length; ++i) {
                components[i] -= vector.components[i];
            }
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
        Vector result = new Vector(Math.max(vector1.components.length, vector2.components.length));

        return result.sum(vector1).sum(vector2);
    }

    public static Vector getSub(Vector vector1, Vector vector2) {
        Vector result = new Vector(Math.max(vector1.components.length, vector2.components.length));

        return result.sum(vector1).sum(vector2.invert());
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); ++i) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}