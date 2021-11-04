package Model;

public class GoSquare extends Square{
    private final int addMoney;

    /** Represents the GO Square
     *
     * @param name
     * @param number
     * @param money
     */
    public GoSquare(int number, int money) {
        super("Go", number);
        this.addMoney = money;
    }

    /** Returns the GO Square money
     *
     * @return amount of money a player gets when they pass go
     */
    private int getAddMoney() {return addMoney;}

    /** Player passed GO square, gets free money
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.increaseCash(this.addMoney);
        System.out.println("You passed GO Square! You will get " + this.addMoney);
    }
}
