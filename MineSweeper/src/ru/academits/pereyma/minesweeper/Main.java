package ru.academits.pereyma.minesweeper;

import ru.academits.pereyma.minesweeper.model.MineSweeperModelImp;
import ru.academits.pereyma.minesweeper.gui.View;

public class Main {
    public static void main(String[] args) {
        MineSweeperModelImp mineSweeperModelImp = new MineSweeperModelImp();

        mineSweeperModelImp.printMineField();

        View view = new View(mineSweeperModelImp);
    }
}
