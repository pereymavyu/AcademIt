package ru.academits.pereyma.temperature.model;

public class TemperatureModel {
    public static final double ABSOLUTE_ZERO_VALUE_CELSIUS = -273.15;
    public static final double ABSOLUTE_ZERO_VALUE_FAHRENHEIT = -459.67;
    public static final double ABSOLUTE_ZERO_VALUE_KELVIN = 0;

    TemperatureScales inputTemperatureScale = TemperatureScales.CELSIUS;
    TemperatureScales outputTemperatureScale = TemperatureScales.FAHRENHEIT;

    public TemperatureScales getInputTemperatureScale() {
        return inputTemperatureScale;
    }

    public void setInputTemperatureScale(TemperatureScales inputTemperatureScale) {
        this.inputTemperatureScale = inputTemperatureScale;
    }

    public TemperatureScales getOutputTemperatureScale() {
        return outputTemperatureScale;
    }

    public void setOutputTemperatureScale(TemperatureScales outputTemperatureScale) {
        this.outputTemperatureScale = outputTemperatureScale;
    }

    public double getConvertedTemperature(double oldTemperature, TemperatureScales oldScale, TemperatureScales newScale) {
        switch (oldScale) {
            case CELSIUS -> {
                if (oldTemperature < ABSOLUTE_ZERO_VALUE_CELSIUS) {
                    throw new IllegalArgumentException("Temperature value" + oldTemperature + " must be greater than absolute zero " + ABSOLUTE_ZERO_VALUE_CELSIUS);
                }

                switch (newScale) {
                    case CELSIUS -> {
                        return oldTemperature;
                    }
                    case FAHRENHEIT -> {
                        return oldTemperature * 1.8 + 32;
                    }
                    case KELVIN -> {
                        return oldTemperature - ABSOLUTE_ZERO_VALUE_CELSIUS;
                    }
                }
            }

            case FAHRENHEIT -> {
                if (oldTemperature < ABSOLUTE_ZERO_VALUE_FAHRENHEIT) {
                    throw new IllegalArgumentException("Temperature value" + oldTemperature + " must be greater than absolute zero " + ABSOLUTE_ZERO_VALUE_FAHRENHEIT);
                }

                switch (newScale) {
                    case CELSIUS -> {
                        return (oldTemperature - 32) * 5 / 9;
                    }
                    case FAHRENHEIT -> {
                        return oldTemperature;
                    }
                    case KELVIN -> {
                        return (oldTemperature - 32) * 5 / 9 - ABSOLUTE_ZERO_VALUE_CELSIUS;
                    }
                }
            }

            case KELVIN -> {
                if (oldTemperature < ABSOLUTE_ZERO_VALUE_KELVIN) {
                    throw new IllegalArgumentException("Temperature value" + oldTemperature + " must be greater than absolute zero " + ABSOLUTE_ZERO_VALUE_KELVIN);
                }

                switch (newScale) {
                    case CELSIUS -> {
                        return oldTemperature + ABSOLUTE_ZERO_VALUE_CELSIUS;
                    }
                    case FAHRENHEIT -> {
                        return (oldTemperature + ABSOLUTE_ZERO_VALUE_CELSIUS) * 1.8 + 32;
                    }
                    case KELVIN -> {
                        return oldTemperature;
                    }
                }
            }
        }

        return 0;
    }
}