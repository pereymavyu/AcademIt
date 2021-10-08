package ru.academits.pereyma.temperature.view;

import ru.academits.pereyma.temperature.model.TemperatureModel;
import ru.academits.pereyma.temperature.model.TemperatureScales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureView extends JFrame {
    private final TemperatureModel model;

    private final JTextField userInputField;
    private final JTextField outputField;
    private final JComboBox<String> inputTemperatureScalesList;
    private final JComboBox<String> outputTemperatureScalesList;

    public TemperatureView(TemperatureModel model) {
        super("Temperature Converter");

        this.model = model;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new GridLayout(4, 2));

        JLabel temperatureInputPrompt = new JLabel("Enter temperature value:", SwingConstants.RIGHT);
        add(temperatureInputPrompt);

        userInputField = new JTextField(10);
        add(userInputField);

        JLabel inputTemperatureScaleLabel = new JLabel("From:", SwingConstants.RIGHT);
        add(inputTemperatureScaleLabel);

        String[] temperatureScales = {"Celsius", "Fahrenheit", "Kelvin"};

        inputTemperatureScalesList = new JComboBox<>(temperatureScales);
        inputTemperatureScalesList.setSelectedIndex(0);
        add(inputTemperatureScalesList);

        JLabel outputTemperatureScaleLabel = new JLabel("To:", SwingConstants.RIGHT);
        add(outputTemperatureScaleLabel);

        outputTemperatureScalesList = new JComboBox<>(temperatureScales);
        outputTemperatureScalesList.setSelectedIndex(1);
        add(outputTemperatureScalesList);

        JButton convertButton = new JButton("Convert");
        add(convertButton);

        outputField = new JTextField(20);
        outputField.setEditable(false);
        add(outputField);

        convertButton.addActionListener(new ButtonListener());

        pack();
        setVisible(true);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double inputTemp;

            try {
                inputTemp = Double.parseDouble(userInputField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TemperatureView.this, "Please, enter a number");
                return;
            }

            int index = inputTemperatureScalesList.getSelectedIndex();

            if (index == 0) {
                model.setInputTemperatureScale(TemperatureScales.CELSIUS);
            } else if (index == 1) {
                model.setInputTemperatureScale(TemperatureScales.FAHRENHEIT);
            } else {
                model.setInputTemperatureScale(TemperatureScales.KELVIN);
            }

            int indexOut = outputTemperatureScalesList.getSelectedIndex();

            if (indexOut == 0) {
                model.setOutputTemperatureScale(TemperatureScales.CELSIUS);
            } else if (indexOut == 1) {
                model.setOutputTemperatureScale(TemperatureScales.FAHRENHEIT);
            } else {
                model.setOutputTemperatureScale(TemperatureScales.KELVIN);
            }

            double resultTemperature;

            try {
                resultTemperature = model.getConvertedTemperature(inputTemp, model.getInputTemperatureScale(), model.getOutputTemperatureScale());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(TemperatureView.this, "Temperature value must not be less than absolute zero (-273.15 for Celsius, -459.67 for Fahrenheit, 0 for Kelvin)");
                return;
            }

            outputField.setText(String.format("= %.2f", resultTemperature));
        }
    }
}
