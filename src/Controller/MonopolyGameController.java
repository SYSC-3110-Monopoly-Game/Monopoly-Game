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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
        }
        if(b.getText().equals("Sell")){
            try {
                this.game.sellSquare();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
        }
        if(b.getText().equals("Roll Dice")){
            try {
                this.game.playRound();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
        }
        if(b.getText().equals("Next Turn")){
            try {
                this.game.nextTurn();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
        }
        if(b.getText().equals("Build On Property")){
            this.game.checkAvailableBuild();
        }
        if(b.getText().equals("Sell Houses/Hotels")){
            this.game.checkAvailableSell();
        }
        if(b.getText().equals("Start new game")){
            try {
                game = new MonopolyGame("NewGame.xml");
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            }
        }
        if(b.getText().equals("Load previous game")){
            try {
                game = new MonopolyGame("testSaveFile.xml");
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(b.getText().equals("Back To Previous Game")){
            try {
                this.game.loadGame("testSaveFile.xml");
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(b.getText().equals("Export Current Game")){
            this.game.exportGameToXML("testSaveFile.xml");
        }
    }
}
