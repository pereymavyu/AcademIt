package ru.academits.pereyma.temperature;

import javax.swing.*;
import java.awt.*;

public class TemparatureMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TemperatureModel model = new TemperatureModel();

                TemperatureView view = new TemperatureView();
                view.setModel(model);
            }
        });
    }

}