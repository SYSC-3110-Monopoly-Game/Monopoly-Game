package Model;

import java.util.ArrayList;

public class main {
    public main() {

        MonopolyGame game = new MonopolyGame();
        game.getBoard().displayBoard();
        while (!game.playerWin()) {                             // if no one wins; go into the loop
            game.printPlayersInfo();                            // show all players' information
            ArrayList<Player> tempList = game.playRound();      // play a round
            game.refreshPlayer(tempList);                       // refresh player list from the edited temp player list
        }                                                       // if some one wins
        game.closeScanner();
        System.out.println("Game Over, the winner is: " + game.getWinner().getName());  // show who is the winner
    }

    public static void main(String[] args) {
        new main();
    }
}
