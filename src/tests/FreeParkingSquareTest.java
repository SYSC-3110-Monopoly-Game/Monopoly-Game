package tests;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class FreeParkingSquareTest {

    private final MonopolyGame game = new MonopolyGame("NewGame.xml");

    FreeParkingSquareTest() throws ParserConfigurationException, IOException, SAXException {
    }


    @Test
    void landOn() {
        FreeParkingSquare freeParking = new FreeParkingSquare("FreeParking", 100);
        Player p  = new Player("player1", 500, false, false, null,
                game.board.startingSquare(), game.board.startingSquare(), null, null);
        freeParking.landOn(p);
        Assertions.assertEquals(p.getCurrentLocation(), freeParking);
    }
}