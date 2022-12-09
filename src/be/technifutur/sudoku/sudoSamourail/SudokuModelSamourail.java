package be.technifutur.sudoku.sudoSamourail;

import be.technifutur.sudoku.AbstractSudokuModel;
import be.technifutur.sudoku.Cell;
import be.technifutur.sudoku.SudokuModel;

public class SudokuModelSamourail extends AbstractSudokuModel implements SudokuModel {

    public SudokuModelSamourail() {
        super(createGrille());
    }

    private static Cell[][] createGrille() {
        Cell[][] grille = new Cell[21][21];
        for (int lig = 0; lig < 21; lig++) {
            for (int col = 0; col < 21; col++) {
                if (isPositionValidStatic(lig, col))
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
        return isPositionValidStatic(lig, col);
    }

    public static boolean isPositionValidStatic(int lig, int col) {
        return isPositionInSudoku(lig, col, 0, 0) ||
                isPositionInSudoku(lig, col, 0, 9 + 3) ||
                isPositionInSudoku(lig, col, 6, 6) ||
                isPositionInSudoku(lig, col, 9 + 3, 0) ||
                isPositionInSudoku(lig, col, 9 + 3, 9 + 3);
    }

    @Override
    public int getNbvalues() {
        return 21 * 21 - 4 * 3 * 6;
    }

    private static boolean isPositionInSudoku(int lig, int col, int lig0, int col0) {
        return lig >= lig0 &&
                lig < 9 + lig0 &&
                col >= col0 &&
                col < 9 + col0;
    }
}
