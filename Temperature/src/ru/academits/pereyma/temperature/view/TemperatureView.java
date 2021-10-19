package ru.academits.pereyma.temperature.view;

import ru.academits.pereyma.temperature.model.*;

import javax.swing.*;
import java.awt.*;

public class TemperatureView {
    private final TemperatureModel model;
    private JFrame frame;
    private JTextField userInputField;
    private JTextField outputField;
    private JComboBox<String> inputScaleSelector;
    private JComboBox<String> outputScaleSelector;

    public TemperatureView(TemperatureModel model) {
        this.model = model;

        SwingUtilities.invokeLater(this::makeFrame);
    }

    public void makeFrame() {
        // Создание окна программы
        frame = new JFrame("Temperature converter");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new GridLayout(4, 2));

        // Создание поля ввода температуры и подписи для него
        JLabel temperatureInputPrompt = new JLabel("Enter temperature value:", SwingConstants.RIGHT);
        frame.add(temperatureInputPrompt);

        userInputField = new JTextField(10);
        frame.add(userInputField);

        // Создание выпадающего списка шкал входной температуры и подписи для него
        JLabel inputScaleLabel = new JLabel("From:", SwingConstants.RIGHT);
        frame.add(inputScaleLabel);

        String[] scalesNames = model.getScalesNames();

        inputScaleSelector = new JComboBox<>(scalesNames);
        inputScaleSelector.setSelectedIndex(0);
        frame.add(inputScaleSelector);

        // Создание выпадающего списка шкал выходной температуры и подписи для него
        JLabel outputScaleLabel = new JLabel("To:", SwingConstants.RIGHT);
        frame.add(outputScaleLabel);

        outputScaleSelector = new JComboBox<>(scalesNames);
        outputScaleSelector.setSelectedIndex(1);
        frame.add(outputScaleSelector);

        // Создание кнопки Convert и поля вывода конвертированной температуры
        JButton convertButton = new JButton("Convert");
        frame.add(convertButton);

        outputField = new JTextField(20);
        outputField.setEditable(false);
        frame.add(outputField);

        convertButton.addActionListener(e -> {
            double inputTemp;

            try {
                inputTemp = Double.parseDouble(userInputField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please, enter a number");
                return;
            }

            TemperatureScale[] scales = model.getScales();

            // Установка входной и выходной температурной шкалы
            // (индексы температурных шкал в выпадающих списках и в массиве используемых шкал в TemperatureModelImp.TEMPERATURE_SCALES соответствуют друг другу)
            int inputScaleIndex = inputScaleSelector.getSelectedIndex();
            model.setInputScale(scales[inputScaleIndex]);

            int outputScaleIndex = outputScaleSelector.getSelectedIndex();
            model.setOutputScale(scales[outputScaleIndex]);

            double resultTemperature;

            try {
                resultTemperature = model.getConvertedTemperature(inputTemp);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "Temperature value must not be less than absolute zero (-273.15 for Celsius, -459.67 for Fahrenheit, 0 for Kelvin)");
                return;
            }

            outputField.setText(String.format("%.2f", resultTemperature));
        });

        frame.pack();
        frame.setVisible(true);
    }
}