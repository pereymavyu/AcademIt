package ru.academits.pereyma.temperature.model;

public class Celsius implements TemperatureScale {
    @Override
    public String getScaleName() {
        return "Celsius";
    }

    @Override
    public double convertToCelsius(double oldValue) {
        return oldValue;
    }

    @Override
    public double convertFromCelsius(double oldValue) {
        return oldValue;
    }
}
