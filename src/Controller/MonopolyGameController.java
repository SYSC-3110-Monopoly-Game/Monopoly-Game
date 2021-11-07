package Controller;

import Model.MonopolyGame;
import view.MonopolyGameGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MonopolyGameController implements ActionListener {

    private MonopolyGame game;
    private MonopolyGameGUI view;

    public MonopolyGameController(MonopolyGame game, MonopolyGameGUI view) {
        this.game=game;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
