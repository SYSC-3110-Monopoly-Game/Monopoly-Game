package Model;

import view.GoSquareGUI;

import java.awt.Color;

public class GoSquare extends Square{
    private final int addMoney;
    private Color color;


    /** Represents the GO Square
     *
     * @param name
     * @param number
     * @param money
     */
    public GoSquare(String name, int number, Color color, int money) {
        super(name, number);
        this.addMoney = money;
        message = " just passed GO and earns $"+money+"\n";
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

}
