package be.technifutur.sudoku.sudo4x4;

import be.technifutur.sudoku.AbstractSudokuModel;
import be.technifutur.sudoku.Cell;
import be.technifutur.sudoku.SudokuModel;

public class SudokuModel4x4 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel4x4() {
        super(createGrille());
    }

    private static Cell[][] createGrille() {
        Cell[][] grille = new Cell[4][4];
        for (int lig = 0; lig < 4; lig++) {
            for (int col = 0; col < 4; col++) {
                grille[lig][col] = new Cell();
            }
        }
        return grille;
    }

    @Override
    public boolean isValueValid(char value) {
        return value >= '1' && value <= '4';
    }

    @Override
    public boolean isPositionValid(int lig, int col) {
        return lig >= 0 &&
                lig < 4 &&
                col >= 0 &&
                col < 4;
    }

    @Override
    public int getNbvalues() {
        return 4 * 4;
    }
}
