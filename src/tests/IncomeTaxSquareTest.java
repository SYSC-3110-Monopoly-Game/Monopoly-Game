package tests;

import Model.GoSquare;
import Model.IncomeTaxSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class IncomeTaxSquareTest {

    private IncomeTaxSquare square = new IncomeTaxSquare("go", 0, 100);
    private Player p = new Player("player", square);


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