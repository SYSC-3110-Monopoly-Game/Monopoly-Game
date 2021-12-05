package Model;

public class TaxSquare extends Square {

    private final int tax;

    public TaxSquare(String name, int number, int tax) {
        super(name, number);
        this.tax = tax;
    }

    /**
     * Player pays income tax when they land on this square
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

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"Tax\">\n");
        string.append("<Name>" + this.getName() + "</Name>\n");
        string.append("<Number>" + this.getNumber() + "</Number>\n");
        string.append("<Price>" + this.getTax() + "</Price>\n");
        string.append("</Square>");

        return string.toString();
    }
}
