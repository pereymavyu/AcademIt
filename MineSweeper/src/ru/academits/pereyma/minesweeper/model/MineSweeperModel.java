package ru.academits.pereyma.minesweeper.model;

public interface MineSweeperModel {
    void startNewGame(int sizeX, int sizeY, int minesAmount);

    int[][] generateMineField(int sizeX, int sizeY, int minesAmount);

    int[][] generateMaskField(int sizeX, int sizeY);

    int openCell(int x, int y);

    void setFlag(int x, int y);

    void finishGame();
}
