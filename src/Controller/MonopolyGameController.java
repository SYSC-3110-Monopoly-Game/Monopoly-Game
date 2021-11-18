package Controller;

import Model.MonopolyGame;
import view.MonopolyGameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MonopolyGameController implements ActionListener {

    private final MonopolyGame game;
    private final MonopolyGameGUI view;

    public MonopolyGameController(MonopolyGame game, MonopolyGameGUI view) {
        this.game=game;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Buy")){
            this.game.buySquare();
        }
        if(b.getText().equals("Sell")){
            this.game.sellSquare();
        }
        if(b.getText().equals("Roll Dice")){
            this.game.playRound();
        }
        if(b.getText().equals("Next Turn")){
            this.game.nextTurn();
        }
        if(b.getText().equals("Build On Property")){
            this.game.checkAvailableBuild();
        }
        if(b.getText().equals("Sell Houses/Hotels")){
            this.game.checkAvailableSell();
        }
    }
}
