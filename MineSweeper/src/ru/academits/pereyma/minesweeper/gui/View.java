package ru.academits.pereyma.minesweeper.gui;

import ru.academits.pereyma.minesweeper.model.MineSweeperModelImp;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    MineSweeperModelImp mineSweeperModelImp;
    JLabel flagCounterView;

    public View(MineSweeperModelImp mineSweeperModelImp) {
        this.mineSweeperModelImp = mineSweeperModelImp;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel flagCounterView = new JLabel(Integer.toString(mineSweeperModelImp.getFlagsCounter()));
        this.flagCounterView = flagCounterView;
        add(flagCounterView);

        JButton newGameButton = new JButton("Start new game");
        add(newGameButton);

        add(new JLabel("Timer"));


        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipady = 40;      //make this component tall
        //c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;

        ButtonsFieldGui buttonsField = new ButtonsFieldGui(mineSweeperModelImp, this);

        add(buttonsField, c);

        buttonsField.setMinimumSize(new Dimension(1000, 1000));
        //pane.add(button, c);


        pack();
        setVisible(true);
    }

    public JLabel getFlagCounterView() {
        return flagCounterView;
    }
}
