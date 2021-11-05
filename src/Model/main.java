package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public main() {

        MonopolyGame game = new MonopolyGame();
        game.getBoard().displayBoard();
        while (!game.playerWin()) {
            game.printPlayersInfo();
            ArrayList<Player> tempList = game.playRound();
            game.refreshPlayer(tempList);
        }
        game.closeScanner();
        System.out.println("Game Over, the winner is: " + game.getWinner().getName());
    }

    public static void main(String[] args) {
        new main();
    }
}
