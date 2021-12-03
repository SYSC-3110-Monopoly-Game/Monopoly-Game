package Controller;

import Model.MonopolyGame;
import org.xml.sax.SAXException;

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
        if(b.getText().equals("Buy")){
            try {
                this.game.buySquare();
            } catch (IOException | ParserConfigurationException | SAXException ex) {
                ex.printStackTrace();
            }
        }
        if(b.getText().equals("Sell")){
            try {
                this.game.sellSquare();
            } catch (IOException | ParserConfigurationException | SAXException ioException) {
                ioException.printStackTrace();
            }
        }
        if(b.getText().equals("Roll Dice")){
            try {
                this.game.playRound();
            } catch (IOException | ParserConfigurationException | SAXException ioException) {
                ioException.printStackTrace();
            }
        }
        if(b.getText().equals("Next Turn")){
            try {
                this.game.nextTurn();
            } catch (IOException | SAXException | ParserConfigurationException ex) {
                ex.printStackTrace();
            }
        }
        if(b.getText().equals("Build On Property")){
            this.game.checkAvailableBuild();
        }
        if(b.getText().equals("Sell Houses/Hotels")){
            this.game.checkAvailableSell();
        }

    }
}
