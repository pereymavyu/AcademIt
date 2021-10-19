package ru.academits.pereyma.temperature.main;

import ru.academits.pereyma.temperature.model.TemperatureModel;
import ru.academits.pereyma.temperature.model.TemperatureModelImp;
import ru.academits.pereyma.temperature.view.TemperatureView;

import javax.swing.*;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureModel model = new TemperatureModelImp();
        new TemperatureView(model);
    }
}