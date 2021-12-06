package tests;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class GoToJailSquareTest {

    MonopolyGame game = new MonopolyGame("NewGame.xml");
    private final JailSquare jail = new JailSquare("Jail", 8, 50, null);
    private final GoToJailSquare goJail = new GoToJailSquare("go", 0);
    private final Player p  = new Player("player1", 500, false, false, null, game.board.startingSquare(), game.board.startingSquare(), null, null);

    GoToJailSquareTest() throws ParserConfigurationException, IOException, SAXException {
    }


    @Test
    void landOn() {
        goJail.setJail(jail);
        goJail.landOn(p);
        Assertions.assertEquals(jail, p.getCurrentLocation());
    }

    @Test
    void toXML() {
        String xml =
                "<Square type=\"GoToJail\">\n" +
                        "<Name>go</Name>\n" +
                        "<Number>0</Number>\n" +
                        "</Square>\n";
        Assertions.assertEquals(goJail.toXML(), xml);
    }
}