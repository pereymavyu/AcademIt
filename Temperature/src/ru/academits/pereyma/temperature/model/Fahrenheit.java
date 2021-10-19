package ru.academits.pereyma.temperature.model;

public class Fahrenheit implements TemperatureScale {
    @Override
    public String getScaleName() {
        return "Fahrenheit";
    }

    @Override
    public double convertToCelsius(double oldValue) {
        return (oldValue - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double oldValue) {
        return oldValue * 1.8 + 32;
    }
}
