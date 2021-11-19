package Model;

import Controller.MonopolyGameController;
import view.*;

public class Main {

    public static void main(String[] args) {
        MonopolyGame game = new MonopolyGame();
        MonopolyGameGUI view = new MonopolyGameGUI(game);
        game.addView(view);
        MonopolyGameController controller = new MonopolyGameController(game, view);
        view.setController(controller);
    }
}
