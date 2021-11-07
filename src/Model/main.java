package Model;

import Controller.MonopolyGameController;
import view.*;

public class main {

    public static void main(String[] args) {
        MonopolyGame game = new MonopolyGame();
        MonopolyGameGUI view = new MonopolyGameGUI(game);
        MonopolyGameController controller = new MonopolyGameController(game, view);
        view.setController(controller);
    }
}
