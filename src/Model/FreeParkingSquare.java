package Model;


import view.FreeParkingSquareGUI;

import java.awt.*;

public class FreeParkingSquare extends Square {

    public FreeParkingSquareGUI gui;

    public FreeParkingSquare(String name, int number) {
        super(name, number);
        this.gui = new FreeParkingSquareGUI();
    }

    /** Player gets a free parking
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setLocation(this);
        this.gui.addPlayer(p.getName());
        System.out.println("Free Parking for player " + p.getName());
    }

    @Override
    public void landOff(Player p) {
        this.gui.removePlayer(p.getName(), false);
    }

}
