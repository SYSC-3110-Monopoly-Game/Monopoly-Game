package tests;

import Model.Player;
import Model.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PlayerTest {

    Player player;
    private Player s;
    private Player square;
    private Square PropertySquare;
    private ArrayList<Model.PropertySquare> squaresOwned;
    private Square atSquare;
    private Object Square;
    private int cashTotal;
    private int rentFee;

    PlayerTest(Player s, Player square, Square propertySquare, ArrayList<Model.PropertySquare> squaresOwned, int rentFee) {
        this.s = s;
        this.square = square;
        PropertySquare = propertySquare;
        this.squaresOwned = squaresOwned;
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
    void setLocation(Square nextSquare) {
        this.atSquare = nextSquare;
        Assertions.assertEquals(nextSquare, player.setLocation(Square));
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
        Assertions.assertEquals(player.buyProperty(location), player.buyProperty(location));
    }

    @Test
    void getProperties() {
        Assertions.assertEquals(player.getProperties(), player.getProperties());
    }

    @Test
    void ifWantToBuy() {
        Assertions.assertEquals("If you want to buy " + square.getName() + "? (y/n)",player.ifWantToBuy((Model.PropertySquare) PropertySquare));
    }
}
