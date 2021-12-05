package tests;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoToJailSquareTest {

    private final JailSquare jail = new JailSquare("Jail", 8, 50);
    private final GoToJailSquare goJail = new GoToJailSquare("go", 0);
    private final Player p  = new Player("player1", 500, false, false, null, MonopolyGame.board.startingSquare(), MonopolyGame.board.startingSquare(), null, null);



    @Test
    void landOn() {
        goJail.setJail(jail);
        goJail.landOn(p);
        Assertions.assertEquals(jail, p.getCurrentLocation());
    }
}