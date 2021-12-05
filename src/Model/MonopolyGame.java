package Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import view.Enums;
import view.MonopolyGameGUI;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MonopolyGame {
    public static MonopolyBoard board;
    public static Dice dice;
    private final ArrayList<MonopolyGameGUI> views;
    public ArrayList<Player> players;
    private int doubleCounter;
    private Player playerInTurn;

    private ArrayList<Player> playersNotInTurn;

    private int index = -1;

    /**
     * load game from path
     *
     * @param path: file name of the xml file
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public MonopolyGame(String path) throws ParserConfigurationException, IOException, SAXException {
        views = new ArrayList<>();
        dice = new Dice();
        loadGame(path);
    }

    /**
     * export the whole game to path
     *
     * @param path: file name of the xml file
     */
    public void exportGameToXML(String path) {
        try {
            File writename = new File(path);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write("<Game>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        board.exportToXML(path);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).exportPlayers(path, i);
        }
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write("<DoubleCounter>" + this.getDoubleCounter() + "</DoubleCounter>");
            writer.write("<PlayerInTurn>" + this.getPlayerInTurn().getName() + "</PlayerInTurn>");
            writer.write("<PlayerNotInTurn>");
            for (Player p : this.getPlayersNotInTurn()) {
                writer.write(p.getName() + ",");
            }
            writer.write("</PlayerNotInTurn>");
            writer.write("<DiceValue>");
            writer.write(dice.getDice()[0] + "," + dice.getDice()[1]);
            writer.write("</DiceValue>");
            writer.write("</Game>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load the game from path
     *
     * @param path: file name of the xml file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void loadGame(String path) throws ParserConfigurationException, SAXException, IOException {
        board = new MonopolyBoard(path);
        players = new ArrayList<>();
        final int[] numberOfPlayer = {0};

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(path, new DefaultHandler(){
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if(qName.equals("Player")){
                    numberOfPlayer[0]++;
                }
            }
        });
        for(int i=0; i<numberOfPlayer[0]; i++){
            players.add(i, Player.makePlayerFromXML(board, path, i));
        }
        final boolean[] ccl = {false, false, false, false};

        saxParser.parse(path, new DefaultHandler() {

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                switch (qName) {
                    case "PlayerInTurn" -> ccl[0] = true;
                    case "PlayerNotInTurn" -> ccl[1] = true;
                    case "DoubleCounter" -> ccl[2] = true;
                    case "DiceValue" -> ccl[3] = true;
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                switch (qName) {
                    case "PlayerInTurn" -> ccl[0] = false;
                    case "PlayerNotInTurn" -> ccl[1] = false;
                    case "DoubleCounter" -> ccl[2] = false;
                    case "DiceValue" -> ccl[3] = false;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                String string = new String(ch, start, length);
                if (ccl[0]) {
                    for (Player p : players) {
                        if (p.getName().equals(string)) {
                            playerInTurn = p;
                        }
                    }
                } else if (ccl[1]) {
                    ArrayList<Player> playerss = new ArrayList<>();
                    String[] ps = string.split(",");
                    for (String s : ps) {
                        for (Player p : players) {
                            if (p.getName().equals(s)) {
                                playerss.add(p);
                            }
                        }
                    }
                    playersNotInTurn = playerss;
                } else if (ccl[2]) {
                    doubleCounter = Integer.parseInt(string);
                }
            }
        });
        for (Square s : board.getSquares()) {
            if (s instanceof PropertySquare) {
                ((PropertySquare) s).setOwnerAccordingToOwnerName(players);
            }
            if (s instanceof JailSquare) {
                ((JailSquare) s).loadMapAccordingStringMap(players);
            }
        }
    }

    /**
     * move the player with a random distance
     */
    private void movePlayer(Player p, int distance) {
        Square currentLocation = p.getCurrentLocation();
        System.out.println("Current location: " + currentLocation.toString());

        // calculate the square will be reached
        Square nextSquare = board.getNextSquare(p.getCurrentLocation(), distance);

        // if the square will reached passes GoSquare AND is Not GoSquare
        if (currentLocation.getNumber() > nextSquare.getNumber() && nextSquare.getNumber() != 0) {
            // increase player money by go money amount
            p.increaseCash(((GoSquare) board.startingSquare()).getAddMoney());
        }
        System.out.println("New location: " + nextSquare + "\n");

        // Move player to the calculated square
        nextSquare.landOn(p);
    }


    /**
     * return the winner of the game
     */
    private Player getWinner() {
        // if more than 1 player exist return null
        if (players.size() != 1)
            return null;
            // if only 1 player exist return the winner
        else
            return players.get(0);
    }


    /**
     * do all needed check when rolling dices
     */
    public int getDistance() {
        dice.rollDice();
        System.out.println("\t Dice Rolled!!\n" + dice);
        System.out.println("+-------------------------+");

        return dice.getTotalValue();
    }

    /**
     * buy the landed on property
     */
    public void buySquare() throws IOException, SAXException, ParserConfigurationException {
        if (playerInTurn.buyProperty(playerInTurn.getCurrentLocation())) {
            this.updateViews(playerInTurn, Enums.BUY);
        }
    }

    /**
     * sell properties
     */
    public void sellSquare() throws IOException, SAXException, ParserConfigurationException {
        if (!playerInTurn.getProperties().isEmpty()) {
            this.updateViews(playerInTurn, Enums.SELL);
        }
    }

    /**
     * check if the player can build a house on any of its property square list
     */
    public void checkAvailableBuild() {
        ArrayList<PropertySquare> propertyList = playerInTurn.removeRailroadUtility(playerInTurn.hasWholeSet());
        if (!propertyList.isEmpty()) {
            propertyList = playerInTurn.getAvailableProperties(propertyList);
            if (!propertyList.isEmpty()) {
                views.get(0).getDecision(propertyList, this);
                this.getPlayerInTurn().setDecision(Enums.BUILD);
            } else {
                System.out.println("not enough money");
            }
        } else {
            System.out.println("not hold a set");
        }
    }

    /**
     * check if the player can sell a building from any of its property square list
     */
    public void checkAvailableSell() {
        ArrayList<PropertySquare> propertyList = playerInTurn.hasBuilding();
        if (!propertyList.isEmpty()) {
            views.get(0).getDecision(propertyList, this);
            this.getPlayerInTurn().setDecision(Enums.SELLH);
        } else {
            System.out.println("No houses neither hotels");
        }
    }

    /**
     * A round of playing for every player
     */
    public void playRound() throws IOException, SAXException, ParserConfigurationException {
        // print which player's turn it is
        System.out.println("+----------+");
        System.out.println("NEXT ROUND : " + playerInTurn.getName());
        System.out.println("+----------+");

        //check and handle player hail status
        boolean inJail = playerInTurn.isInJail();
        int distance = getDistance();
        handlePlayerJailStatus(inJail,distance);

        //check and handle player bankrupt status
        if (playerInTurn.isBankrupt()) {
            if (!(playerInTurn instanceof AIPlayer)) {
                updateViews(playerInTurn, Enums.BANKRUPT);
            }
        }

        //check and handle game winner( if there is any)
        if (getWinner() != null) {
            updateViews(getWinner(), Enums.WINNER);
        }

        if (doubleCounter == 3 || doubleCounter == 0 && playerInTurn instanceof AIPlayer) {
            playerInTurn.setDiceRolled(true);
        } else if (!(playerInTurn instanceof AIPlayer)) {
            playerInTurn.setDiceRolled(true);
        }
    }

    public void handlePlayerJailStatus(Boolean inJail, int distanceToMove){
        //if player is in jail, only let player out if they rolled a double
        if (inJail) {
            if (dice.hasDoubles()) {
                updateViews(playerInTurn, Enums.DOUBLES);
                System.out.println("You rolled a double, you can go out!");

            } else {
                MonopolyBoard.jail.addCounter(playerInTurn);
                System.out.println("You did not roll a double!");
                //check if player hasn't rolled a double 3 times, make them pay jail fee and get out of jail
                if (MonopolyBoard.jail.getMap().get(playerInTurn) == 2) {
                    playerInTurn.decreaseCash(MonopolyBoard.jail.getJailFee());
                    MonopolyBoard.jail.goOutJail(playerInTurn);
                }
                updateViews(playerInTurn, Enums.NO_DOUBLES);
            }
        } else {  // if player not in jail
            if (dice.hasDoubles()) {
                doubleCounter++;
                updateViews(playerInTurn, Enums.DOUBLES);
            } else {
                movePlayer(playerInTurn, distanceToMove);
                updateViews(playerInTurn, Enums.ROLL_DICE);
            }
        }
    }

    public void removeBankruptPlayer(Player player) {
        for (PropertySquare property : player.getProperties()) {
            property.sellAll();
            player.setSelectedSquare(property);
            views.get(0).sellBuildBuilding(Enums.SELLH, Enums.HOTEL, player);
            for (int i = 0; i < 4; i++) {
                views.get(0).sellBuildBuilding(Enums.SELLH, Enums.HOUSE, player);
            }
            property.setOwner(null);
        }
        players.remove(player);


    }

    /**
     * display all needed information about players
     */
    public void printPlayersInfo() {
        System.out.println("\n+-------------------+");
        for (Player p : players) {
            System.out.println("\nPlayer #" + (players.indexOf(p) + 1) + " " + p.getName()); // player ID and Name
            System.out.println("Cash = $ " + p.getCash());                                   // player cash
            System.out.println("Properties = " + p.getProperties());                         // player properties
            System.out.println("Current location =  " + p.getCurrentLocation().toString());         // player location
        }
        System.out.print("Properties that are not bought yet: ");
        for(Square s: board.getSquares()){
            if(s instanceof PropertySquare && ((PropertySquare) s).getOwner() == null){
                System.out.print(s.getName() + "[" + s.getNumber() + "], ");
            }
        }
        System.out.println();
        System.out.println("+-------------------+\n");
    }

    public void addView(MonopolyGameGUI view) {
        views.add(view);
        view.loadGameGUI(board, players, playerInTurn);
    }

    /**
     * update GUI
     */
    private void updateViews(Player p, Enums command) {
        for (MonopolyGameGUI view : views) {
            view.handleUpdate(p, command, playersNotInTurn);
        }
    }

    /**
     * Gets the double counter int value
     *
     * @return
     */
    public int getDoubleCounter() {
        return doubleCounter;
    }

    /**
     * Sets the double counter value
     *
     * @param doubleCounter
     */
    public void setDoubleCounter(int doubleCounter) {
        this.doubleCounter = doubleCounter;
    }

    /**
     * get the player who is currently playing the game
     */
    public Player getPlayerInTurn() {
        return this.playerInTurn;
    }

    /**
     * go to next player
     */
    public void nextTurn() throws IOException, SAXException, ParserConfigurationException {
        doubleCounter = 0;
        playerInTurn.setDiceRolled(false);

        int currentIndex = players.indexOf(this.playerInTurn);
        if (currentIndex == players.size() - 1) currentIndex = -1;
        this.playerInTurn = players.get(currentIndex + 1);
        this.playersNotInTurn = getPlayersNotInTurn();
        if (index != -1) {
            players.remove(index);
            index = -1;
        }

        this.playersNotInTurn = getPlayersNotInTurn();

        updateViews(playerInTurn, Enums.NEXT_TURN);
        printPlayersInfo();

        AIProcess();
    }

    /**
     * set the selected property to the player in turn
     * ask the player to choose whether you want to build/sell a house or a hotel
     */
    public void setSelectedProperty(String text) throws IOException, SAXException, ParserConfigurationException {
        playerInTurn.setSelectedSquare(playerInTurn.getPropertyFromName(text));
        updateViews(playerInTurn, this.getPlayerInTurn().getDecision());
    }

    /**
     * List of players not in turn
     */
    public ArrayList<Player> getPlayersNotInTurn() {

        for (Player player : players) {
            if (player != playerInTurn) {
                if (!playersNotInTurn.contains(player)) {
                    playersNotInTurn.add(player);
                }
            } else {
                playersNotInTurn.remove(player);
            }
        }
        return playersNotInTurn;
    }

    /**
     * AI process during their turn
     */
    public void AIProcess() throws IOException, SAXException, ParserConfigurationException {
        if (playerInTurn instanceof AIPlayer) {
            int temp = this.doubleCounter;
            playRound();
            while (!playerInTurn.isInJail() && temp < this.doubleCounter && temp != 3) {
                temp = this.doubleCounter;
                playRound();
            }
            if (playerInTurn.isInJail() && this.doubleCounter == 1) {
                updateViews(playerInTurn, Enums.NO_DOUBLES);
            } else if (temp == 3) {
                updateViews(playerInTurn, Enums.DOUBLES);
            } else {
                playerInTurn.buyProperty(playerInTurn.getCurrentLocation());
                if (((AIPlayer) playerInTurn).buildBuildings()) {
                    views.get(0).sellBuildBuilding(Enums.BUILD, Enums.HOUSE, playerInTurn);
                }
                while (playerInTurn.isBankrupt() && !playerInTurn.hasBuilding().isEmpty()) {
                    ((AIPlayer) playerInTurn).sellBuildings();
                    views.get(0).sellBuildBuilding(Enums.SELLH, Enums.HOUSE, playerInTurn);
                }
                ((AIPlayer) playerInTurn).sellSomeThing();
            }
            if (playerInTurn.isBankrupt()) {
                this.removeBankruptPlayer(playerInTurn);
                this.updateViews(playerInTurn, Enums.BANKRUPT);
            } else if (this.getWinner() == playerInTurn) {
                this.updateViews(playerInTurn, Enums.WINNER);
            } else {
                nextTurn();
            }
        }
    }
}