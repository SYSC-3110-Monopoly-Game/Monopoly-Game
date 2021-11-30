package tests;

import Model.FreeParkingSquare;
import Model.MonopolyBoard;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class FreeParkingSquareTest {

    private final MonopolyBoard board = new MonopolyBoard("NewGame.xml");

    FreeParkingSquareTest() throws ParserConfigurationException, IOException, SAXException {
    }


    @Test
    void landOn() {
        FreeParkingSquare freeParking = new FreeParkingSquare("FreeParking", 100);
        Player p = new Player("player1", board.startingSquare());
        freeParking.landOn(p);
        Assertions.assertEquals(p.getCurrentLocation(), freeParking);
    }
}