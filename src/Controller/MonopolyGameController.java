package Controller;

import Model.Dice;
import Model.MonopolyGame;
import Model.PropertySquare;
import view.MonopolyGameGUI;

import javax.swing.*;
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
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("Buy")){
            this.game.buySquare();
        }
        if(b.getText().equals("Sell")){
            //this.game.sellProperty(PropertySquare p);
        }
        if(b.getText().equals("Roll Dice")){
            this.game.playRound();
        }
        if(b.getText().equals("Next Turn")){
            this.game.nextTurn();
        }
    }



    public Dice getdice(){
        return game.getDice();
    }
}
