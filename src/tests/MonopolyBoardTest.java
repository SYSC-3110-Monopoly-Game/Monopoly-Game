package tests;

import Model.MonopolyBoard;
import Model.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class MonopolyBoardTest {

    private MonopolyBoard board;

    @BeforeEach
    void setUp() {
        board = new MonopolyBoard();
    }

    @AfterEach
    void tearDown() {
        board = null;
    }

    @Test
    void startingSquare() {
        Assertions.assertEquals(board.getSquares()[0], board.startingSquare());
    }

    @Test
    void getNextSquare() {
        Square next = board.getNextSquare(board.startingSquare(), 3);
        Assertions.assertEquals("Income Tax", next.getName());
    }

    @Test
    void getSquareAt(){
        Assertions.assertEquals(board.getSquareAt(0), board.startingSquare());
    }

    @Test
    void exportToXML() throws ParserConfigurationException, IOException, SAXException {
        board.exportToXML();

        MonopolyBoard b2 = new MonopolyBoard("initialize");

        for(Square s: b2.getSquares()){
            System.out.println(s.toString());
        }
    }
}