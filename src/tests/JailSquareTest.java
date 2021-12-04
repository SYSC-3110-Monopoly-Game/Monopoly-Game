package tests;

import Model.AIPlayer;
import Model.JailSquare;
import Model.MonopolyGame;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JailSquareTest {

    private final JailSquare s= new JailSquare("go", 0, 100);
    private final Player p  = new Player("player1", 500, false, false, null, s, s, null, null);



    @Test
    void goJail() {
        s.goJail(p);
        assertTrue(s.getMap().containsKey(p));
    }

    @Test
    void addCounter() {
        s.goJail(p);
        s.addCounter(p);
        Assertions.assertEquals(1, s.getMap().get(p));
    }

    @Test
    void goOutJail() {
        s.goOutJail(p);
        assertFalse(s.getMap().containsKey(p));
    }

    @Test
    void landOn() {
        s.landOn(p);
        assertFalse(s.getMap().containsKey(p));
        Assertions.assertEquals(s, p.getCurrentLocation());
    }

    @Test
    void getMap() {
        Assertions.assertEquals(s.map, s.getMap());
    }

}