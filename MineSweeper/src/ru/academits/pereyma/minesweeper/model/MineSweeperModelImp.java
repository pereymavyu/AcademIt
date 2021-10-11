package ru.academits.pereyma.minesweeper.model;

import java.util.Arrays;
import java.util.Random;

public class MineSweeperModelImp implements MineSweeperModel {
    private static final int DEFAULT_MINE_FIELD_SIZE_X = 9;
    private static final int DEFAULT_MINE_FIELD_SIZE_Y = 9;
    private static final int DEFAULT_MINES_AMOUNT = 10;

    int fieldSizeX;
    int fieldSizeY;
    int minesAmount;
    int[][] mineField;
    int[][] maskField;
    int flagsCounter;

    public MineSweeperModelImp() {
        fieldSizeX = DEFAULT_MINE_FIELD_SIZE_X;
        fieldSizeY = DEFAULT_MINE_FIELD_SIZE_Y;
        minesAmount = DEFAULT_MINES_AMOUNT;
        flagsCounter = DEFAULT_MINES_AMOUNT;

        generateMineField();
        generateMaskField();
    }

    private int[][] generateMines() {
        int[][] mines = new int[minesAmount][];

        Random random = new Random();

        for (int i = 0; i < minesAmount; ) {
            int[] mine = {random.nextInt(fieldSizeX), random.nextInt(fieldSizeY)};

            boolean contains = false;

            for (int[] e : mines) {
                if (Arrays.equals(e, mine)) {
                    contains = true;

                    break;
                }
            }

            if (!contains) {
                mines[i] = mine;
                ++i;
            }
        }

        for (int[] e : mines) {
            System.out.printf("(%2d, %2d), ", e[0], e[1]);
        }

        System.out.println("==");
        System.out.println(Arrays.deepToString(mines));

        return mines;
    }

    public void generateMineField() {
        mineField = new int[fieldSizeX][fieldSizeY];

        for (int i = 0; i < fieldSizeX; ++i) {
            for (int j = 0; j < fieldSizeY; ++j) {
                mineField[i][j] = 0;
            }
        }

        int[][] mines = generateMines();

        for (int[] e : mines) {
            int x = e[0];
            int y = e[1];

            for (int i = x - 1; i <= x + 1; ++i) {
                for (int j = y - 1; j <= y + 1; ++j) {
                    if (i >= 0 && i < fieldSizeX && j >= 0 && j < fieldSizeY) {
                        mineField[i][j] += 1;
                    }
                }
            }
        }

        for (int[] e : mines) {
            mineField[e[0]][e[1]] = -1;
        }

    }

    public void generateMaskField() {
        maskField = new int[fieldSizeX][fieldSizeY];

        for (int i = 0; i < fieldSizeX; ++i) {
            for (int j = 0; j < fieldSizeY; ++j) {
                maskField[i][j] = 0;
            }
        }
    }

    public int openCell(int x, int y) {
        if (mineField[x][y] == -1) {
            return -1;
            //endGame();
        } else if (mineField[x][y] == 0) {
            openFreeCells(x, y);
            return 0;
        } else {
            maskField[x][y] = 1;
            return mineField[x][y];
        }
    }

    public void endGame() {
        return;
    }

    public void openFreeCells(int x, int y) {
        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                if (i == x && j == x) {
                    continue;
                }

                if (i >= 0 && i < fieldSizeX && j >= 0 && j < fieldSizeY && mineField[i][j] == 0 && maskField[i][j] == 0) {
                    maskField[i][j] += 1;

                    openFreeCells(i, j);
                }
            }
        }


        return;
    }

    @Override
    public void startNewGame(int sizeX, int sizeY, int minesAmount) {

    }

    @Override
    public int[][] generateMineField(int sizeX, int sizeY, int minesAmount) {
        return new int[0][];
    }

    @Override
    public int[][] generateMaskField(int sizeX, int sizeY) {
        return new int[0][];
    }


    @Override
    public void setFlag(int x, int y) {
        if (maskField[x][y] == 0) {
            maskField[x][y] = 1;
            ++flagsCounter;
        } else {
            maskField[x][y] = 0;
            --flagsCounter;
        }
    }

    @Override
    public void removeFlag(int x, int y) {

    }

    @Override
    public void finishGame() {

    }

    public int getFieldSizeX() {
        return fieldSizeX;
    }

    public int getFieldSizeY() {
        return fieldSizeY;
    }

    public int[][] getMineField() {
        return mineField;
    }

    public int[][] getMaskField() {
        return maskField;
    }

    public int getFlagsCounter() {
        return flagsCounter;
    }

    public void setFlagsCounter(int flagsCounter) {
        this.flagsCounter = flagsCounter;
    }


    public void printMineField() {
        for (int i = 0; i < fieldSizeY; ++i) {
            for (int j = 0; j < fieldSizeX; ++j) {
                if (mineField[j][i] == -1) {
                    System.out.print("xx  ");
                    continue;
                }

                System.out.printf("%2d  ", mineField[j][i]);
            }

            System.out.println();
        }
    }

}

