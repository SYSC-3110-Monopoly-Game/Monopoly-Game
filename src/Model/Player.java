package Model;
import java.awt.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private ArrayList<PropertySquare> squaresOwn; // the player's owned squares
    private String name; // the player's name
    private Color color;
    private Square atSquare;
    private int cashTotal;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwn
     */
    public Player(String name, int i) {
        this.name = name;
        this.squaresOwn = new ArrayList<>();
    }


    public void addColor(Color c)
    {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    /**
     * @ param square is removed from the squaresOwn array list
     */
    public void removeSquare(Square square) {
        this.squaresOwn.remove(square);
    }

    /**
     * get all the countries names from the squareOwn array list
     * @ return a list of the owner's squares
     */
    public String getStatus() {
        String s = "";
        s += "Player: " + this.name + " has squares:\n";
        for (Square square : squaresOwn) {
            s += "  " + square.printState() + "\n";
        }
        return s + "\n";
    }


    public String getSquaresInString(){
        String s="";
        for(Square c: squaresOwn){
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

    public void clear(){this.squaresOwn.clear();}

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
     * @ param square is added into the squaresOwn array list
     */
    public void buyProperty(Square location) {
        if(location instanceof PropertySquare){
            this.squaresOwn.add((PropertySquare) location);
        }
    }

    public ArrayList<PropertySquare> getProperties() {
        return squaresOwn;
    }
}
