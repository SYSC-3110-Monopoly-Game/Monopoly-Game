package tests;

import Model.MonopolyGame;
import Model.Player;
import Model.PropertySquare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PropertySquareTest {

    private PropertySquare property;

    @BeforeEach
    void setUp() {
        property = new PropertySquare("p", 0, 50, 50, Color.BLACK);
    }

    @AfterEach
    void tearDown() {
        property = null;
    }

    @Test
    void getColor() {
        Assertions.assertEquals(Color.BLACK, property.getColor());
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(50, property.getPrice());
    }

    @Test
    void getRentFee() {
        Assertions.assertEquals(50, property.getRentFee());
    }

    @Test
    void getOwner() {
        assertNull(property.getOwner());
    }

    @Test
    void setOwner() {
        Player p = new Player("p", property);
        property.setOwner(p);
        Assertions.assertEquals(p, property.getOwner());
    }

    @Test
    void landOn() {
        new MonopolyGame();
        Player p1 = new Player("p1", property);
        Player p2 = new Player("p2", property);
        property.setOwner(p1);
        property.landOn(p2);
        Assertions.assertEquals(350, p1.getCash());
        Assertions.assertEquals(250, p2.getCash());
    }
}