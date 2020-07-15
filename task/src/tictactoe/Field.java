package tictactoe;

import java.util.ArrayList;
import java.util.List;

import static tictactoe.GameState.*;

public class Field {
    private char[][] field;

    public Field() {
        this.field = new char[][] {
                {'_','_','_'},
                {'_','_','_'},
                {'_','_','_'}
        };
    }

    public Field(Field field) {
        char[][] newField = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newField[i][j] = field.get(i,j);
            }
        }
        this.field = newField;
    }

    public char[][] getField() {
        return field;
    }

    public char get(int x, int y) {
        return field[x][y];
    }

    public void set(int x, int y, char val) {
        this.field[x][y] = val;
    }

    public void set(int idx, char val) {
        this.field[idx / 3][idx % 3] = val;
    }

    public int getXIdxInInternalRepresentation(int x_idx, int y_idx) {
        return 3 - y_idx;
    }

    public int getYIdxInInternalRepresentation(int x_idx, int y_idx) {
        return x_idx - 1;
    }

    public void printState() {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public List<Integer> getIdxsOfFreeSpots() {
        List<Integer> freeSpots = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (field[i / 3][i % 3] == '_') {
                freeSpots.add(i);
            }
        }

        return freeSpots;
    }

    public GameState analyzeState() {
        if (win('X')) {
            return X_WINS;
        } else if (win('O')) {
            return O_WINS;
        } else if (isFinished()) {
            return DRAW;
        } else {
            return GAME_NOT_FINISHED;
        }
    }

    private boolean win(char ch) {
        // rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == ch && field[i][0] == field[i][1] && field[i][1] == field[i][2]) {
                return true;
            }
        }
        // columns
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == ch && field[0][i] == field[1][i] && field[1][i] == field[2][i]) {
                return true;
            }
        }
        // diagonals
        if (field[0][0] == ch && field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            return true;
        }
        if (field[0][2] == ch && field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            return true;
        }
        return false;
    }

    private boolean isFinished() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }
}
