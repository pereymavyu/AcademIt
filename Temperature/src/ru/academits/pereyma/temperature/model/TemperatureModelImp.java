package ru.academits.pereyma.temperature.model;

public class TemperatureModelImp implements TemperatureModel {
    private final static double ABSOLUTE_ZERO_IN_CELSIUS = -273.15;

    // Массив используемых температурных шкал. Для добавления новой шкалы в программу нужно создать новый класс,
    // имплементирующий интерфейс TemperatureScale и добавить объект нового класса в массив TEMPERATURE_SCALES
    private final static TemperatureScale[] TEMPERATURE_SCALES = new TemperatureScale[]{new Celsius(), new Fahrenheit(), new Kelvin()};

    private TemperatureScale inputScale;
    private TemperatureScale outputScale;

    public TemperatureScale[] getScales() {
        return TEMPERATURE_SCALES;
    }

    public String[] getScalesNames() {
        String[] ScalesNamesList = new String[TEMPERATURE_SCALES.length];

        for (int i = 0; i < TEMPERATURE_SCALES.length; ++i) {
            ScalesNamesList[i] = TEMPERATURE_SCALES[i].getScaleName();
        }

        return ScalesNamesList;
    }

    @Override
    public void setInputScale(TemperatureScale inputTemperatureScale) {
        this.inputScale = inputTemperatureScale;
    }

    @Override
    public void setOutputScale(TemperatureScale outputTemperatureScale) {
        this.outputScale = outputTemperatureScale;
    }

    @Override
    public double getConvertedTemperature(double inputTemperature) {
        double temperatureInCelsius = inputScale.convertToCelsius(inputTemperature);

        if (temperatureInCelsius < ABSOLUTE_ZERO_IN_CELSIUS) {
            throw new IllegalArgumentException("Temperature " + temperatureInCelsius + " must be not less than absolute zero");
        }

        return outputScale.convertFromCelsius(temperatureInCelsius);
    }
}