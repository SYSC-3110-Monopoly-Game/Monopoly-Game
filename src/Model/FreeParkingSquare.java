package Model;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String name, int number) {
        super(name, number);
        message = " is on Free Parking\n";
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

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"FreeParking\">");
        string.append("<Name>"+this.getName()+"</Name>");
        string.append("<Number>"+this.getNumber()+"</Number>");
        string.append("</Square>");

        return string.toString();
    }

}
