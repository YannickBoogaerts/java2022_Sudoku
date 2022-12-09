package be.technifutur.sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    private char value = SudokuModel.EMPTY;
    private boolean lock = false;

    private Map<String,Set<Character>> zones= new HashMap<>();

    public char getValue() {
        return value;
    }

    public boolean setValue(char value) {
        boolean modif = false;
        if(!isLock() && this.value != value) {
            this.value = value;
            modif = true;
        }
        return modif;
    }

    public boolean isLock() {
        return lock;
    }

    public void lock() {
        if (!isEmpty())
            this.lock = true;
    }

    public boolean isEmpty() {
        return this.value == SudokuModel.EMPTY;
    }

    public boolean clear(){
        return setValue(SudokuModel.EMPTY);
    }


    public static void main(String[] args) {
        Cell cell = new Cell();
        Set<Character> zonel = new HashSet<>();
        Set<Character> zonec = new HashSet<>();
       //zone.add('1');
        cell.addZone("ligne", zonel);
        cell.addZone("colonne", zonec);



        System.out.println(zonel == cell.getZone("ligne"));
        System.out.println(zonec == cell.getZone("colonne"));
    }

    public Set<Character> getZone(String name) {
        return this.zones.get(name);
    }

    public void addZone(String name, Set<Character> zone) {
        this.zones.put(name,zone);
    }
}
