package Controller;

import Model.MonopolyGame;
import org.xml.sax.SAXException;
import view.Enums;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MonopolyGameController implements ActionListener {

    private MonopolyGame game;

    public MonopolyGameController(MonopolyGame game) {
        this.game=game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals(Enums.BUY.toString())){
            try {
                this.game.buySquare();
            } catch (IOException | ParserConfigurationException | SAXException ex) {
                ex.printStackTrace();
            }
        }
        if(b.getText().equals(Enums.SELL.toString())){
            try {
                this.game.sellSquare();
            } catch (IOException | ParserConfigurationException | SAXException ioException) {
                ioException.printStackTrace();
            }
        }
        if(b.getText().equals(Enums.ROLL_DICE.toString())){
            try {
                this.game.playRound();
            } catch (IOException | ParserConfigurationException | SAXException ioException) {
                ioException.printStackTrace();
            }
        }
        if(b.getText().equals(Enums.NEXT_TURN.toString())){
            try {
                this.game.nextTurn();
            } catch (IOException | SAXException | ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        }
        if(b.getText().equals(Enums.BUILD_ON_PROPERTY.toString())){
            this.game.checkAvailableBuild();
        }
        if(b.getText().equals(Enums.SELL_HOUSES_HOTELS.toString())){
            this.game.checkAvailableSell();
        }

    }
}
