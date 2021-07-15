package ru.academits.pereyma.shapes;

import java.util.Objects;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double[] getSidesLengths() {
        return new double[]{
                Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)),
                Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2)),
                Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2)),
        };
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        double[] sidesLengths = this.getSidesLengths();

        double semiPerimeter = (sidesLengths[0] + sidesLengths[1] + sidesLengths[2]) / 2;

        double area = Math.sqrt(semiPerimeter * (semiPerimeter - sidesLengths[0])
                * (semiPerimeter - sidesLengths[1]) * (semiPerimeter - sidesLengths[2]));

        return area;
    }

    @Override
    public double getPerimeter() {
        double[] sidesLengths = this.getSidesLengths();

        return sidesLengths[0] + sidesLengths[1] + sidesLengths[2];
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", x3=" + x3 +
                ", y3=" + y3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.x1, x1) == 0 && Double.compare(triangle.y1, y1) == 0 && Double.compare(triangle.x2, x2) == 0 && Double.compare(triangle.y2, y2) == 0 && Double.compare(triangle.x3, x3) == 0 && Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2, x3, y3);
    }
}
