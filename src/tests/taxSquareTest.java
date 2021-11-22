package tests;

import Model.TaxSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class taxSquareTest {

    private final TaxSquare square = new TaxSquare("go", 0, 100);
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