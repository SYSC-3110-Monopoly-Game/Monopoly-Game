package Model;

import java.awt.*;

public class GoSquare extends Square{
    private final int addMoney;
    Color color;

    /** Represents the GO Square
     *
     * @param name
     * @param number
     * @param money
     */
    public GoSquare(String name, int number, Color color, int money) {
        super(name, number, color);
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
    public int getPrice(){
        return 0;
    }
    public Color getColor(){
        return this.color;
    }
}
