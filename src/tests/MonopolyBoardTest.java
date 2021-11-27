package tests;

import Model.MonopolyBoard;
import Model.MonopolyGame;
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
    private MonopolyGame game;

    @BeforeEach
    void setUp() {
        game = new MonopolyGame();
        board = MonopolyGame.board;
    }

    @AfterEach
    void tearDown() {
        game = null;
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
    void exportANDImportToXML() throws ParserConfigurationException, IOException, SAXException {}
}