package be.technifutur.sudoku.sudo9x9;

import be.technifutur.sudoku.*;

public class SudokuModel9x9 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel9x9() {
        super(createGrille());
    }

    private static Cell[][] createGrille() {
        Cell[][] grille = new Cell[9][9];
        for (int lig = 0; lig < 9; lig++) {
            for (int col = 0; col < 9; col++) {
                grille[lig][col] = new Cell();
            }
        }
        return grille;
    }

    @Override
    public boolean isValueValid(char value) {
        return value >= '1' && value <= '9';
    }

    @Override
    public boolean isPositionValid(int lig, int col) {
        return lig >= 0 &&
                lig < 9 &&
                col >= 0 &&
                col < 9;
    }

    @Override
    public int getNbvalues() {
        return 9*9;
    }


}
