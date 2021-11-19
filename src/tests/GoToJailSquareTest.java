package tests;

import Model.GoToJailSquare;
import Model.JailSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoToJailSquareTest {

    private final JailSquare jail = new JailSquare("Jail", 8, 50);
    private final GoToJailSquare goJail = new GoToJailSquare("go", 0, jail);
    private final Player p = new Player("player", goJail);


    @Test
    void landOn() {
        goJail.landOn(p);
        Assertions.assertEquals(jail, p.getCurrentLocation());
    }
}