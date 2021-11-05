package Model;

import view.IncomeTaxSquareGUI;

import java.awt.Color;

public class IncomeTaxSquare extends Square {

    public IncomeTaxSquareGUI gui;

    private final int incomeTax;
    public IncomeTaxSquare(String name, int number, int tax) {
        super(name, number);
        this.gui = new IncomeTaxSquareGUI(tax);
        this.incomeTax = tax;
    }


    private int getIncomeTax() {return incomeTax;}

    /** Player pays income tax when they land on this square
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setLocation(this);
        this.gui.addPlayer(p.getName());
        System.out.println("You need to pay income tax!");

        p.decreaseCash(this.incomeTax);
        if (p.getCash() - this.incomeTax >= 0) {
            System.out.println(p.getName() + " has paid $" + this.incomeTax);
        }
        else{
            System.out.println(p.getName() + " is bankrupt");
        }
    }

    @Override
    public void landOff(Player p) {
        this.gui.removePlayer(p.getName(), false);
    }

    public int getTax() {
        return this.incomeTax;
    }
}
