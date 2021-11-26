package Model;

public class GoSquare extends Square{
    private final int addMoney;


    /** Represents the GO Square
     *
     * @param name
     * @param number
     * @param money
     */
    public GoSquare(String name, int number, int money) {
        super(name, number);
        this.addMoney = money;
    }

    /** Returns the GO Square money
     *
     * @return amount of money a player gets when they pass go
     */
    public int getAddMoney() {

        return addMoney;
    }

    /** Player passed GO square, gets free money
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setCurrentLocation(this);
        p.increaseCash(this.addMoney);
        System.out.println("You passed GO Square! You will get " + this.addMoney);
    }

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"Go\">\n");
        string.append("<Name>" + this.getName() + "</Name>\n");
        string.append("<Number>" + this.getNumber() + "</Number>\n");
        string.append("<Price>" + this.getAddMoney() + "</Price>\n");
        string.append("</Square>\n");

        return string.toString();
    }

}
