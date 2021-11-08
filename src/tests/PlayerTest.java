package tests;

import Model.MonopolyBoard;
import Model.MonopolyGame;
import Model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    MonopolyGame game;
    ArrayList<Player> player;
    MonopolyBoard board;

    @BeforeEach
    void setUp() {
        game = new MonopolyGame();
        player = game.players;
        board = game.board;
    }

    @AfterEach
    void tearDown() {
        game = null;
        player = null;
        board = null;
    }

    @Test
    void testToString() {
    }

    @Test
    void getName() {
    }

    @Test
    void getLocation() {
    }

    @Test
    void setLocation() {
    }

    @Test
    void decreaseCash() {
    }

    @Test
    void isBankrupt() {
    }

    @Test
    void increaseCash() {
    }

    @Test
    void getCash() {
    }

    @Test
    void buyProperty() {
        player.get(0).buyProperty(board.getSquares()[3]);
        Assertions.assertEquals("Baltic Avenue", player.get(0).getProperties().get(0).getName());
    }

    @Test
    void getProperties() {
    }

    @Test
    void ifWantToBuy() {
    }

    @Test
    void sellProperty() {
        player.get(0).buyProperty(board.getSquares()[3]);
        player.get(0).sellProperty(board.getSquares()[3]);
        Assertions.assertEquals(275, player.get(0).getCash());
    }

    @Test
    void testToString1() {
    }
}