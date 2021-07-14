package ru.academits.pereyma.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector {
    private int dimension;
    private double[] components;

    public Vector(int dimension) {
        this.dimension = dimension;
    }

    public Vector(double[] components) {
        this.components = new double[components.length];
        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public Vector(int dimension, double[] components) {
        this.dimension = dimension;
        if (dimension < components.length)

            this.components = components;
    }

    public Vector(Vector vector) {
        this.dimension = vector.dimension;
        this.components = new double[vector.components.length];

        System.arraycopy(vector.components, 0, this.components, 0, vector.components.length);
    }

    public int getSize() {
        return this.dimension;
    }

    @Override
    public String toString() {
        return "ru.academits.pereyma.vector.Vector{" +
                "components=" + Arrays.toString(components) +
                '}';
    }

    public void sum(Vector vector) {
        if (this.dimension == vector.dimension) {
            for (int i = 0; i < this.dimension; ++i) {
                this.components[i] += vector.components[i];
            }
        }
    }

    public void subtract(Vector vector) {
        if (this.dimension == vector.dimension) {
            for (int i = 0; i < this.dimension; ++i) {
                this.components[i] -= vector.components[i];
            }
        }
    }

    public void multiply(double multiplier) {
        for (int i = 0; i < this.dimension; ++i) {
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
        return dimension == vector.dimension && Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dimension);
        result = 31 * result + Arrays.hashCode(components);
        return result;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector sum = new Vector(Math.max(vector1.dimension, vector2.dimension));


        Vector vector = new Vector(Math.max(vector1.dimension, vector2.dimension));


        if (vector1.dimension == vector2.dimension) {
            for (int i = 0; i < vector1.dimension; ++i) {
                vector1.components[i] += vector2.components[i];
            }
        }

        return vector;
    }

    public static Vector getSub(Vector vector1, Vector vector2) {
        if (vector1.dimension == vector2.dimension) {
            for (int i = 0; i < vector1.dimension; ++i) {
                vector1.components[i] -= vector2.components[i];
            }
        }

        Vector vector = new Vector(vector1.dimension, vector1.components);
        return vector;
    }


    public static Vector getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1.dimension == vector2.dimension) {
            for (int i = 0; i < vector1.dimension; ++i) {
                vector1.components[i] += vector2.components[i];
            }
        }

        Vector vector = new Vector(vector1.dimension, vector1.components);
        return vector;
    }


}
