package Model;

import java.awt.Color;

public class PropertySquare extends Square {

    private final int buyPrice; //price for the player buy this land
    private final int rentPrice; //the price that other players need to pay to the owner.
    private final Color color;
    private boolean sold;
    private Player owner = null;


    public PropertySquare(String name, int number, int buy, int rent, Color color) {
        super(name, number);
        this.buyPrice = buy;
        this.rentPrice = rent;
        this.color = color;
        this.sold = false;
    }

    /**
     * gets the color of the square
     */
    public Color getColor() {
        return Color.BLACK;
    }

    /**
     * get the land sale price
     */
    public int getPrice() {
        return buyPrice;
    }

    /**
     * get the land rent price (square toll)
     */
    public int getRentFee() {
        return rentPrice;
    }

    /**
     * if the square has an owner
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * get the player who own this land
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * player who buy this land
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * action applied when player lands on this square
     */
    @Override
    public void landOn(Player p) {
        p.setLocation(this);

        int fee = this.getRentFee();
        int price = this.getPrice();

        Player owner = this.getOwner();
        //if the square has an owner and the owner is not this player
        if (p != owner && owner != null) {
            //owner gets paid by the player
            owner.increaseCash(fee);
            p.decreaseCash(fee);

            //print the player paid the rent fee
            System.out.println("!!!You need to pay rent!!!");
            System.out.println(p.getName() + " has paid $" + fee);
            System.out.println(owner.getName() + " has received $" + fee);
        } else if (owner == null) {
           //check if the player has enough money to buy the square
            if (p.getCash() - price >= 0) {
                //check if the player want to buy the square
                if (p.ifWantToBuy(this)) {
                    p.buyProperty(this);
                }
            }
        }
    }

}