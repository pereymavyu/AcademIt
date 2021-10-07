package ru.academits.pereyma.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import static java.awt.Component.LEFT_ALIGNMENT;
import static java.awt.Component.RIGHT_ALIGNMENT;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class TemperatureView {
    private TemperatureModel model;

    public void setModel(TemperatureModel model) {
        this.model = model;
    }

    public TemperatureView() {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setSize(500, 500);

        GridLayout experimentLayout = new GridLayout(3, 1);

        frame.getContentPane().setLayout(experimentLayout);
        //new BoxLayout(frame, BoxLayout.Y_AXIS);
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEADING));

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel inputTemperatureLabel = new JLabel("Введите температуру");
        panel1.add(inputTemperatureLabel);

        JTextField userInputField = new JTextField(20);
        panel1.add(userInputField);

        ButtonGroup inputTemperatureOptions = new ButtonGroup();
        JRadioButton inputTemperatureCelcius = new JRadioButton("Celcius", true);
        JRadioButton inputTemperatureFahrenheits = new JRadioButton("Fahrenheits");
        JRadioButton inputTemperatureKelvins = new JRadioButton("Kelvins");

        inputTemperatureOptions.add(inputTemperatureCelcius);
        inputTemperatureOptions.add(inputTemperatureFahrenheits);
        inputTemperatureOptions.add(inputTemperatureKelvins);
        panel1.add(inputTemperatureCelcius);
        panel1.add(inputTemperatureFahrenheits);
        panel1.add(inputTemperatureKelvins);


        JLabel resultTemperature = new JLabel("Результат перевода температуры");
        resultTemperature.setAlignmentX(RIGHT_ALIGNMENT);
        panel3.add(resultTemperature);

        JTextField userOutputField = new JTextField(20);
        userOutputField.setEditable(false);
        panel3.add(userOutputField);


        JButton convertButton = new JButton("Convert");

        panel2.add(convertButton);

        ButtonGroup outputTemperatureOptions = new ButtonGroup();
        JRadioButton outputTemperatureCelcius = new JRadioButton("Celcius");
        JRadioButton outputTemperatureFahrenheits = new JRadioButton("Fahrenheits", true);
        JRadioButton outputTemperatureKelvins = new JRadioButton("Kelvins");

        outputTemperatureOptions.add(outputTemperatureCelcius);
        outputTemperatureOptions.add(outputTemperatureFahrenheits);
        outputTemperatureOptions.add(outputTemperatureKelvins);
        panel3.add(outputTemperatureCelcius);
        panel3.add(outputTemperatureFahrenheits);
        panel3.add(outputTemperatureKelvins);


        class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                double inputTemp = 0;
                try {
                    inputTemp = Double.parseDouble(userInputField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Пожалуйста, введите число", "Ошибка", WARNING_MESSAGE);
                }

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

