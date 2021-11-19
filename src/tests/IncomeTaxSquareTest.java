package tests;

import Model.IncomeTaxSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncomeTaxSquareTest {

    private final IncomeTaxSquare square = new IncomeTaxSquare("go", 0, 100);
    private final Player p = new Player("player", square);


    @Test
    void landOn() {
        square.landOn(p);
        Assertions.assertEquals(200, p.getCash());
    }

    @Test
    void getTax() {
        Assertions.assertEquals(100, square.getTax());
    }
}