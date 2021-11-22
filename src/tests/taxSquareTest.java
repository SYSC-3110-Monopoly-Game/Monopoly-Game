package tests;

import Model.TaxSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class taxSquareTest {

    private final TaxSquare square = new TaxSquare("go", 0, 100);
    private final Player p = new Player("player", square);
    private final int playerinitialcash = 350;

    @Test
    void getTax() {
        Assertions.assertEquals(100, square.getTax());
    }

    @Test
    void landOn() {
        square.landOn(p);
        Assertions.assertEquals(playerinitialcash-square.getTax(), p.getCash());
    }

}