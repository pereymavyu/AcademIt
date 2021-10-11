package ru.academits.pereyma.minesweeper.gui;

import ru.academits.pereyma.minesweeper.model.MineSweeperModelImp;

import javax.swing.*;
import java.awt.*;

public class ButtonsFieldGui extends JPanel {
    MineSweeperModelImp mineSweeperModelImp;
    int sizeX;
    int sizeY;
    public MineButton[][] minesButtonField;
    View view;

    public MineButton[][] getMinesButtonField() {
        return minesButtonField;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    ButtonsFieldGui(MineSweeperModelImp mineSweeperModelImp, View view) {
        super();
        this.mineSweeperModelImp = mineSweeperModelImp;
        this.sizeX = mineSweeperModelImp.getFieldSizeX();
        this.sizeY = mineSweeperModelImp.getFieldSizeY();
        this.view = view;

        setSize(sizeX * 40, sizeY * 40);
        setLayout(new GridLayout(sizeX, sizeY));

        minesButtonField = new MineButton[sizeX][sizeY];

        for (int i = 0; i < sizeX; ++i) {
            for (int j = 0; j < sizeY; ++j) {
                minesButtonField[i][j] = new MineButton();

                add(minesButtonField[i][j]);
                minesButtonField[i][j].addMouseListener(new MineButtonListener(i, j, mineSweeperModelImp, minesButtonField[i][j], this, view));
            }
        }
    }
}
