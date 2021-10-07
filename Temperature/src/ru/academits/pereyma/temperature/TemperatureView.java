package ru.academits.pereyma.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureView extends JFrame {
    private TemperatureModel model;

    public void setModel(TemperatureModel model) {
        this.model = model;
    }

    public TemperatureView() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new GridLayout(4, 2));

        JLabel inputTemperatureValueLabel = new JLabel("Введите температуру:");
        frame.add(inputTemperatureValueLabel);

        JTextField userInputField = new JTextField(20);
        frame.add(userInputField);

        JLabel inputTemperatureScaleLabel = new JLabel("From:");
        frame.add(inputTemperatureScaleLabel);

        String[] inputTemperatureScales = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> inputTemperatureScalesList = new JComboBox<>(inputTemperatureScales);
        inputTemperatureScalesList.setSelectedIndex(0);
        frame.add(inputTemperatureScalesList);


        JLabel outputTemperatureScaleLabel = new JLabel("To:");
        frame.add(outputTemperatureScaleLabel);

        String[] outputTemperatureScales = {"Celsius", "Fahrenheit", "Kelvin"};
        JComboBox<String> outputTemperatureScalesList = new JComboBox<>(inputTemperatureScales);
        outputTemperatureScalesList.setSelectedIndex(1);
        frame.add(outputTemperatureScalesList);

        JButton convertButton = new JButton("Convert");
        frame.add(convertButton);

        JTextField outputField = new JTextField(20);
        outputField.setEditable(false);
        frame.add(outputField);


        class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                double inputTemp = 0;

                try {
                    inputTemp = Double.parseDouble(userInputField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Пожалуйста, введите число");
                    return;
                }

                int index = inputTemperatureScalesList.getSelectedIndex();

                if (index == 0) {
                    model.setOldScale(TemperatureScales.CELCIUS);
                } else if (index == 1) {
                    model.setOldScale(TemperatureScales.FAHRENHEITS);
                } else {
                    model.setOldScale(TemperatureScales.KELVINS);
                }

                int indexOut = outputTemperatureScalesList.getSelectedIndex();


                if (indexOut == 0) {
                    model.setNewScale(TemperatureScales.CELCIUS);
                    //outPutString = "celsius";
                } else if (indexOut == 1) {
                    model.setNewScale(TemperatureScales.FAHRENHEITS);
                    //outPutString = "fahrenheits";
                } else {
                    model.setNewScale(TemperatureScales.KELVINS);
                    //outPutString = "kelvins";
                }

                Double resultTemperature = model.getConvertedTemperature(inputTemp, model.getOldScale(), model.getNewScale());
                outputField.setText(String.format("= %.2f \u00B0", resultTemperature));
            }
        }

        convertButton.addActionListener(new ButtonListener());

        frame.pack();
        frame.setVisible(true);
    }
}
