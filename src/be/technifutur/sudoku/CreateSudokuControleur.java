package be.technifutur.sudoku;

import be.technifutur.sudoku.sudo9x9.SudokuModel9x9;
import be.technifutur.sudoku.sudo9x9.Vue9x9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateSudokuControleur implements SudokuControleur {
    private final SudokuModel sudoku;
    private final SudokuVue vue;

    private final Input input;

    private Pattern pattern = Pattern.compile("([1-9][0-9]*)\\.([1-9][0-9]*)(\\..)?");
    public CreateSudokuControleur(SudokuModel sudoku, SudokuVue vue, Input input) {
        this.sudoku = sudoku;
        this.vue = vue;
        this.input = input;
    }
    public CreateSudokuControleur(SudokuModel sudoku, SudokuVue vue) {
        this(sudoku, vue,new ScannerInput());
    }

    @Override
    public void sart() {
        vue.afficherGrille();
        String request = input.read("Modififier (lig.col.valeur), supprimer (lig.col), quitter (q) :");
        while(!request.equalsIgnoreCase("q")){
            Matcher matcher = pattern.matcher(request);
            if(matcher.matches()){
                int lig = Integer.parseInt(matcher.group(1))-1;
                int col = Integer.parseInt(matcher.group(2))-1;
                String value = matcher.group(3);
                try {
                    if (value != null) {
                        char val = value.charAt(1);
                        sudoku.setValue(lig, col, val);
                    } else {
                        sudoku.deleteValue(lig, col);
                    }
                }catch(SudokuException e){
                    vue.setMessage(e.getMessage());
                }

            }else{
                vue.setMessage("entrée non valide");
            }
            vue.afficherGrille();
            request = input.read("Modififier (lig.col.valeur), supprimer (lig.col), quitter (q) :");
        }
    }

    @Override
    public void init(String fileName) {
        File file = new File(fileName);
        try(Scanner scan = new Scanner(file)){
            int line = 0;
            while(scan.hasNextLine()){
                int col = 0;
                for(String val : scan.nextLine().split(",")) {
                    char value = val.charAt(0);
                    if(sudoku.isValueValid(value)) {
                        this.sudoku.setValue(line, col, value);
                    }
                    col++;
                }
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SudokuValueException e) {
            throw new RuntimeException(e);
        } catch (SudokuPositionException e) {
            throw new RuntimeException(e);
        }
        sudoku.lock();
    }
}
