package Model;
import java.awt.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Square> squaresOwn; // the player's owned squares
    private String name; // the player's name
    private Color color;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwn
     */
    public Player(String name) {
        this.name = name;
        this.squaresOwn = new ArrayList<>();
    }

    /**
     * @ param square is added into the squaresOwn array list
     */
    public void addSquare(Square square) {
        this.squaresOwn.add(square);
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

    /**
     * @ return squaresOwn array list
     */
    public List<Square> getSquaresOwn() { // helper method used for getting all the available countries in a printable list for the player
        return squaresOwn;
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
}
