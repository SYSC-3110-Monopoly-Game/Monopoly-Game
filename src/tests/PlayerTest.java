package tests;

import Model.MonopolyGame;
import Model.Player;
import Model.PropertySquare;
import Model.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

class PlayerTest {

    private MonopolyGame game;
    private Player player;

    @BeforeEach
    void setUp() {
        game = new MonopolyGame();
        player = game.getPlayerInTurn();
    }

    @AfterEach
    void tearDown() {
        game = null;
        player = null;
    }

    @Test
    void getName() {
        Assertions.assertEquals(""+1, player.getName());
    }

    @Test
    void testToString() {}

    @Test
    void getCash() {
        Assertions.assertEquals(player.getCash(), 300);
    }

    @Test
    void decreaseCash() {
        player.decreaseCash(100);
        Assertions.assertEquals(200, player.getCash());
    }

    @Test
    void increaseCash() {
        player.increaseCash(100);
        Assertions.assertEquals(400, player.getCash());
    }

    @Test
    void isBankrupt() {
        Assertions.assertFalse(player.isBankrupt());
    }

    @Test
    void setCurrentLocation() {
        player.setCurrentLocation(game.board.getSquares()[2]);
        Assertions.assertEquals(game.board.getSquares()[2], player.getCurrentLocation());
    }

    @Test
    void getLastLocation() {
        player.setCurrentLocation(game.board.getSquares()[2]);
        Assertions.assertEquals(game.board.startingSquare(), player.getLastLocation());
    }

    @Test
    void getCurrentLocation() {
        player.setCurrentLocation(game.board.getSquares()[2]);
        Assertions.assertEquals(game.board.getSquares()[2], player.getCurrentLocation());
    }


    @Test
    void getProperties() {
        Assertions.assertEquals(0, player.getProperties().size());
    }

    @Test
    void buyProperty() {
        player.setCurrentLocation(game.board.getSquares()[2]);
        player.buyProperty(player.getCurrentLocation());
        Assertions.assertEquals(player.getCurrentLocation(), player.getProperties().get(0));
    }

    @Test
    void sellProperty() {
        player.setCurrentLocation(game.board.getSquares()[2]);
        player.sellProperty(player.getCurrentLocation());
        Assertions.assertEquals(0, player.getProperties().size());
    }

    @Test
    void isInJail() {
        Assertions.assertFalse(player.isInJail());
    }

    @Test
    void setInJail() {
        player.setInJail(true);
        Assertions.assertTrue(player.isInJail());
    }
}