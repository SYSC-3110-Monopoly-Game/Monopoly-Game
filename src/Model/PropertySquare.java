package Model;

import java.awt.Color;
import java.util.HashMap;

public class PropertySquare extends Square {

    private final int buyPrice; //price for the player buy this land
    private final int rentPrice; //the price that other players need to pay to the owner.
    private final Color color;
    private Player owner = null;


    public PropertySquare(String name, int number, int buy, int rent, Color color) {
        super(name, number);
        this.buyPrice = buy;
        this.rentPrice = rent;
        this.color = color;
    }

    /**
     * gets the color of the square
     */
    public Color getColor() {
        return color;
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
        p.setCurrentLocation(this);

        int fee = this.getRentFee();

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
        }
    }

}