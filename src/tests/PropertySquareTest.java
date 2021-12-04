package tests;

import Model.AIPlayer;
import Model.MonopolyGame;
import Model.Player;
import Model.PropertySquare;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PropertySquareTest {

    private PropertySquare property;
    private  Player p1;
    private  Player p2;

    private final int playerinitialcash = 350;

    @BeforeEach
    void setUp() {
        property = new PropertySquare("p", 0, 50, 50, Color.BLACK);
        p1= new Player("player1", 500, false, false, null, property, property,null, property);
        p2= new Player("player2", 500, false, false, null, property, property,null, property);

    }

    @AfterEach
    void tearDown() {
        property = null;
    }

    @Test
    void getHousePrice(){
        Assertions.assertEquals(50, property.getHousePrice());
    }

    @Test
    void getHotelPrice() {
        Assertions.assertEquals(250, property.getHotelPrice());
    }

    @Test
    void buildHouse() {
        property.setOwner(p1);
        Assertions.assertEquals(50, property.buildHouse());
    }

    @Test
    void buildHotel() {
        property.setOwner(p1);
        property.buildHouse();
        Assertions.assertEquals(200, property.buildHotel());
    }

    @Test
    void hasHouses() {
        property.setOwner(p1);
        property.buildHotel();
        assertTrue(property.hasHouses());
    }

    @Test
    void hasHotel() {
        property.setOwner(p1);
        property.buildHotel();
        assertTrue(property.hasHotel());
    }

    @Test
    void sellHotel() {
        property.sellHotel();
        assertFalse(property.hasHotel());
    }

    @Test
    void sellHouse() {
        property.sellHouse();
        property.sellHouse();
        property.sellHouse();
        property.sellHouse();
        assertFalse(property.hasHouses());
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
        property.setOwner(p2);
        Assertions.assertEquals(p2, property.getOwner());
    }

    @Test
    void landOn() throws ParserConfigurationException, IOException, SAXException {
        new MonopolyGame("NewGame.xml");
        property.setOwner(p1);
        property.landOn(p2);
        Assertions.assertEquals(playerinitialcash+50, p1.getCash());
        Assertions.assertEquals(playerinitialcash-50, p2.getCash());
    }
}