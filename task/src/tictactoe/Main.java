package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean gameIsInSession = true;

        while (gameIsInSession) {

            TicTacToeGame game = new TicTacToeGame();

            while (game.getState().equals(GameState.NOT_STARTED)) {
                System.out.print("Input command: ");
                String command = scanner.nextLine();
                game.initializePlayers(command);
            }

            while (game.getState().equals(GameState.GAME_NOT_FINISHED)) {
                if (game.humanIsPlaying()) {
                    System.out.print("Enter the coordinates: ");
                    String coordinates_str = scanner.nextLine();

                    game.makeUserMove(coordinates_str);
                } else {
                    game.makeComputerMove();
                }
            }

            if (game.getState().equals(GameState.FINISHED_EARLY)) {
                gameIsInSession = false;
            }
        }
    }
}
