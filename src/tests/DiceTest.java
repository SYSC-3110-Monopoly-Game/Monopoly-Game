package tests;

import model.Dice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {

    Dice dice;

    @BeforeEach
    void setUp() {
        dice = new Dice();
    }

    @AfterEach
    void tearDown() {
        dice = null;
    }

    @Test
    void testRollDiceAndGetTotalValue() {
        int values[] = dice.rollDice();
        Assertions.assertEquals(values[0] + values[1], dice.getTotalValue());
    }

    @Test
    void testToString() {
        int values[] = dice.rollDice();
        String expected = "Dice #1 = " + values[0] + "\t\t" + "Dice #2 = " + values[1];
        Assertions.assertEquals(expected, dice.toString());
    }
}