package Model;

import view.IncomeTaxSquareGUI;

import java.awt.Color;

public class IncomeTaxSquare extends Square {

    private final int incomeTax;

    public IncomeTaxSquare(String name, int number, int tax) {
        super(name, number);
        this.incomeTax = tax;
        message = " is on Income Tax and has just paid $" + tax + '\n';
    }

    /** Player pays income tax when they land on this square
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setCurrentLocation(this);
        System.out.println("You need to pay income tax!");

        p.decreaseCash(this.incomeTax);
        System.out.println(p.getName() + " has paid $" + this.incomeTax);

    }

    public int getTax() {
        return this.incomeTax;
    }
}
