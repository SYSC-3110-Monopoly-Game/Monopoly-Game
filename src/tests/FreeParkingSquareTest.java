package tests;

import Model.FreeParkingSquare;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FreeParkingSquareTest {

    private FreeParkingSquare freeParking;
    private Player p;

    FreeParkingSquareTest(FreeParkingSquare freeParking, Player p) {
        this.freeParking = freeParking;
        this.p = p;
    }

    @Test
    void landOn() {
        freeParking.landOn(p);
        Assertions.assertEquals(p, "Free Parking for player " + p.getName());
    }
}