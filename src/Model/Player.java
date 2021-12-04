package Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private final String name;
    private String decision;
    private final ArrayList<PropertySquare> squaresOwned;
    private Square currentLocation;
    private Square lastLocation;
    private PropertySquare selectedSquare;

    private int cashTotal;
    private boolean isInJail;
    private boolean diceRolled;

    /**
     * Constructor of Player
     * make an array list for the owner's squares called squaresOwned
     */
    public Player(String name, Square square) {
        this.name = name;
        this.squaresOwned = new ArrayList<>();
        this.cashTotal = 350;
        this.currentLocation = square;
        this.decision = "";
        this.diceRolled = false;
    }

    public Player(String name, int cash, boolean inJail, boolean diceRolled, String decision, Square lastSquare, Square thisSquare, ArrayList<PropertySquare> squares, PropertySquare square){
        this.name = name;
        this.cashTotal = cash;
        this.isInJail = inJail;
        this.decision = decision;
        this.lastLocation = lastSquare;
        this.currentLocation = thisSquare;
        this.squaresOwned = squares;
        this.selectedSquare = square;
        this.diceRolled = diceRolled;
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
        return this.cashTotal <= -1;
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

    public void setDiceRolled(boolean b) {
        this.diceRolled = b;
    }

    public boolean getDiceRolled() {
        return this.diceRolled;
    }

    /**
     * export the player as a xml file to file: fileName
     * @param fileName: file name of the xml file
     * @param id: the sequence the player is in the game
     */
    public void exportPlayers(String fileName, int id) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(this.toXML(id));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a player with given variables
     * @param playerInfo: variables contains information of this player
     * @return: The instance of player or AIPlayer with the given index
     */
    public static Player loadToPlayer(HashMap<String, Object> playerInfo){
        String name, decision;
        int cash, isInJail, diceRolled;
        Square currentLocation, lastLocation;
        PropertySquare selectedSquare;
        ArrayList<PropertySquare> sOwned = new ArrayList<>();

        name = (String) playerInfo.get("Name");
        decision = (String) playerInfo.get("Decision");
        cash = (int) playerInfo.get("Cash");
        isInJail = (int) playerInfo.get("IsInJail");
        diceRolled = (int) playerInfo.get("DiceRolled");
        currentLocation = (Square) playerInfo.get("CurrentLocation");
        lastLocation = (Square) playerInfo.get("LastLocation");
        selectedSquare = (PropertySquare) playerInfo.get("SelectedSquare");
        if(playerInfo.get("SquareOwned") instanceof ArrayList){
            sOwned = (ArrayList<PropertySquare>) playerInfo.get("SquareOwned");
        }

        if(name.length() > 2 && name.charAt(1) == 'A' && name.charAt(2) == 'I'){
            return new AIPlayer(name, cash, isInJail == 1, diceRolled == 1, decision, lastLocation, currentLocation, sOwned, selectedSquare);
        }
        return new Player(name, cash, isInJail == 1, diceRolled == 1, decision, lastLocation, currentLocation, sOwned, selectedSquare);
    }

    /**
     * create a player from the given xml file path
     * @param board: Monopoly board
     * @param path: file name of the xml file
     * @param index: the sequence the player is in the game
     * @return: The instance of player with the given index
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Player makePlayerFromXML(MonopolyBoard board, String path, int index) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();


        final Player[] returnPlayer = new Player[1];
        Square s = board.getSquareAt(0);
        PropertySquare testProperty = (PropertySquare) board.getSquareAt(1);

        HashMap<String, Object> playerInfo = new HashMap<>(Map.of("Name", "","Decision", "",
                "DiceRolled", 0, "Cash", 0, "IsInJail", 0, "CurrentLocation",
                s,"LastLocation", s, "SelectedSquare", testProperty,
                "SquareOwned", new ArrayList<PropertySquare>()));

        boolean[] toLoad = {false,false,false,false,false,false,false,false,false,false};
        final String[] variables = {"Name","Decision","Cash", "IsInJail", "DiceRolled", "CurrentLocation",
                "LastLocation", "SelectedSquare", "SquareOwned"};

        saxParser.parse(path, new DefaultHandler(){

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if(qName.equals("Player")){
                    if(Integer.parseInt(attributes.getValue("id"))== index)
                        toLoad[0] = true;
                } else {
                    for(int i=0; i< variables.length; i++){
                        if(qName.equals(variables[i])){
                            toLoad[i+1] = true;
                        }
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                if(qName.equals("Player")){
                    toLoad[0] = false;
                    returnPlayer[0] = loadToPlayer(playerInfo);
                } else {
                    for(int i=0; i< variables.length; i++){
                        if(qName.equals(variables[i])){
                            toLoad[i+1] = false;
                        }
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                String temp = new String(ch, start, length);

                for(int i=0; i< variables.length; i++){
                    if(toLoad[i+1] && toLoad[0]){
                        if(i<2){
                            playerInfo.put(variables[i], playerInfo.get(variables[i])+temp);
                        }
                        else if(i<5){
                            int num = Integer.parseInt(temp);
                            playerInfo.put(variables[i], num);
                        }
                        else if(i<8){
                            int num = Integer.parseInt(temp);
                            playerInfo.put(variables[i], board.getSquareAt(num));
                        }
                        else {
                            String[] map = temp.split(",");
                            ArrayList<PropertySquare> squareOwn = new ArrayList<>();
                            for(String s: map){
                                squareOwn.add((PropertySquare) board.getSquareAt(Integer.parseInt(s)));
                            }

                            playerInfo.put(variables[i], squareOwn);
                        }
                        break;
                    }
                }
            }
        });
        return returnPlayer[0];
    }

    /**
     * convert the player to xml format
     * @param id: the sequence the player is in the game
     * @return: xml format String
     */
    private String toXML(int id) {
        StringBuilder string = new StringBuilder();
        string.append("<Player id=\""+ id +"\">\n");
        string.append("<Name>"+this.getName()+"</Name>\n");
        string.append("<Decision>"+this.getDecision()+"</Decision>\n");
        string.append("<Cash>"+this.getCash()+"</Cash>\n");
        string.append("<IsInJail>"+(this.isInJail() ? 1:0)+"</IsInJail>\n");
        string.append("<DiceRolled>"+(this.getDiceRolled() ? 1:0)+"</DiceRolled>\n");
        string.append("<CurrentLocation>"+this.getCurrentLocation().getNumber()+"</CurrentLocation>\n");
        if(this.getLastLocation() != null) {
            string.append("<LastLocation>"+this.getLastLocation().getNumber()+"</LastLocation>\n");
        } else {
            string.append("<LastLocation></LastLocation>\n");
        }
        if(this.getSelectedSquare() != null) {
            string.append("<SelectedSquare>"+this.getSelectedSquare().getNumber()+"</SelectedSquare>\n");
        } else {
            string.append("<SelectedSquare></SelectedSquare>\n");
        }
        string.append("<SquareOwned>");
        for(PropertySquare p: this.getProperties()){
            string.append(p.getNumber()+ ",");
        }
        string.append("</SquareOwned>\n");

        string.append("</Player>\n");

        return string.toString();
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

    /**
     * check if the player owns 1 or more whole color set, if so, return the color sets
     */
    public ArrayList<PropertySquare> hasWholeSet() {
        int[] counter = new int[MonopolyBoard.colors.size()];
        ArrayList<PropertySquare> result = new ArrayList<>();
        ArrayList<Color> c= MonopolyBoard.colors;

        for(PropertySquare p: squaresOwned){
            for(int i=0; i<c.size(); i++){
                if (p.getColor().equals(c.get(i))){
                    counter[i]++;
                }
            }
        }
        for(int i=0; i<(c.size()-4); i++){
            if (counter[i] == 3){
                for(PropertySquare p: squaresOwned){
                    if(p.getColor().equals(c.get(i))){
                        result.add(p);
                    }
                }
            }
        }
        for(int i=(c.size()-4); i<c.size(); i++){
            if (counter[i] >= 2){
                for(PropertySquare p: squaresOwned){
                    if(p.getColor().equals(c.get(i))){
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
            if(p.getColor().equals(color)){
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