package tests;

import Model.IncomeTaxSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncomeTaxSquareTest {

    private IncomeTaxSquare tax;
    private Player p;
    private int cashTotal;

    IncomeTaxSquareTest(IncomeTaxSquare tax, Player p, int cashTotal) {
        this.tax = tax;
        this.p = p;
        this.cashTotal = cashTotal;
    }

    @Test
    void landOn() {
        tax.landOn(p);
        Assertions.assertEquals(p, "You need to pay income tax!"+ p.getName());
        Assertions.assertEquals(tax, p.decreaseCash(cashTotal));
    }

    @Test
    void getTax() {
        Assertions.assertEquals(tax, tax.getTax());
    }
}