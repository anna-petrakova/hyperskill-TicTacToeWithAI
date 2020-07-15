package tictactoe;

import static tictactoe.GameState.*;

public class TicTacToeGame {
    private Field field;
    private GameState state = NOT_STARTED;
    private PlayerStrategy playerX;
    private PlayerStrategy playerO;
    private PlayerStrategy currentPlayer;

    public TicTacToeGame() {
        this.field = new Field();
    }

    public GameState getState() {
        return state;
    }

    public boolean humanIsPlaying() {
        return currentPlayer instanceof StrategyUser;
    }

    public void initializePlayers(String command) {
        if ("exit".equals(command)) {
            state = FINISHED_EARLY;
            return;
        }
        String[] parts = command.split(" ");
        if (parts.length != 3 || !("start".equals(parts[0]))) {
            System.out.println("Bad parameters!");
            return;
        }
        playerX = setPlayer(parts[1], 'X');
        playerO = setPlayer(parts[2], 'O');
        if (playerX == null || playerO == null) {
            System.out.println("Bad parameters!");
            return;
        }
        currentPlayer = playerX;
        state = GAME_NOT_FINISHED;
        field.printState();
    }

    private PlayerStrategy setPlayer(String player_str, char player) {
        switch (player_str) {
            case "user":
                return new StrategyUser(field, player);
            case "easy":
                return new StrategyEasy(field, player);
            case "medium":
                return new StrategyMedium(field, player);
            case "hard":
                return new StrategyHard(field, player);
            default:
                return null;
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX ? playerO : playerX);
    }

    public void makeUserMove(String coordinates_str) {
        if ("exit".equals(coordinates_str)) {
            state = FINISHED_EARLY;
            return;
        }
        ((StrategyUser) currentPlayer).setCoordinates(coordinates_str);
        boolean successful = currentPlayer.computeMove();
        if (successful) finalizeMove();
    }

    public void makeComputerMove() {
        System.out.println(currentPlayer.getMoveAnnouncement());
        boolean successful = currentPlayer.computeMove();
        if (successful) finalizeMove();
    }

    private void finalizeMove() {
        int x = currentPlayer.getX();
        int y = currentPlayer.getY();

        field.set(x, y, currentPlayer.getPlayerSymbol());
        field.printState();
        switchPlayer();
        state = field.analyzeState();
        if (!state.equals(GAME_NOT_FINISHED)) {
            System.out.println(state.getAnnouncement());
        }
    }
}
