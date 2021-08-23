package ru.academits.pereyma.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int size;
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("vector dimensionality must be greater than 0");
        }

        this.size = size;
        components = new double[size];
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("number of vector components must be greater than 0");
        }

        size = components.length;

        this.components = Arrays.copyOf(components, size);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("vector dimensionality must be greater than 0");
        }

        if (size < components.length) {
            throw new IllegalArgumentException("number of vector components must not exceed vector dimensionality");
        }

        this.size = size;
        this.components = Arrays.copyOf(components, size);
    }

    public Vector(Vector vector) {
        size = vector.size;
        components = Arrays.copyOf(vector.components, size);
    }

    public int getSize() {
        return size;
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

    public void sum(Vector vector) {
        if (size >= vector.size) {
            for (int i = 0; i < vector.size; ++i) {
                components[i] += vector.components[i];
            }
        } else {
            size = vector.size;
            components = Arrays.copyOf(components, size);

            for (int i = 0; i < size; ++i) {
                components[i] += vector.components[i];
            }
        }
    }

    public void subtract(Vector vector) {
        if (size >= vector.size) {
            for (int i = 0; i < vector.size; ++i) {
                components[i] -= vector.components[i];
            }
        } else {
            size = vector.size;
            components = Arrays.copyOf(components, size);

            for (int i = 0; i < size; ++i) {
                components[i] -= vector.components[i];
            }
        }
    }

    public void multiply(double multiplier) {
        for (int i = 0; i < size; ++i) {
            components[i] *= multiplier;
        }
    }

    public void invert() {
        multiply(-1);
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
        return size == vector.size && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(components);

        return result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(Math.max(vector1.size, vector2.size));

        result.sum(vector1);
        result.sum(vector2);

        return result;
    }

    public static Vector getSub(Vector vector1, Vector vector2) {
        Vector result = new Vector(Math.max(vector1.size, vector2.size));

        result.sum(vector1);

        for (int i = 0; i < vector2.size; ++i) {
            result.components[i] -= vector2.components[i];
        }

        return result;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.size, vector2.size); ++i) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}