package tests;

import Model.GoToJailSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoToJailSquareTest {

    private GoToJailSquare goJail;
    private Player p;

    GoToJailSquareTest(GoToJailSquare goJail, Player p) {
        this.goJail = goJail;
        this.p = p;
    }

    @Test
    void landOn() {
        goJail.landOn(p);
        Assertions.assertEquals(p, "Oh no! You have to go to jail player " + p.getName());
    }
}