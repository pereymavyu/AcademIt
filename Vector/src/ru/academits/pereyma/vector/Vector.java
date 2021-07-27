package ru.academits.pereyma.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int size;
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("dimension must be > 0");
        }
        this.size = size;
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("number of vector components must be greater than 0");
        }

        size = components.length;

        this.components = new double[size];
        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public Vector(int size, double[] components) {
        if (size <= 0) {
            throw new IllegalArgumentException("dimension must be > 0");
        }

        if (size < components.length) {
            throw new IllegalArgumentException("number of vector components must not exceed vector dimension");
        }

        this.size = size;
        this.components = new double[size];
        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public Vector(Vector vector) {
        size = vector.size;
        components = new double[vector.components.length];

        System.arraycopy(vector.components, 0, components, 0, components.length);
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");

        for (int i = 0; ; i++) {
            sb.append(this.components[i]);
            if (i == this.components.length - 1) {
                return sb.append(" }").toString();
            }

            sb.append(", ");
        }
    }

    public void sum(Vector vector) {
        if (this.size == vector.size) {
            for (int i = 0; i < this.size; ++i) {
                this.components[i] += vector.components[i];
            }
        }
    }

    public void subtract(Vector vector) {
        if (this.size == vector.size) {
            for (int i = 0; i < this.size; ++i) {
                this.components[i] -= vector.components[i];
            }
        }
    }

    public void multiply(double multiplier) {
        for (int i = 0; i < this.size; ++i) {
            this.components[i] *= multiplier;
        }
    }

    public void invert() {
        this.multiply(-1);
    }

    public double getLength() {
        double squaresSum = 0;
        for (double e : components) {
            squaresSum += e * e;
        }

        return Math.sqrt(squaresSum);
    }

    public double getComponentByIndex(int index) {
        return components[index];
    }

    public void setComponents(int index, double value) {
        components[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
        Vector sum = new Vector(Math.max(vector1.size, vector2.size));


        Vector vector = new Vector(Math.max(vector1.size, vector2.size));


        if (vector1.size == vector2.size) {
            for (int i = 0; i < vector1.size; ++i) {
                vector1.components[i] += vector2.components[i];
            }
        }

        return vector;
    }

    public static Vector getSub(Vector vector1, Vector vector2) {
        if (vector1.size == vector2.size) {
            for (int i = 0; i < vector1.size; ++i) {
                vector1.components[i] -= vector2.components[i];
            }
        }

        Vector vector = new Vector(vector1.size, vector1.components);
        return vector;
    }


    public static Vector getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1.size == vector2.size) {
            for (int i = 0; i < vector1.size; ++i) {
                vector1.components[i] += vector2.components[i];
            }
        }

        Vector vector = new Vector(vector1.size, vector1.components);
        return vector;
    }


}
