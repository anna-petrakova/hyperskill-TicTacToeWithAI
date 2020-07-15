package tictactoe;

import java.util.Random;

public class StrategyEasy implements PlayerStrategy {
    private Field field;
    private Random random = new Random();
    private int x;
    private int y;
    private char player;

    public StrategyEasy(Field field, char player) {
        this.field = field;
        this.player = player;
    }

    @Override
    public boolean computeMove() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (field.get(x, y) != '_');

        this.x = x;
        this.y = y;

        return true;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getPlayerSymbol() {
        return player;
    }

    @Override
    public String getMoveAnnouncement() {
        return "Making move level \"easy\"";
    }
}
