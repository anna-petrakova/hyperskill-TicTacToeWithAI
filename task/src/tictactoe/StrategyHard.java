package tictactoe;
import java.util.List;

import static tictactoe.GameState.*;

public class StrategyHard implements PlayerStrategy {
    private Field field;
    private char player;
    private char opponent;
    private int x;
    private int y;

    public StrategyHard(Field field, char player) {
        this.field = field;
        this.player = player;
        this.opponent = (player == 'X' ? 'O' : 'X');
    }

    @Override
    public boolean computeMove() {
        Field testField = new Field(field);
        Move bestMove = minimaxField(testField, player);
        int idxOfBestMove = bestMove.idxOfMove;
        this.x = idxOfBestMove / 3;
        this.y = idxOfBestMove % 3;
        return true;
    }

    private Move minimaxField(Field testField, char currentPlayer) {
        Move bestMove = new Move(0);
        GameState state = testField.analyzeState();
        if (!state.equals(GAME_NOT_FINISHED)) {
            bestMove = getProbablityOfWinInFinishedGame(state);
        } else {
            bestMove = getBestMoveInUnfinishedGame(testField, currentPlayer);
        }
        return bestMove;
    }

    private Move getProbablityOfWinInFinishedGame(GameState state) {
        int probabilityOfWin;
        if (state.equals(X_WINS) && player == 'X') {
            probabilityOfWin = 10;
        } else if (state.equals(X_WINS) && player == 'O') {
            probabilityOfWin = -10;
        } else if (state.equals(O_WINS) && player == 'O') {
            probabilityOfWin = 10;
        } else if (state.equals(O_WINS) && player == 'X') {
            probabilityOfWin = -10;
        } else {
            probabilityOfWin = 0;
        }
        return new Move(probabilityOfWin);
    }

    private Move getBestMoveInUnfinishedGame(Field testField, char currentPlayer) {
        Move bestMove;
        if (currentPlayer == player) {
            bestMove = getMoveWithMaxScoreOnPlayersTurn(testField, currentPlayer);
        } else {
            bestMove = getMoveWithMinScoreOnOpponentsTurn(testField, currentPlayer);
        }
        return bestMove;
    }

    private Move getMoveWithMaxScoreOnPlayersTurn(Field testField, char currentPlayer) {
        Move bestMove = new Move();
        int maxScoreInNextConfig = Integer.MIN_VALUE;
        List<Integer> emptySpots = testField.getIdxsOfFreeSpots();

        for (int idx : emptySpots) {
            testField.set(idx, currentPlayer);
            Move bestMoveInNextConfig = minimaxField(testField, opponent);
            bestMoveInNextConfig.setIdx(idx);

            if (bestMoveInNextConfig.score > maxScoreInNextConfig) {
                maxScoreInNextConfig = bestMoveInNextConfig.score;
                bestMove = bestMoveInNextConfig;
            }
            testField.set(idx, '_');
        }
        return bestMove;
    }

    private Move getMoveWithMinScoreOnOpponentsTurn(Field testField, char currentPlayer) {
        List<Integer> emptySpots = testField.getIdxsOfFreeSpots();

        int minScoreInNextConfig = Integer.MAX_VALUE;

        for (int idx : emptySpots) {
            testField.set(idx, currentPlayer);
            Move bestMoveInNextConfig = minimaxField(testField, player);
            testField.set(idx, '_');

            if (bestMoveInNextConfig.score < minScoreInNextConfig) {
                minScoreInNextConfig = bestMoveInNextConfig.score;
            }
        }
        return new Move(minScoreInNextConfig);
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
        return "Making move level \"hard\"";
    }


    private class Move {
        int score = 0;
        int idxOfMove;

        Move() {}

        Move(int score) {
            this.score = score;
        }

        void setIdx(int idx) {
            this.idxOfMove = idx;
        }
    }
}
