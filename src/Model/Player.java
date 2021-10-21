package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private ArrayList<PropertySquare> squaresOwned; // the player's owned squares
    private final String name; // the player's name
    private Square atSquare;
    private int cashTotal;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwned
     */
    public Player(String name) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = 1500;
//        wait for monopolyboard class
        this.atSquare = null;
    }

    public String toString(){
        StringBuilder s= new StringBuilder();
        for(Square c: squaresOwned){
            s.append(c.toString());
        }
        return s.toString();
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
        return this.cashTotal <= 0;
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
//            add the property to player's properties list
            this.squaresOwned.add((PropertySquare) location);
//            set the owner of the square to this player
            ((PropertySquare) location).setOwner(this);
//            reset the player's cash
            this.decreaseCash(((PropertySquare) location).getPrice());
        }
    }

    public ArrayList<PropertySquare> getProperties() {
        return squaresOwned;
    }

    /*
    * select from owned properties that which want to be sold
    * */
    public boolean selectWhichToSell(int fee) {
//        if sell some properties and the cash will be greater than 1
//        return true
//        else
//        return false
        return false;
    }

    /*
check is the player want to buy the property
* */
    public boolean ifWantToBuy(PropertySquare square) {
        Scanner input = new Scanner(System.in);
//        print out the information of the property that the player can buy
        System.out.println("If you want to buy " + square.getName() + "? (y/n)");
        System.out.println("Price: " + square.getPrice());
        System.out.println("Color: " + square.getColor());
//        get command from player
        String op = input.next();
//        if command not valid, get command again
        while (MonopolyGame.checkCommand(op) == -1) {
            System.out.print("\nPlease input a valid command:(y/n) ");
            op = input.next();
        }
        input.close();

//        y->return true | n->return false
        if (op.equals("y")) return true;
        else return false;
    }
}
