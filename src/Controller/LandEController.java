package Controller;

import Model.MonopolyGame;
import org.xml.sax.SAXException;
import view.MonopolyGameGUI;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LandEController implements ActionListener {
    private MonopolyGame game;

    public LandEController(MonopolyGame game) {this.game = game;}

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b.getText().equals("Back To Previous Game")) {
            try {
                game = new MonopolyGame("testSaveFile.xml");
                MonopolyGameGUI view = new MonopolyGameGUI(game);
                MonopolyGameController controller = new MonopolyGameController(game);
                view.setController(controller);
            } catch (ParserConfigurationException | SAXException | IOException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            }
        }
        if (b.getText().equals("Export Current Game")) {
            this.game.exportGameToXML("testSaveFile.xml");
        }
    }
}
