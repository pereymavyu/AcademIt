package ru.academits.pereyma.temperature.model;

public interface TemperatureScale {
    String getScaleName();

    double convertToCelsius(double oldValue);

    double convertFromCelsius(double oldValue);
}
