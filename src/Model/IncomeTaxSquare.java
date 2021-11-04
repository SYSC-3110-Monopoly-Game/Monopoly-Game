package Model;

import java.awt.Color;

public class IncomeTaxSquare extends Square {

    private final int incomeTax;
    public IncomeTaxSquare(String name, int number, int tax) {

        super(name, number);
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
        System.out.println("You need to pay income tax!");

        p.decreaseCash(this.incomeTax);
        if (p.getCash() - this.incomeTax >= 0) {
            System.out.println(p.getName() + " has paid $" + this.incomeTax);
        }
        else{
            System.out.println(p.getName() + " is bankrupt");
        }
    }

    public int getTax() {
        return this.incomeTax;
    }
}
