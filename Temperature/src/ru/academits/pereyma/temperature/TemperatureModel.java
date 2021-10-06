package ru.academits.pereyma.temperature;

enum TemperatureScales {
    CELCIUS,
    FAHRENHEITS,
    KELVINS
}

public class TemperatureModel {
    double oldTemperature;
    TemperatureScales oldScale = TemperatureScales.CELCIUS;
    TemperatureScales newScale = TemperatureScales.FAHRENHEITS;
    double newTemperature = getConvertedTemperature(oldTemperature, oldScale, newScale);

    public double getOldTemperature() {
        return oldTemperature;
    }

    public void setOldTemperature(double oldTemperature) {
        this.oldTemperature = oldTemperature;
    }

    public TemperatureScales getOldScale() {
        return oldScale;
    }

    public void setOldScale(TemperatureScales oldScale) {
        this.oldScale = oldScale;
    }

    public TemperatureScales getNewScale() {
        return newScale;
    }

    public void setNewScale(TemperatureScales newScale) {
        this.newScale = newScale;
    }

    public double getNewTemperature() {
        return newTemperature;
    }

    public void setNewTemperature(double newTemperature) {
        this.newTemperature = newTemperature;
    }

    public double getConvertedTemperature(double oldTemperature, TemperatureScales oldScale, TemperatureScales newScale) {
        switch (oldScale) {
            case CELCIUS -> {
                switch (newScale) {
                    case CELCIUS -> {
                        return oldTemperature;
                    }
                    case FAHRENHEITS -> {
                        return oldTemperature * 1.8 + 32;
                    }
                    case KELVINS -> {
                        return oldTemperature + 273.15;
                    }
                }
            }

            case FAHRENHEITS -> {
                switch (newScale) {
                    case CELCIUS -> {
                        return (oldTemperature - 32) * 5 / 9;
                    }
                    case FAHRENHEITS -> {
                        return oldTemperature;
                    }
                    case KELVINS -> {
                        return (oldTemperature - 32) * 5 / 9 + 273.15;
                    }

                }
            }

            case KELVINS -> {
                switch (newScale) {
                    case CELCIUS -> {
                        return oldTemperature - 273.15;
                    }
                    case FAHRENHEITS -> {
                        return (oldTemperature - 273.15) * 1.8 + 32;
                    }

                    case KELVINS -> {
                        return oldTemperature;
                    }
                }
            }
        }

        return 0;
    }
}