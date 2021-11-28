package tests;

import Model.AIPlayer;
import Model.MonopolyGame;
import Model.PropertySquare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AIPlayerTest {

    AIPlayer ai;

    @BeforeEach
    void setUp() throws ParserConfigurationException, IOException, SAXException {
        MonopolyGame game = new MonopolyGame("NewGame.xml");
        ai = new AIPlayer("1", MonopolyGame.board.startingSquare());
    }

    @AfterEach
    void tearDown() {
        ai = null;
    }

    @Test
    void getRandomSquare() {
        PropertySquare p = new PropertySquare("test", 2, 50, 50, Color.BLACK);
        ai.buyProperty(p);
        Assertions.assertEquals(p, ai.getRandomSquare(ai.getProperties()));
    }

    @Test
    void getSellProperties() {
        PropertySquare p = new PropertySquare("test", 2, 50, 50, Color.BLACK);
        ai.buyProperty(p);
        Assertions.assertEquals(ai.getProperties(), ai.getSellProperties());
    }

    @Test
    void sellSomeThing() {
        PropertySquare p = new PropertySquare("test", 2, 350, 50, Color.BLACK);
        ai.buyProperty(p);
        ai.decreaseCash(10);
        ai.sellSomeThing();
        Assertions.assertEquals(0, ai.getProperties().size());
    }

    @Test
    void buildBuildings() {
        Assertions.assertFalse(ai.buildBuildings());
    }
}