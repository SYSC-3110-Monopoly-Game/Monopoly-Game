package Model;

import view.MonopolyGameGUI;

import java.util.*;

public class MonopolyGame {
    public static MonopolyBoard board;
    public static Dice dice;
    public ArrayList<Player> players;
    private ArrayList<MonopolyGameGUI> views;
    private Player playerInTurn;
    private int doubleCounter;
    private AIPlayer ai;
    private PropertySquare square;
    private MonopolyGame mg;

    private ArrayList<Player> playersNotInTurn;

    private int index = -1;

    public MonopolyGame() {
        board = new MonopolyBoard();
        players = createPlayers();
        playersNotInTurn = new ArrayList<>();
        dice = new Dice();
        views = new ArrayList<>();
        printPlayersInfo();
    }


    /**
     * set 4 players
     */
    private ArrayList<Player> createPlayers() {
        ArrayList<Player> playerTempList = new ArrayList<>();
        System.out.println("Welcome to Monopoly Game!!");
        System.out.println("There are 4 player in total");

        int number = 4;                                                      // get the number of players to 4

        //create the 4 players
        for (int i = 0; i < number; i++) {
            String name = "" + (i + 1);
            Player p = new Player(name, board.startingSquare());
            p.setLastLocation(board.startingSquare());
            playerTempList.add(p);   // add players to a temp arraylist

        }
        this.playerInTurn = playerTempList.get(0);
        return playerTempList;                                              // return the temp arrayList
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
    public void buySquare() {
        if (playerInTurn.buyProperty(playerInTurn.getCurrentLocation())) {
            this.updateViews(playerInTurn, "Buy");
        }
    }

    /**
     * sell properties
     *
     */
    public void sellSquare() {
        if (!playerInTurn.getProperties().isEmpty()) {
            this.updateViews(playerInTurn, "Sell");
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
                this.getPlayerInTurn().setDecision("build");
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
            this.getPlayerInTurn().setDecision("sellH");
        } else {
            System.out.println("No houses neither hotels");
        }
    }

    /**
     * A round of playing for every player
     */
    public void playRound() {
        // print which player's turn it is
        System.out.println("+----------+");
        System.out.println("NEXT ROUND : " + playerInTurn.getName());
        System.out.println("+----------+");

        boolean inJail = playerInTurn.isInJail();
        int distance = getDistance();


        //if player is in jail, only let player out if they rolled a double
        if (inJail) {
            if (dice.hasDoubles()) {
                updateViews(playerInTurn, "Doubles");
                System.out.println("You rolled a double, you can go out!");

            } else {
                System.out.println("You did not roll a double!");
                MonopolyBoard.jail.addCounter(playerInTurn);
                //check if player hasn't rolled a double 3 times, make them pay jail fee and get out of jail
                if (MonopolyBoard.jail.getMap().get(playerInTurn) == 2) {
                    playerInTurn.decreaseCash(MonopolyBoard.jail.getJailFee());
                    MonopolyBoard.jail.goOutJail(playerInTurn);
                }
            }
        } else {  // if player not in jail
            if (dice.hasDoubles()) {
                updateViews(playerInTurn, "Doubles");
                doubleCounter++;
            }else {
                movePlayer(playerInTurn, distance);
                updateViews(playerInTurn, "Roll Dice");
            }

        }



        if (playerInTurn.isBankrupt()) {
            updateViews(playerInTurn, "Bankrupt");
        }
        //index = players.indexOf(playerInTurn);

        if (getWinner() != null) {
            updateViews(getWinner(), "Winner");
        }
    }

    public void removeBankruptPlayer(Player player){
        for(PropertySquare property: player.getProperties()){
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
        System.out.println("+-------------------+\n");
    }

    public void addView(MonopolyGameGUI view) {
        views.add(view);
    }

    /**
     * update GUI
     */
    private void updateViews(Player p, String command) {
        for (MonopolyGameGUI view : views) {
            view.handleUpdate(p, command, playersNotInTurn);

        }
    }

    public int getDoubleCounter() {
        return doubleCounter;
    }

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
    public void nextTurn() {

        int currentIndex = players.indexOf(this.playerInTurn);
        if (currentIndex == players.size() - 1) currentIndex = -1;
        this.playerInTurn = players.get(currentIndex + 1);
        this.playersNotInTurn = getPlayersNotInTurn();
        if (index != -1) {
            players.remove(index);
            index = -1;
        }

        this.playersNotInTurn = getPlayersNotInTurn();

        updateViews(playerInTurn, "Next Turn");

        AIProcess();

    }

    /**
     * set the selected property to the player in turn
     * ask the player to choose whether you want to build/sell a house or a hotel
     */
    public int setSelectedProperty(String text) {
        playerInTurn.setSelectedSquare(playerInTurn.getPropertyFromName(text));
        updateViews(playerInTurn, this.getPlayerInTurn().getDecision());

        for (int i = 0; i < board.getSquares().length; i++) {
            if (text.equals(board.getSquares()[i].getName())) {
                return i;
            }
        }
        return -1;
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
                if (playersNotInTurn.contains(player)) {
                    playersNotInTurn.remove(player);
                }
            }
        }
        return playersNotInTurn;
    }

    public void AIProcess() {
        if (playerInTurn instanceof AIPlayer) {
            playRound();
            playerInTurn.buyProperty(playerInTurn.getCurrentLocation());
            ((AIPlayer) playerInTurn).buildBuildings();
            ((AIPlayer) playerInTurn).sellSomeThing();
            nextTurn();
        }
    }
}