package Model;

import Controller.MonopolyGameController;
import org.xml.sax.SAXException;
import view.MonopolyGameGUI;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFrame beforeGame;

        //before game
        beforeGame = new JFrame();
        beforeGame.setSize(500,300);
        beforeGame.setLocation(500,300);

        JPanel full = new JPanel(new GridLayout(2,1));
        JPanel buttons = new JPanel(new GridLayout(1,2));
        JLabel text = new JLabel("Welcome to Monopoly Game!");
        JButton newGame = new JButton("Start new game");
        newGame.addActionListener(e -> {
            MonopolyGame game = null;
            try {
                game = new MonopolyGame(Constants.NEW_FILE_PATH);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                ex.printStackTrace();
            }
            assert game != null;
            MonopolyGameGUI view = new MonopolyGameGUI(game);
            MonopolyGameController controller = new MonopolyGameController(game);
            view.setController(controller);
            beforeGame.dispose();
        });
        JButton load = new JButton("Load previous game");
        load.addActionListener(e -> {
            MonopolyGame game = null;
            try {
                game = new MonopolyGame(Constants.SAVED_FILE_PATH);
            } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
            assert game != null;
            MonopolyGameGUI view = new MonopolyGameGUI(game);
            MonopolyGameController controller = new MonopolyGameController(game);
            view.setController(controller);
            beforeGame.dispose();
        });

        buttons.add(newGame);
        buttons.add(load);
        full.add(text);
        full.add(buttons);
        beforeGame.add(full);
        beforeGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        beforeGame.setVisible(true);

    }
}
