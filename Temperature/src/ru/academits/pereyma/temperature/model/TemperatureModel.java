package ru.academits.pereyma.temperature.model;

public interface TemperatureModel {
    TemperatureScale[] getScales();

    String[] getScalesNames();

    void setInputScale(TemperatureScale inputTemperatureScale);

    void setOutputScale(TemperatureScale outputTemperatureScale);

    double getConvertedTemperature(double inputTemperature);
}
