package tests;

import Model.MonopolyGame;
import Model.Player;
import Model.PropertySquare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    private MonopolyGame game;
    private Player player;

    private final int playerinitialcash = 350;

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
        Assertions.assertEquals(player.getCash(), playerinitialcash);
    }

    @Test
    void decreaseCash() {
        player.decreaseCash(100);
        Assertions.assertEquals(playerinitialcash-100, player.getCash());
    }

    @Test
    void increaseCash() {
        player.increaseCash(100);
        Assertions.assertEquals(playerinitialcash+100, player.getCash());
    }

    @Test
    void isBankrupt() {
        Assertions.assertFalse(player.isBankrupt());
    }

    @Test
    void setCurrentLocation() {
        player.setCurrentLocation(MonopolyGame.board.getSquares()[2]);
        Assertions.assertEquals(MonopolyGame.board.getSquares()[2], player.getCurrentLocation());
    }

    @Test
    void getLastLocation() {
        player.setCurrentLocation(MonopolyGame.board.getSquares()[2]);
        Assertions.assertEquals(MonopolyGame.board.startingSquare(), player.getLastLocation());
    }

    @Test
    void getCurrentLocation() {
        player.setCurrentLocation(MonopolyGame.board.getSquares()[2]);
        Assertions.assertEquals(MonopolyGame.board.getSquares()[2], player.getCurrentLocation());
    }

    @Test
    void setANDgetSelectedSquare() {
        player.setSelectedSquare((PropertySquare) MonopolyGame.board.getSquareAt(2));
        Assertions.assertEquals((PropertySquare) MonopolyGame.board.getSquareAt(2), player.getSelectedSquare());
    }

    @Test
    void setANDgetDecision() {
        player.setDecision("test");
        Assertions.assertEquals("test", player.getDecision());
    }

    @Test
    void getProperties() {
        Assertions.assertEquals(0, player.getProperties().size());
    }

    @Test
    void buyProperty() {
        player.setCurrentLocation(MonopolyGame.board.getSquares()[2]);
        player.buyProperty(player.getCurrentLocation());
        Assertions.assertEquals(player.getCurrentLocation(), player.getProperties().get(0));
    }

    @Test
    void sellProperty() {
        player.setCurrentLocation(MonopolyGame.board.getSquares()[2]);
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

    @Test
    void getPropertyFromName() {
        PropertySquare p = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        player.buyProperty(p);
        Assertions.assertEquals(p, player.getPropertyFromName("test"));
    }

    @Test
    void hasWholeSet() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        player.buyProperty(p1);
        player.buyProperty(p2);
        ArrayList<PropertySquare> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        Assertions.assertEquals(test, player.hasWholeSet());
    }

    @Test
    void hasBuilding() {
        Assertions.assertEquals(new ArrayList<PropertySquare>(), player.hasBuilding());
    }

    @Test
    void removeRailroadUtility() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLUE);
        ArrayList<PropertySquare> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        ArrayList<PropertySquare> test2 = test;
        test2.remove(p1);

        Assertions.assertEquals(test2, player.removeRailroadUtility(test));
    }

    @Test
    void countNumber() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        player.buyProperty(p1);
        player.buyProperty(p2);
        Assertions.assertEquals(2, player.countNumber(Color.BLACK));
    }

    @Test
    void getAvailableProperties() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        ArrayList<PropertySquare> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        Assertions.assertEquals(test, player.getAvailableProperties(test));
    }

    @Test
    void buildH() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        player.buyProperty(p1);
        player.setSelectedSquare(p1);
        Assertions.assertEquals(player.buildHouseOrHotel("House"), 50);
        Assertions.assertEquals(new ArrayList<PropertySquare>(List.of(p1)), player.hasBuilding());
    }

    @Test
    void sellH() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK);
        player.buyProperty(p1);
        player.setSelectedSquare(p1);
        player.buildHouseOrHotel("House");
        Assertions.assertEquals(25, player.sellHouseOrHotel("House"));
        Assertions.assertEquals(new ArrayList<PropertySquare>(), player.hasBuilding());
    }

}