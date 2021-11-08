package Model;

import view.*;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
<<<<<<< Updated upstream
        new MonopolyGameGUI();
        new MonopolyGame();
=======
        MonopolyGame game = new MonopolyGame();
//        ArrayList<PropertySquare> s = new ArrayList<>();
//        s.add((PropertySquare) game.getBoard().getSquares()[1]);
//        s.add((PropertySquare) game.getBoard().getSquares()[2]);
//        s.add((PropertySquare) game.getBoard().getSquares()[4]);
//        s.add((PropertySquare) game.getBoard().getSquares()[5]);
//        s.add((PropertySquare) game.getBoard().getSquares()[6]);
//        s.add((PropertySquare) game.getBoard().getSquares()[7]);
//        s.add((PropertySquare) game.getBoard().getSquares()[9]);
//        s.add((PropertySquare) game.getBoard().getSquares()[10]);
//        s.add((PropertySquare) game.getBoard().getSquares()[11]);
//        new popupWindow(game.players.get(0), s);
        MonopolyGameGUI view = new MonopolyGameGUI(game);
        game.addView(view);
        MonopolyGameController controller = new MonopolyGameController(game, view);
        view.setController(controller);
>>>>>>> Stashed changes
    }
}
