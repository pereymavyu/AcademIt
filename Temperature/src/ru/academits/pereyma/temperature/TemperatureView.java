package ru.academits.pereyma.temperature;

import javax.swing.*;
import java.awt.*;

public class TemperatureView {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Temperature Converter");
                frame.setSize(500, 500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


               // JPanel panel = new JPanel(new BorderLayout());
               // frame.add(panel);

               // JTextField userInputField = new JTextField();
               // userInputField.setBounds(400,400,10,10);

                JButton convertButton = new JButton("Convert");
               // convertButton.setBounds(200, 200, 10, 10);
                frame.add(convertButton);

                JButton convertButton2 = new JButton("Convert2");
               // convertButton2.setBounds(200, 200, 10, 10);
                frame.add(convertButton2);

              //  JLabel resultTemperature = new JLabel("result");
              //  resultTemperature.setBounds(600,600,10,10);

                frame.setVisible(true);


            }
        });
    }

}
