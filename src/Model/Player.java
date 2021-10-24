package Model;
import java.awt.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private ArrayList<PropertySquare> squaresOwned; // the player's owned squares
    private String name; // the player's name
    private Square atSquare;
    private int cashTotal;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwned
     */
    public Player(String name, int i) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = cashTotal;
    }

    public String toString(){
        String s="";
        for(Square c: squaresOwned){
            s += c.toString();
        }
        return s;
    }

    /**
     * @ return name of the squares
     */
    public String getName() {
        return name;
    }

    public Square getLocation() {
        return this.atSquare;
    }

    public void setLocation(Square nextSquare) {
        this.atSquare = nextSquare;
    }

    public void decreaseCash(int rentFee) {
        this.cashTotal -= rentFee;
    }

    public boolean isBankrupt() {
        if(this.cashTotal <= 0){
            return true;
        }else{
            return false;
        }
    }

    public void increaseCash(int rentFee) {
        this.cashTotal += rentFee;
    }

    public int getCash() {
        return this.cashTotal;
    }

    /**
     * @ param square is added into the squaresOwned array list
     */
    public void buyProperty(Square location) {
        if(location instanceof PropertySquare){
            this.squaresOwned.add((PropertySquare) location);
        }
    }

    public ArrayList<PropertySquare> getProperties() {
        return squaresOwned;
    }
}
