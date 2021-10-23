package Model;
import java.awt.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private ArrayList<PropertySquare> squaresOwned; // player's owned squares
    private String name; // player's name
    private Square atSquare;// player's location
    private int cashTotal;// player's money

    // make an array list for the owner's squares called squaresOwned
    public Player(String name, int i) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = cashTotal;
    }

    // make square's name toString
    public String toString() {
        String s = "";
        for (Square c : squaresOwned) {
            s += c.toString();
        }
        return s;
    }

    // get name of the squares
    public String getName() {
        return name;
    }

    // get player's location
    public Square getLocation() {
        return this.atSquare;
    }

    // make player move to the nextSquare
    public void setLocation(Square nextSquare) {
        this.atSquare = nextSquare;
    }

    // count cashTotal when player lose money
    public void decreaseCash(int rentFee) {
        this.cashTotal -= rentFee;
    }

    // check if the player isBankrupt
    public boolean isBankrupt() {
        if(this.cashTotal <= 0){
            return true;
        }else{
            return false;
        }
    }

    // count cashTotal when player gain money
    public void increaseCash(int rentFee) {
        this.cashTotal += rentFee;
    }

    // check how much money does player have
    public int getCash() {
        return this.cashTotal;
    }

    // the new bought square is added into the squaresOwned array list
    public void buyProperty(Square location) {
        if(location instanceof PropertySquare){
            this.squaresOwned.add((PropertySquare) location);
        }
    }

    // check the names of the squares that player has
    public ArrayList<PropertySquare> getProperties() {
        return squaresOwned;
    }
}
