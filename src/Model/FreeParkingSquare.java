package Model;


import view.FreeParkingSquareGUI;

import java.awt.*;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String name, int number) {
        super(name, number);
    }

    /** Player gets a free parking
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setCurrentLocation(this);
        System.out.println("Free Parking for player " + p.getName());
    }

}
