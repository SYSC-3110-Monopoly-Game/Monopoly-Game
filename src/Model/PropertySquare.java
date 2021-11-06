package Model;

import view.PropertySquareGUI;
import java.awt.Color;
import java.util.HashMap;

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

        if (p != owner && owner != null) {              // if the property has an owner and the owner is not this player

            HashMap jail = MonopolyBoard.jail.getMap();
            if (jail != null && jail.containsKey(owner)) {                  // if the owner of this property is in jail
                System.out.println("The owner is in jail, no need to pay rent fee");    // do not pay rent fee
            } else {                                                        // if the owner is not in jail

                owner.increaseCash(fee);                                    // owner gets paid by the player
                p.decreaseCash(fee);                                        // player cash decreases

                System.out.println("!!!You need to pay rent!!!");           //
                System.out.println(p.getName() + " has paid $" + fee);      // print the player paid the rent fee
                System.out.println(owner.getName() + " has received $" + fee);//
            }
        } else if (owner == null) {                                         // if this property has no owner

            if (p.getCash() - price >= 0) {                     // if the player has enough money to buy the square

                System.out.println("your cash is: " + p.getCash());     // show player's cash
                if (p.ifWantToBuy(this)) {                      // if the player is willing to buy the square
                    p.buyProperty(this);                        // player buy the property!!
                }
            }
        }
    }

}