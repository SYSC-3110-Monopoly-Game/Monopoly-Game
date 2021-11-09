package tests;

import Model.FreeParkingSquare;
import Model.MonopolyBoard;
import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FreeParkingSquareTest {

    private FreeParkingSquare freeParking;
    private Player p;
    private MonopolyBoard board = new MonopolyBoard();



    @Test
    void landOn() {
        freeParking = new FreeParkingSquare("FreeParking", 100);
        p = new Player("player1", board.startingSquare());
        freeParking.landOn(p);
        Assertions.assertEquals(p.getCurrentLocation(), freeParking);
    }
}