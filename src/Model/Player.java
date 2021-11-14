package Model;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<PropertySquare> squaresOwned;
    private Square currentLocation;
    private Square lastLocation;
    private int cashTotal;
    private boolean isInJail;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwned
     */
    public Player(String name, Square square) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = 300;
        this.currentLocation = square;
    }


    /**
     * Returns the name of player
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    public Square getLastLocation() {
        return lastLocation;
    }

    public Square getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Square currentLocation) {
        this.lastLocation = this.currentLocation;
        this.currentLocation = currentLocation;
    }

    /**
     * Returns the list of properties owned by player
     *
     */
    public ArrayList<PropertySquare> getProperties() {
        return squaresOwned;
    }

    /**
     * Returns the cash amount owned by player
     *
     */
    public int getCash() {
        return this.cashTotal;
    }

    /**
     * Decreases player's cash by rentFee amount
     *
     */
    public void decreaseCash(int rentFee) {
        this.cashTotal -= rentFee;
    }

    /**
     * Increases player's cash by rentFee amount
     *
     */
    public void increaseCash(int rentFee) {
        this.cashTotal += rentFee;
    }

    /**
     * Checks if player is bankrupt
     *
     * @return boolean
     */
    public boolean isBankrupt() {
        return this.cashTotal < 0;
    }

    /**
     * @ param square is added into the squaresOwned array list
     */
    public boolean buyProperty(Square location) {
        // if the square is a property
        if (location instanceof PropertySquare) {
            //if the property have not been bought
            if(((PropertySquare) location).getOwner() == null) {
                //if the player has enough money to buy
                if(this.getCash() >= ((PropertySquare) location).getPrice()) {
                    this.squaresOwned.add((PropertySquare) location);           // add the property to player's properties list
                    ((PropertySquare) location).setOwner(this);                 // set the owner of the property to this player
                    this.decreaseCash(((PropertySquare) location).getPrice());  // reset the player's cash
                    return true;
                } else {
                    System.out.println("u do not have enough money");
                }
            } else {
                System.out.println("the property has been bought");
            }
        } else {
            System.out.println("this square is not a property");
        }

        return false;
    }

    /**
     * @ param square is removed from the squaresOwned array list
     */
    public void sellProperty(Square property) {
        if (property instanceof PropertySquare) {
            this.squaresOwned.remove(property);                     // remove the property from player's properties list
            ((PropertySquare) property).setOwner(null);             // set the owner of the property to nobody
            this.increaseCash(((PropertySquare) property).getPrice() / 2);    // reset the player's cash
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Square c : squaresOwned) {
            s.append(c.toString());
        }
        return s.toString();
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }
}