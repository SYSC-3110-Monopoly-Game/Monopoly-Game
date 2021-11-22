package Model;

public class TaxSquare extends Square {

    private final int tax;

    public TaxSquare(String name, int number, int tax) {
        super(name, number);
        this.tax = tax;
    }

    /** Player pays income tax when they land on this square
     *
     */
    @Override
    public void landOn(Player p) {
        p.setCurrentLocation(this);
        System.out.println("You need to pay tax!");

        p.decreaseCash(this.tax);
        System.out.println(p.getName() + " has paid $" + this.tax);

    }

    public int getTax() {
        return this.tax;
    }
}
