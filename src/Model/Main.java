package Model;

import Controller.MonopolyGameController;
import org.xml.sax.SAXException;
import view.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        MonopolyGame game = new MonopolyGame("testSaveFile.xml");
        MonopolyGameGUI view = new MonopolyGameGUI(game);
        MonopolyGameController controller = new MonopolyGameController(game);
        view.setController(controller);
    }
}
