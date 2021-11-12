package tests;

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

    Player player;
    private Square square;
    private Square PropertySquare;
    private Square atSquare;
    private int cashTotal;
    private int rentFee;

    PlayerTest(Square square, Square propertySquare, Square atSquare, int rentFee) {
        this.square = square;
        PropertySquare = propertySquare;
        this.atSquare = atSquare;
        this.rentFee = rentFee;
        this.cashTotal = 300;
    }

    @BeforeEach
    void setUp(String name, Square square) {
        player = new Player(name, square);
    }

    @AfterEach
    void tearDown() {
        player = null;
    }

    @Test
    void getName() {
        Assertions.assertEquals(player.getName(), player.getName());
    }

    @Test
    void testToString() {
        String expected = "Oriental Avenue";
        Assertions.assertEquals(expected, player.toString());
    }

    @Test
    void getLocation() {
        Assertions.assertEquals(player.getLocation(), player.getLocation());
    }

    @Test
    void setLocation() {
        player.setLocation(square);
        Assertions.assertEquals(atSquare, player.getLocation());
    }

    @Test
    void decreaseCash() {
        Assertions.assertEquals(rentFee, player.decreaseCash(cashTotal));
    }

    @Test
    void isBankrupt() {
        Assertions.assertEquals(false, player.isBankrupt());
    }

    @Test
    void increaseCash() {
        Assertions.assertEquals(rentFee, player.increaseCash(cashTotal));
    }

    @Test
    void getCash() {
        Assertions.assertEquals(player.getCash(), player.getCash());
    }

    @Test
    void buyProperty() {
        Square location = new PropertySquare("aSquare", 1,1,1, "black");
        Assertions.assertEquals(player.buyProperty(), player.buyProperty());
    }

    @Test
    void getProperties() {
        Assertions.assertEquals(player.getProperties(), player.getProperties());
    }

    @Test
    void ifWantToBuy() {
        Assertions.assertEquals("If you want to buy " + square.getName() + "? (y/n)",player.ifWantToBuy((PropertySquare) PropertySquare));
    }
}