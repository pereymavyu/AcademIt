package ru.academits.pereyma.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class TemperatureView {
    private TemperatureModel model;

    public void setModel(TemperatureModel model) {
        this.model = model;
    }

    public TemperatureView() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(500, 500);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel inputTemperatureLabel = new JLabel("Введите температуру");
        frame.add(inputTemperatureLabel);

        JTextField userInputField = new JTextField(20);
        frame.add(userInputField);

        ButtonGroup inputTemperatureOptions = new ButtonGroup();
        JRadioButton inputTemperatureCelcius = new JRadioButton("Celcius", true);
        JRadioButton inputTemperatureFahrenheits = new JRadioButton("Fahrenheits");
        JRadioButton inputTemperatureKelvins = new JRadioButton("Kelvins");

        inputTemperatureOptions.add(inputTemperatureCelcius);
        inputTemperatureOptions.add(inputTemperatureFahrenheits);
        inputTemperatureOptions.add(inputTemperatureKelvins);
        frame.add(inputTemperatureCelcius);
        frame.add(inputTemperatureFahrenheits);
        frame.add(inputTemperatureKelvins);


        JLabel resultTemperature = new JLabel("Результат перевода температуры");
        frame.add(resultTemperature);

        JTextField userOutputField = new JTextField(20);
        userOutputField.setEditable(false);
        frame.add(userOutputField);


        JButton convertButton = new JButton("Convert");


        frame.add(convertButton);

        ButtonGroup outputTemperatureOptions = new ButtonGroup();
        JRadioButton outputTemperatureCelcius = new JRadioButton("Celcius");
        JRadioButton outputTemperatureFahrenheits = new JRadioButton("Fahrenheits", true);
        JRadioButton outputTemperatureKelvins = new JRadioButton("Kelvins");

        outputTemperatureOptions.add(outputTemperatureCelcius);
        outputTemperatureOptions.add(outputTemperatureFahrenheits);
        outputTemperatureOptions.add(outputTemperatureKelvins);
        frame.add(outputTemperatureCelcius);
        frame.add(outputTemperatureFahrenheits);
        frame.add(outputTemperatureKelvins);


        class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                double inputTemp = Double.parseDouble(userInputField.getText());

                if (inputTemperatureCelcius.isSelected()) {
                    model.setOldScale(TemperatureScales.CELCIUS);
                } else if (inputTemperatureFahrenheits.isSelected()) {
                    model.setOldScale(TemperatureScales.FAHRENHEITS);
                } else {
                    model.setOldScale(TemperatureScales.KELVINS);
                }

                if (outputTemperatureCelcius.isSelected()) {
                    model.setNewScale(TemperatureScales.CELCIUS);
                } else if (outputTemperatureFahrenheits.isSelected()) {
                    model.setNewScale(TemperatureScales.FAHRENHEITS);
                } else {
                    model.setNewScale(TemperatureScales.KELVINS);
                }

                userOutputField.setText(String.valueOf(model.getConvertedTemperature(inputTemp, model.getOldScale(), model.getNewScale())));
            }
        }

        convertButton.addActionListener(new ButtonListener());

        frame.pack();
        frame.setVisible(true);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}

