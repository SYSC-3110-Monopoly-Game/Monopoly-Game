package tests;

import Model.MonopolyGame;
import Model.Player;
import Model.PropertySquare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import view.Enums;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    private final int playerinitialcash = 600;
    private MonopolyGame game;
    private Player player;

    @BeforeEach
    void setUp() throws ParserConfigurationException, IOException, SAXException {
        game = new MonopolyGame("NewGame.xml");
        player = game.getPlayerInTurn();
    }

    @AfterEach
    void tearDown() {
        game = null;
        player = null;
    }

    @Test
    void getName() {
        Assertions.assertEquals("" + 1, player.getName());
    }

    @Test
    void increaseCash() {
        player.increaseCash(100);
        Assertions.assertEquals(playerinitialcash + 100, player.getCash());
    }

    @Test
    void decreaseCash() {
        player.decreaseCash(100);
        Assertions.assertEquals(playerinitialcash - 100, player.getCash());
    }

    @Test
    void testToString() {
    }

    @Test
    void getCash() {
        Assertions.assertEquals(player.getCash(), playerinitialcash);
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
    void setANDgetSelectedSquare() {
        player.setSelectedSquare((PropertySquare) game.board.getSquareAt(2));
        Assertions.assertEquals((PropertySquare) game.board.getSquareAt(2), player.getSelectedSquare());
    }

    @Test
    void setANDgetDecision() {
        player.setDecision(Enums.SELL);
        Assertions.assertEquals(Enums.SELL, player.getDecision());
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

    @Test
    void getPropertyFromName() {
        PropertySquare p = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        player.buyProperty(p);
        Assertions.assertEquals(p, player.getPropertyFromName("test"));
    }

    @Test
    void hasWholeSet() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
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
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLUE, 0, 0, 0, null);
        ArrayList<PropertySquare> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        ArrayList<PropertySquare> test2 = test;
        test2.remove(p1);

        Assertions.assertEquals(test2, player.removeRailroadUtility(test));
    }

    @Test
    void countNumber() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        player.buyProperty(p1);
        player.buyProperty(p2);
        Assertions.assertEquals(2, player.countNumber(Color.BLACK));
    }

    @Test
    void getAvailableProperties() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        PropertySquare p2 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 0, 0, 0, null);
        ArrayList<PropertySquare> test = new ArrayList<>();
        test.add(p1);
        test.add(p2);
        Assertions.assertEquals(test, player.getAvailableProperties(test));
    }

    @Test
    void buildH() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 50, 0, 0, null);
        player.buyProperty(p1);
        player.setSelectedSquare(p1);
        Assertions.assertEquals(player.buildH(Enums.HOUSE), 50);
        Assertions.assertEquals(new ArrayList<PropertySquare>(List.of(p1)), player.hasBuilding());
    }

    @Test
    void sellH() {
        PropertySquare p1 = new PropertySquare("test", 0, 50, 50, Color.BLACK, 50, 0, 50, null);
        player.buyProperty(p1);
        player.setSelectedSquare(p1);
        player.buildH(Enums.HOUSE);
        Assertions.assertEquals(25, player.sellH(Enums.HOUSE));
        Assertions.assertEquals(new ArrayList<PropertySquare>(), player.hasBuilding());
    }

}