package ru.academits.pereyma.minesweeper.gui;

import ru.academits.pereyma.minesweeper.model.MineSweeperModelImp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.event.MouseEvent.*;

public class MineButtonListener extends MouseAdapter {
    MineSweeperModelImp mineSweeperModelImp;
    ButtonsFieldGui buttonsFieldGui;
    int x;
    int y;
    MineButton button;
    View view;

    MineButtonListener(int x, int y, MineSweeperModelImp mineSweeperModelImp, MineButton button, ButtonsFieldGui buttonsFieldGui, View view) {
        super();
        this.x = x;
        this.y = y;
        this.mineSweeperModelImp = mineSweeperModelImp;
        this.button = button;
        this.buttonsFieldGui = buttonsFieldGui;
        this.view = view;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == BUTTON1) {

            int modelOutput = mineSweeperModelImp.openCell(x, y);
            if (modelOutput > 0) {
                button.setText(Integer.toString(modelOutput));
            } else if (modelOutput == 0) {
                button.setText("  ");

                mineSweeperModelImp.openFreeCells(x, y);

                MineButton[][] buttonField = buttonsFieldGui.getMinesButtonField();
                int[][] userField = mineSweeperModelImp.getMaskField();
                int[][] mineField = mineSweeperModelImp.getMaskField();

                for (int i = 0; i < buttonsFieldGui.getSizeX(); ++i) {
                    for (int j = 0; j < buttonsFieldGui.getSizeX(); ++j) {
                        if (userField[i][j] == 1) {
                            buttonField[i][j].setEnabled(false);
                        }
                    }
                }
            } else {
                button.setText("xx");
            }


            button.setEnabled(false);

        } else if (e.getButton() == BUTTON3) {
            int[][] userField = mineSweeperModelImp.getMaskField();
            MineButton[][] buttonField = buttonsFieldGui.getMinesButtonField();
            if (userField[x][y] == 0) {
                buttonField[x][y].setText("FF");
                userField[x][y] = 2;
                mineSweeperModelImp.setFlagsCounter(mineSweeperModelImp.getFlagsCounter() - 1);
                view.getFlagCounterView().setText(Integer.toString(mineSweeperModelImp.getFlagsCounter()));


            } else if (userField[x][y] == 2) {
                buttonField[x][y].setText("  ");
                userField[x][y] = 0;
                mineSweeperModelImp.setFlagsCounter(mineSweeperModelImp.getFlagsCounter() + 1);
                view.getFlagCounterView().setText(Integer.toString(mineSweeperModelImp.getFlagsCounter()));
            }
        }
    }
}
