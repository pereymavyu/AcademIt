package ru.academits.pereyma.matrix;

import ru.academits.pereyma.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsAmount, int columnsAmount) {
        if (rowsAmount <= 0) {
            throw new IllegalArgumentException("Rows amount must be greater than 0");
        }

        if (columnsAmount <= 0) {
            throw new IllegalArgumentException("Columns amount must be greater than 0");
        }

        rows = new Vector[rowsAmount];

        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(columnsAmount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Rows amount must be greater than 0");
        }

        int maxVectorSize = 0;

        for (double[] e : values) {
            if (e.length > maxVectorSize) {
                maxVectorSize = e.length;
            }
        }

        if (maxVectorSize == 0) {
            throw new IllegalArgumentException("Columns amount must be greater than 0");
        }

        rows = new Vector[values.length];

        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(maxVectorSize, values[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Rows amount must be greater than 0");
        }

        int maxVectorSize = 0;

        for (Vector e : vectors) {
            if (e.getSize() > maxVectorSize) {
                maxVectorSize = e.getSize();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; ++i) {
            rows[i] = Vector.getSum(new Vector(maxVectorSize), vectors[i]);
        }
    }

    public int getRowsAmount() {
        return rows.length;
    }

    public int getColumnsAmount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsAmount()) {
            throw new IndexOutOfBoundsException("Index is out of rows range");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsAmount()) {
            throw new IndexOutOfBoundsException("Index is out of rows range");
        }

        if (vector.getSize() != getColumnsAmount()) {
            throw new IllegalArgumentException("Vector size must be equal to columns amount");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Index is out of columns range");
        }

        double[] column = new double[rows.length];

        for (int i = 0; i < column.length; ++i) {
            column[i] = rows[i].getComponent(index);
        }

        return new Vector(column);
    }

    public void transpose() {
        Vector[] resultRows = new Vector[getColumnsAmount()];

        for (int i = 0; i < resultRows.length; ++i) {
            resultRows[i] = getColumn(i);
        }

        rows = resultRows;
    }

    public void multiply(double multiplier) {
        for (Vector e : rows) {
            e.multiply(multiplier);
        }
    }

    public double getValue(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= getRowsAmount() || columnIndex < 0 || columnIndex >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Indexes is out of matrix range");
        }

        return rows[rowIndex].getComponent(columnIndex);
    }

    public void setValue(int rowIndex, int columnIndex, double value) {
        if (rowIndex < 0 || rowIndex >= getRowsAmount() || columnIndex < 0 || columnIndex >= getColumnsAmount()) {
            throw new IndexOutOfBoundsException("Indexes is out of matrix range");
        }

        rows[rowIndex].setComponent(columnIndex, value);
    }

    public double getDeterminant() {
        if (getRowsAmount() != getColumnsAmount()) {
            throw new UnsupportedOperationException("Matrix must be square to get determinant");
        }

        int matrixSize = getRowsAmount();

        if (matrixSize == 1) {
            return getValue(0, 0);
        }

        if (matrixSize == 2) {
            return getValue(0, 0) * getValue(1, 1) - getValue(0, 1) * getValue(1, 0);
        }

        Matrix minor = new Matrix(matrixSize - 1, matrixSize - 1);
        double result = 0;

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 1; j < matrixSize; ++j) {
                for (int k = 0; k < matrixSize; ++k) {
                    if (k < i) {
                        minor.setValue(j - 1, k, getValue(j, k));
                    } else if (k > i) {
                        minor.setValue(j - 1, k - 1, getValue(j, k));
                    }
                }
            }

            result += getValue(0, i) * Math.pow(-1, i) * minor.getDeterminant();
        }

        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('{');

        for (int i = 0; ; i++) {
            sb.append(rows[i]);

            if (i == rows.length - 1) {
                return sb.append('}').toString();
            }

            sb.append(", ");
        }
    }

    public Vector getProduct(Vector vector) {
        if (getColumnsAmount() != vector.getSize()) {
            throw new IllegalArgumentException("Vector size must be equal to matrix columns amount");
        }

        Vector result = new Vector(getRowsAmount());

        for (int i = 0; i < getRowsAmount(); ++i) {
            int temp = 0;

            for (int j = 0; j < getColumnsAmount(); ++j) {
                temp += getValue(i, j) * vector.getComponent(j);
            }

            result.setComponent(i, temp);
        }

        return result;
    }

    public void sum(Matrix matrix) {
        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        for (int i = 0; i < getRowsAmount(); ++i) {
            for (int j = 0; j < getColumnsAmount(); ++j) {
                setValue(i, j, getValue(i, j) + matrix.getValue(i, j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsAmount() != matrix.getRowsAmount() || getColumnsAmount() != matrix.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < rows.length; ++j) {
                setValue(i, j, getValue(i, j) - matrix.getValue(i, j));
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount() || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        Matrix result = new Matrix(matrix1);

        result.sum(matrix2);

        return result;
    }

    public static Matrix getSub(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsAmount() != matrix2.getRowsAmount() || matrix1.getColumnsAmount() != matrix2.getColumnsAmount()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        Matrix result = new Matrix(matrix1);

        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsAmount() != matrix2.getRowsAmount()) {
            throw new IllegalArgumentException("Matrix1 columns number must be equal to matrix2 rows amount");
        }

        Matrix result = new Matrix(matrix1.getRowsAmount(), matrix2.getColumnsAmount());

        for (int i = 0; i < result.getRowsAmount(); ++i) {
            for (int j = 0; j < result.getColumnsAmount(); ++j) {
                double temp = 0;

                for (int k = 0; k < matrix1.getColumnsAmount(); ++k) {
                    temp += matrix1.getValue(i, k) * matrix2.getValue(k, j);
                }

                result.setValue(i, j, temp);
            }
        }

        return result;
    }
}
