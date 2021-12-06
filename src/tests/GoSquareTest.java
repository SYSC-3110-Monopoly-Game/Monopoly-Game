package tests;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class GoSquareTest {

    MonopolyGame game = new MonopolyGame("NewGame.xml");
    private final GoSquare goSquare = new GoSquare("go", 0, 100);
    private final Player p  = new Player("player1", 500, false, false, null,
            game.board.startingSquare(), game.board.startingSquare(), null, null);

    GoSquareTest() throws ParserConfigurationException, IOException, SAXException {
    }


    @Test
    void getAddMoney() {
        Assertions.assertEquals(100, goSquare.getAddMoney());
    }

    @Test
    void landOn() {
        goSquare.landOn(p);
        Assertions.assertEquals(goSquare, p.getCurrentLocation());
    }

    @Test
    void toXML() {
        String s =
               "<Square type=\"Go\">\n" +
                       "<Name>go</Name>\n" +
                       "<Number>0</Number>\n" +
                       "<Price>100</Price>\n" +
                       "</Square>\n";
        Assertions.assertEquals(goSquare.toXML(), s);
    }
}