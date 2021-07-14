package ru.academits.pereyma.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (this.getFrom() >= range.getTo() || this.getTo() <= range.getFrom()) {
            return null;
        }

        Range intersection = new Range(this.getFrom(), this.getTo());

        if (this.isInside(range.getFrom())) {
            intersection.setFrom(range.getFrom());
        }

        if (this.isInside(range.getTo())) {
            intersection.setTo(range.getTo());
        }

        return intersection;
    }

    public Range[] getUnion(Range range) {
        if (this.getFrom() > range.getTo()) {
            return new Range[]{new Range(range.getFrom(), range.getTo()), new Range(this.getFrom(), this.getTo())};
        }

        if (this.getTo() < range.getFrom()) {
            return new Range[]{new Range(this.getFrom(), this.getTo()), new Range(range.getFrom(), range.getTo())};
        }

        Range union = new Range(this.getFrom(), this.getTo());

        if (range.isInside(this.getFrom())) {
            union.setFrom(range.getFrom());
        }

        if (range.isInside(this.getTo())) {
            union.setTo(range.getTo());
        }

        return new Range[]{union};
    }

    public Range[] getDifference(Range range) {
        if (this.getFrom() >= range.getFrom() && this.getTo() <= range.getTo()) {
            return null;
        }

        if (this.isInside(range.getFrom()) && this.isInside(range.getTo())) {
            return new Range[]{new Range(this.getFrom(), range.getFrom()), new Range(range.getTo(), this.getTo())};
        }

        Range difference = new Range(this.getFrom(), this.getTo());

        if (this.isInside(range.getFrom())) {
            difference.setTo(range.getFrom());
        } else if (this.isInside(range.getTo())) {
            difference.setFrom(range.getTo());
        }

        return new Range[]{difference};
    }

    @Override
    public String toString() {
        return "{" +
                from +
                ", " + to +
                '}';
    }
}