package Model;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private final String name;
    private String decision;
    private final ArrayList<PropertySquare> squaresOwned;
    private Square currentLocation;
    private Square lastLocation;
    private PropertySquare selectedSquare;

    private int cashTotal;
    private boolean isInJail;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwned
     */
    public Player(String name, Square square) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = 350;
        this.currentLocation = square;
        this.decision = null;
    }

    public Player(String name, int cash, boolean inJail, String decision, Square lastSquare, Square thisSquare, ArrayList<PropertySquare> squares, PropertySquare square){
        this.name = name;
        this.cashTotal = cash;
        this.isInJail = inJail;
        this.decision = decision;
        this.lastLocation = lastSquare;
        this.currentLocation = thisSquare;
        this.squaresOwned = squares;
        this.selectedSquare = square;
    }


    /**
     * Returns the name of player
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns last location of player
     */
    public Square getLastLocation() {
        return lastLocation;
    }

    /**
     * Returns current location of player
     */
    public Square getCurrentLocation() {
        return currentLocation;
    }

    /**
     * return the property according to its name
     */
    public PropertySquare getPropertyFromName(String name) {
        for (PropertySquare p: this.getProperties()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    /**
     * get which square the player would like to build/sell building
     *
     * @return selectedSquare
     */
    public PropertySquare getSelectedSquare() {
        return selectedSquare;
    }

    /**
     * set selected square (a private field in player)
     */
    public void setSelectedSquare(PropertySquare selectedSquare) {
        this.selectedSquare = selectedSquare;
    }

    /**
     * Sets the current location of th player
     */
    public void setCurrentLocation(Square currentLocation) {
        this.lastLocation = this.currentLocation;
        this.currentLocation = currentLocation;
    }

    /**
     * Sets the last location of the player
     */
    public void setLastLocation(Square lastLocation) {
        this.lastLocation = lastLocation;
    }

    /**
     * get if player want to build or sell buildings
     *
     * @return decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * set decision (a private field in player)
     */
    public void setDecision(String decision) {
        this.decision = decision;
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

    /**
     * check if the player owns 1 or more whole color set, if so, return the color sets
     */
    public ArrayList<PropertySquare> hasWholeSet() {
        int[] counter = new int[MonopolyBoard.colors.size()];
        ArrayList<PropertySquare> result = new ArrayList<>();
        ArrayList<Color> c= MonopolyBoard.colors;

        for(PropertySquare p: squaresOwned){
            for(int i=0; i<c.size(); i++){
                if (p.getColor() == c.get(i)){
                    counter[i]++;
                }
            }
        }
        for(int i=0; i<(c.size()-4); i++){
            if (counter[i] == 3){
                for(PropertySquare p: squaresOwned){
                    if(p.getColor() == c.get(i)){
                        result.add(p);
                    }
                }
            }
        }
        for(int i=(c.size()-4); i<c.size(); i++){
            if (counter[i] >= 2){
                for(PropertySquare p: squaresOwned){
                    if(p.getColor() == c.get(i)){
                        result.add(p);
                    }
                }
            }
        }
        return result;
    }

    /**
    * check if the player has build 1 or more buildings, if so, return the properties with buildings
     */
    public ArrayList<PropertySquare> hasBuilding() {
        ArrayList<PropertySquare> result = new ArrayList<>();
        for(PropertySquare p: squaresOwned){
            if (p.hasHouses() || p.hasHotel()){
                result.add(p);
            }
        }
        return result;
    }

    /**
     * remove all railroad and utility squares from the given list
     */
    public ArrayList<PropertySquare> removeRailroadUtility(ArrayList<PropertySquare> input){
        ArrayList<PropertySquare> output = new ArrayList<>(input);
        if(!input.isEmpty()) {
            output.removeIf(p -> p instanceof RailRoadSquare || p instanceof UtilitySquare);
        }
        return output;
    }

    /**
     * in player's property square list, count how many property squares have the same color with the given color
     */
    public int countNumber(Color color) {
        int counter = 0;
        ArrayList<PropertySquare> temp = this.hasWholeSet();
        for(PropertySquare p: temp){
            if(p.getColor() == color){
                counter++;
            }
        }
        return counter;
    }

    /**
     * remove all property squares which player does not have enough money to build a house on it from the given list
     */
    public ArrayList<PropertySquare> getAvailableProperties(ArrayList<PropertySquare> p){
        if(!p.isEmpty()){
            p.removeIf(property -> this.getCash() < property.getHousePrice());
        }
        if(!p.isEmpty()){
            p.removeIf(PropertySquare::hasHouses);
        }
        return p;
    }

    /**
     * player build a house or a hotel according to the command on the selected square(a private field in player)
     *
     */
    public int buildH(String answer) {
        if(answer.equals("House")){
            return this.getSelectedSquare().buildHouse();
        } else if (answer.equals("Hotel")){
            return this.getSelectedSquare().buildHotel();
        }
        return -1;
    }

    /**
     * player sell a house or a hotel according to the command on the selected square(a private field in player)
     *
     */
    public int sellH(String answer) {
        if(answer.equals("House")){
            return this.getSelectedSquare().sellHouse();
        } else if (answer.equals("Hotel")){
            return this.getSelectedSquare().sellHotel();
        }
        return -1;
    }
}