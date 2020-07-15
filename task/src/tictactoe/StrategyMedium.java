package tictactoe;

public class StrategyMedium implements PlayerStrategy {
    private Field field;
    private int x;
    private int y;
    private char player;
    private char opponent;
    private PlayerStrategy randomMove;

    public StrategyMedium(Field field, char player) {
        this.field = field;
        randomMove = new StrategyEasy(field, player);
        this.player = player;
        this.opponent = (player == 'X' ? 'O' : 'X');
    }
    @Override
    public boolean computeMove() {
        if (isPossibleToWin(player)) {
            return true;
        } else if (isPossibleToWin(opponent)) {
            return true;
        } else {
            randomMove.computeMove();
            this.x = randomMove.getX();
            this.y = randomMove.getY();
        }
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
        return "Making move level \"medium\"";
    }

    private boolean isPossibleToWin(char player) {
        boolean canWin = false;
        // columns
        for (int i = 0; i < 3; i++) {
            if (hasTwoWinning(i, 0, i, 1, i, 2, player) ||
                hasTwoWinning(i, 1, i, 2, i, 0, player) ||
                hasTwoWinning(i, 0, i, 2, i, 1, player)) {
                canWin = true;
            };
        }
        // rows
        for (int i = 0; i < 3; i++) {
            if (hasTwoWinning(0, i, 1, i, 2, i, player) ||
                hasTwoWinning(1, i, 2, i, 0, i, player) ||
                hasTwoWinning(0, i, 2, i, 1, i, player)) {
                canWin = true;
            }
        }
        // diagonals
        if (hasTwoWinning(0, 0, 1, 1, 2, 2, player) ||
            hasTwoWinning(1, 1, 2, 2, 0, 0, player) ||
            hasTwoWinning(0, 0, 2, 2, 1, 1, player)) {
            canWin = true;
        }

        if (hasTwoWinning(0, 2, 1, 1, 2, 0, player) ||
            hasTwoWinning(1, 1, 2, 0, 0, 2, player) ||
            hasTwoWinning(0, 2, 2, 0, 1, 1, player)) {
            canWin = true;
        }
        return canWin;
    }

    private boolean hasTwoWinning(int x1, int y1, int x2, int y2, int x3, int y3, char sym) {
        boolean win = field.get(x1, y1) == sym && field.get(x2, y2) == sym
                && field.get(x3, y3) == '_';
        if (win) {
            this.x = x3;
            this.y = y3;
            return true;
        }
        return false;
    }
}
