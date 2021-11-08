package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private final String name;
    private ArrayList<PropertySquare> squaresOwned;
    private Square atSquare;
    private int cashTotal;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwned
     */
    public Player(String name, Square square) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = 300;
        this.setLocation(square);
    }


    /** Returns the name of plater
     * @return name
     */
    public String getName() {
        return name;
    }

    /** Returns the current location of player
     * @return location
     */
    public Square getLocation() {
        return this.atSquare;
    }

    /** Sets the location of player
     *
     * @param nextSquare
     */
    public void setLocation(Square nextSquare) {
        this.atSquare = nextSquare;
    }

    /** Returns the list of properties owned by player
     *
     * @return
     */
    public ArrayList<PropertySquare> getProperties() {
        return squaresOwned;
    }

    /** Returns the cash amount owned by player
     *
     * @return
     */
    public int getCash() {
        return this.cashTotal;
    }

    /** Decreases player's cash by rentFee amount
     *
     * @param rentFee
     */
    public int decreaseCash(int rentFee) {
        this.cashTotal -= rentFee;
        return cashTotal;
    }

    /** Increases player's cash by rentFee amount
     *
     * @param rentFee
     */
    public int increaseCash(int rentFee) {
        this.cashTotal += rentFee;
        return cashTotal;
    }

    /** Checks if player is bankrupt
     *
     * @return boolean
     */
    public boolean isBankrupt() {
        return this.cashTotal <= 0;
    }

    /**
     * @ param square is added into the squaresOwned array list
     */
    public void buyProperty(Square location) {
        if (location instanceof PropertySquare) {
            //add the property to player's properties list
            this.squaresOwned.add((PropertySquare) location);
            //set the owner of the square to this player
            ((PropertySquare) location).setOwner(this);
            //reset the player's cash
            this.decreaseCash(((PropertySquare) location).getPrice());
        }
    }


    /**
     * check is the player want to buy the property
     */
    public boolean ifWantToBuy(PropertySquare square) {
        System.out.println("If you want to buy " + square.getName() + "? (y/n)");
        System.out.println("Price: " + square.getPrice());
        System.out.println("Color: " + square.getColor());

        return MonopolyGame.checkCommand();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Square c : squaresOwned) {
            s.append(c.toString());
        }
        return s.toString();
    }
}
