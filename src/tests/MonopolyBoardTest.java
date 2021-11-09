package tests;

import Model.MonopolyBoard;
import Model.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}