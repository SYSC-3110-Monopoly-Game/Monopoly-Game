package tests;

import Model.AIPlayer;
import Model.GoSquare;
import Model.MonopolyGame;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoSquareTest {

    private final GoSquare goSquare = new GoSquare("go", 0, 100);
    private final Player p  = new Player("player1", 500, false, false, null, MonopolyGame.board.startingSquare(), MonopolyGame.board.startingSquare(), null, null);


    @Test
    void getAddMoney() {
        Assertions.assertEquals(100, goSquare.getAddMoney());
    }

    @Test
    void landOn() {
        goSquare.landOn(p);
        Assertions.assertEquals(goSquare, p.getCurrentLocation());
    }
}