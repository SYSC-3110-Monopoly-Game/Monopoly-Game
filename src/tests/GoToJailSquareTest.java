package tests;

import Model.GoSquare;
import Model.GoToJailSquare;
import Model.JailSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class GoToJailSquareTest {

    private JailSquare jail = new JailSquare("Jail", 8, 50);
    private GoToJailSquare goJail = new GoToJailSquare("go", 0, jail);
    private Player p = new Player("player", goJail);


    @Test
    void landOn() {
        goJail.landOn(p);
        Assertions.assertEquals(jail, p.getCurrentLocation());
    }
}