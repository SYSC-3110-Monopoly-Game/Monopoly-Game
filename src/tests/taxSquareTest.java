package tests;

import Model.MonopolyGame;
import Model.TaxSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class taxSquareTest {

    private final TaxSquare square = new TaxSquare("go", 0, 100);
    private final Player p  = new Player("player1", 500, false, false, null, square, square, null, null);

    private final int playerInitialCash = p.getCash();

    @Test
    void getTax() {
        Assertions.assertEquals(100, square.getTax());
    }

    @Test
    void landOn() {
        square.landOn(p);
        Assertions.assertEquals(playerInitialCash-square.getTax(), p.getCash());
    }

}