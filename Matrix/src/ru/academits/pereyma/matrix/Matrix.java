package ru.academits.pereyma.matrix;

public class Matrix {
    Vector[] rows;

    public Matrix(int rowsNumber, int columnsNumber) {
        if (rowsNumber <= 0) {
            throw new IllegalArgumentException("Rows number must be greater than 0");
        }

        if (columnsNumber <= 0) {
            throw new IllegalArgumentException("Columns number must be greater than 0");
        }

        rows = new Vector[rowsNumber];

        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(columnsNumber);
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
            throw new IllegalArgumentException("Rows number must be greater than 0");
        }

        int maxVectorSize = 0;

        for (double[] e : values) {
            if (e.length > maxVectorSize) {
                maxVectorSize = e.length;
            }
        }

        if (maxVectorSize == 0) {
            throw new IllegalArgumentException("Columns number must be greater than 0");
        }

        rows = new Vector[values.length];

        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(maxVectorSize, values[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Rows number must be greater than 0");
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

    public int getRowsNumber() {
        return rows.length;
    }

    public int getColumnsNumber() {
        return rows[0].getSize();
    }

    public int[] getSizes() {
        return new int[]{rows.length, rows[0].getSize()};
    }

    public Vector getRow(int index) {
        if (index < 0 || index > this.getRowsNumber()) {
            throw new IndexOutOfBoundsException("Index is out of rows range");
        }

        return rows[index];
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsNumber()) {
            throw new IndexOutOfBoundsException("Index is out of rows range");
        }

        if (vector.getSize() != getColumnsNumber()) {
            throw new IllegalArgumentException("Vector size must be equal to columns number");
        }

        rows[index] = vector;
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsNumber()) {
            throw new IndexOutOfBoundsException("Index is out of columns range");
        }

        double[] column = new double[rows.length];

        for (int i = 0; i < column.length; ++i) {
            column[i] = rows[i].getComponent(index);
        }

        return new Vector(column);
    }

    public void transpose() {
        Vector[] resultRows = new Vector[getColumnsNumber()];

        for (int i = 0; i < resultRows.length; ++i) {
            resultRows[i] = new Vector(getColumn(i));
        }

        rows = resultRows;
    }

    public void multiply(double multiplier) {
        for (Vector e : rows) {
            e.multiply(multiplier);
        }
    }

    public double getValue(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= getRowsNumber() || columnIndex < 0 || columnIndex >= getColumnsNumber()) {
            throw new IndexOutOfBoundsException("Indexes is out of matrix range");
        }

        return rows[rowIndex].getComponent(columnIndex);
    }

    public void setValue(int rowIndex, int columnIndex, double value) {
        if (rowIndex < 0 || rowIndex >= getRowsNumber() || columnIndex < 0 || columnIndex >= getColumnsNumber()) {
            throw new IndexOutOfBoundsException("Indexes is out of matrix range");
        }
        rows[rowIndex].setComponent(columnIndex, value);
    }

    public double getDeterminant() {
        if (getRowsNumber() != getColumnsNumber()) {
            throw new IllegalArgumentException("Matrix must be square to get determinant");
        }

        int matrixSize = getRowsNumber();

        if (matrixSize == 1) {
            return getValue(0, 0);
        }

        if (matrixSize == 2) {
            return getValue(0, 0) * getValue(1, 1) - getValue(0, 1) * getValue(1, 0);
        }

        Matrix temp = new Matrix(matrixSize - 1, matrixSize - 1);
        double result = 0;

        for (int i = 0; i < matrixSize; ++i) {
            for (int j = 1; j < matrixSize; ++j) {
                for (int k = 0; k < matrixSize; ++k) {
                    if (k < i) {
                        temp.setValue(j - 1, k, getValue(j, k));
                    } else if (k > i) {
                        temp.setValue(j - 1, k - 1, getValue(j, k));
                    }
                }
            }

            result += getValue(0, i) * Math.pow(-1, i) * temp.getDeterminant();
        }

        return result;
    }


    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        b.append('{');

        for (int i = 0; ; i++) {
            b.append(rows[i]);
            if (i == rows.length - 1)
                return b.append('}').toString();
            b.append(", ");
        }
    }

    public Vector getVectorProduct(Vector vector) {
        if (getColumnsNumber() != vector.getSize()) {
            throw new IllegalArgumentException("Vector size must be equal to matrix columns number");
        }

        Vector result = new Vector(getRowsNumber());

        for (int i = 0; i < getRowsNumber(); ++i) {
            int temp = 0;

            for (int j = 0; j < getColumnsNumber(); ++j) {
                temp += getValue(i, j) * vector.getComponent(j);
            }

            result.setComponent(i, temp);
        }

        return result;
    }

    public void sum(Matrix matrix) {
        if (getRowsNumber() != matrix.getRowsNumber() || getColumnsNumber() != matrix.getColumnsNumber()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        for (int i = 0; i < getRowsNumber(); ++i) {
            for (int j = 0; j < getColumnsNumber(); ++j) {
                setValue(i, j, getValue(i, j) + matrix.getValue(i, j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsNumber() != matrix.getRowsNumber() || getColumnsNumber() != matrix.getColumnsNumber()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        for (int i = 0; i < rows.length; ++i) {
            for (int j = 0; j < rows.length; ++j) {
                setValue(i, j, getValue(i, j) - matrix.getValue(i, j));
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber() || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        Matrix result = new Matrix(matrix1);

        result.sum(matrix2);

        return result;
    }

    public static Matrix getSub(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber() || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new IllegalArgumentException("Matrices must have the same size");
        }

        Matrix result = new Matrix(matrix1);

        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.getRowsNumber()) {
            throw new IllegalArgumentException("Matrix1 columns number must be equal to matrix2 rows number");
        }

        Matrix result = new Matrix(matrix1.getRowsNumber(), matrix2.getColumnsNumber());

        for (int i = 0; i < result.getRowsNumber(); ++i) {
            for (int j = 0; j < result.getColumnsNumber(); ++j) {
                double temp = 0;

                for (int k = 0; k < matrix1.getColumnsNumber(); ++k) {
                    temp += matrix1.getValue(i, k) * matrix2.getValue(k, j);
                }

                result.setValue(i, j, temp);
            }
        }

        return result;
    }
}
