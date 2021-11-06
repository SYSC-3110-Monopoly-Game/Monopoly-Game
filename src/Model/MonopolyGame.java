package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MonopolyGame {
    public static MonopolyBoard board;
    public static ArrayList<Player> players;
    public static Scanner input;
    public Dice dice;

    public MonopolyGame() {
        board = new MonopolyBoard();
        players = createPlayers();
        input = new Scanner(System.in);
    }

    public static MonopolyBoard getBoard() {
        return board;
    }

    public void closeScanner() {
        input.close();
    }

    /** set 4 players
     * */
    public ArrayList<Player> createPlayers() {
        ArrayList<Player> playerTempList = new ArrayList<>();
        System.out.println("Welcome to Monopoly Game!!");
        System.out.println("There are 4 player in total");

        int number = 4;                                                      // get the number of players to 4

        /**create the 4 players*/
        for (int i = 0; i < number; i++) {
            String name = "Player "+(i+1);
            playerTempList.add(new Player(name, board.startingSquare()));   // add players to a temp arraylist
        }
        return playerTempList;                                              // return the temp arrayList
    }

    /**check what the command is
    */
    public static boolean checkCommand() {
        String op = input.next();
        while (op.charAt(0) != 'y' && op.charAt(0) != 'n') {       // if input is neither 'y' nor 'n', get input again
            System.out.print("\nPlease input a valid command:(y/n) ");
            op = input.next();                                         // get user input
        }
        if (op.charAt(0) == 'y') {                                      // if input is y; return true
            return true;
        } else {                                                        // if input is n; return false
            return false;
        }
    }

    /** move the player with a random distance
     * */
    public void movePlayer(Player p, int distance) {
        Square currentLocation = p.getLocation();
        System.out.println("Current location: " + currentLocation.toString());      // show current location

        Square nextSquare = board.getNextSquare(p.getLocation(), distance);     // calculate the square will be reached

        if(currentLocation.getNumber() > nextSquare.getNumber() && nextSquare.getNumber() != 0) {   // if the square--
                                                                // will reached passes GoSquare AND is Not GoSquare
            board.startingSquare().landOn(p);                                       // land the player on GoSquare
        }
        nextSquare.landOn(p);                                                   // Move player to the calculated square
        System.out.println("New location: " + nextSquare.toString() + "\n");        // show location after moving
    }

    /** check if there is a winner
     * */
    public boolean playerWin() {
//        if more than 1 player exist, return true (keep playing game)
        //        otherwise, return the false (end the game)
        return players.size() == 1;
    }


    /** return the winner of the game
     * */
    public Player getWinner() {
        if (players.size() != 1)                        // if more than 1 player exist
            return null;                                // return nothing

        else                                            // if only 1 player exist
            return players.get(0);                      // return the winner
    }


    /**do all needed check when rolling dices
    */
    public int rollDices(boolean inJail) {
        int counter = 0;                                                // counter for 3 doubles checking
        int distance;
        dice = new Dice();
        if (inJail) {                                                   // if player in jail
            dice.rollDice();                                            // roll dice
            System.out.println("\t Dice Rolled!!\n" + dice.toString());
            System.out.println("+-------------------------+");
            if (dice.hasDoubles()){                                     // if player rolls a double
                System.out.println("You rolled a double, you can go out!");
                distance = dice.getTotalValue();                        // distance available; distance = sum of 2 Dice

            } else {                                                    // if player did not roll a double
                System.out.println("You did not roll a double!");
                distance = -1;                                          // distance unavailable; distance = -1
            }

        } else {                                                        // if player not in jail
            dice.rollDice();                                            // roll dices
            System.out.println("\t Dice Rolled!!\n" + dice.toString());
            System.out.println("+-------------------------+");
            while (dice.hasDoubles() && ++counter < 3){                 // if has double, go into the while loop
                dice.rollDice();                                        // roll dices
                System.out.println("\t Dice Rolled!!\n" + dice.toString());
                System.out.println("+-------------------------+");
            }                                                           // each time a double is rolled, counter++

            if (counter >= 3){                                          // if rolled 3 doubles
                distance = -1;                                          // distance unavailable; distance = -1
            } else {                                                    // if not rolled 3 doubles

                distance = dice.getTotalValue();                        // distance equals to the sum of 2 dices
            }
        }

        return distance;                                                // return distance
    }


    /**check is the player is bankrupt
    */
    public boolean checkBankrupt(Player p, ArrayList<Player> tempList) {

        if (p.isBankrupt()){                                        // check if player is bankrupt
            /** do while loop: player sell properties until he/she is not bankrupt */
            do {
                if (p.getProperties().size() != 0) {                // if player have properties to sell
                    System.out.println(p.toString());               // show information of the properties
                    System.out.println("balance: " + p.getCash());  // show how much player should pay
                    System.out.println(
                            "You are now bankrupt, please sell properties to gain money to avoid bankrupt\n" +
                            "Please input the index of property you want to sell: ");
                    int index = input.nextInt();
                    p.sellProperty(p.getProperties().get(index - 1));// sell the property with the given index

                } else {                                            // if player do not have any property
                    break;                                          // break the loop
                }
            } while (p.isBankrupt());                               // if player is still bankrupt, loop again



            if (p.isBankrupt()){                                    // if player is still bankrupt

                System.out.println(p.getName() + " is bankrupt, removed from player list");
                tempList.remove(p);                                 // remove player from the temp player list

                return true;                                        // someone is removed from list
            }
            return false;                                           // no one is removed
        }
        return false;                                               // no one is removed
    }


    /**A round of playing for every player
    */
    public ArrayList<Player> playRound() {
        ArrayList<Player> tempList = new ArrayList<>(players);          // create a tempList of player in case we need--
                                                                        // to remove players
        int distance;
        for (Player p : players) {
            System.out.println("+----------+");                         //
            System.out.println("NEXT ROUND : " + p.getName());          // print which player's turn it is
            System.out.println("+----------+");                         //

            boolean injail = ifInJail(p);                               // return if or not the player is in jail now

            if (injail){
                System.out.println("type ENTER to roll the dices!");
                input.nextLine();
                distance = rollDices(true);                       // roll dice for player in jail
            } else {
                distance = rollDices(false);                      // roll dice for player not in jail
            }

            if (distance > 0){                                          // if dices numbers are good to move the player

                movePlayer(p, distance);                                // move player for distance

                if(MonopolyBoard.jail.getMap().containsKey(p)){         // if the player is currently in jail but the--
                                                                        // player rolled a double and get out.
                    MonopolyBoard.jail.goOutJail(p);                    // player out of jail immediately
                }

                if (checkBankrupt(p, tempList)) {                       // if player is bankrupt or not
                    continue;                                           // go to next player if this one is bankrupt
                }

            } else if (distance == -1 && !injail) {                     // if dices numbers are not good for move AND--
                                                                        // the player NOT in jail currently
                System.out.println("You have rolled three doubles\nYou have go to jail");
                MonopolyBoard.jail.goJail(p);                           // let the player go to jail

            } else if (distance == -1){                                 // if dices numbers are not good for move AND--
                                                                        // if player in jail currently
                System.out.println("You are still in jail");
            }
            System.out.println("Press <Enter> to pass your turn to next player");
            input.nextLine();                                           // type something to go to next player's turn

        }
        MonopolyBoard.jail.IncrementJail();                             // when all 4 players finish 1 round
                                                                        // all players in jail -> counter++

        return tempList;                                                // return the edited player list
    }


    /** display all needed information about players
     * */
    public void printPlayersInfo() {
        System.out.println("\n+-------------------+");
        for (Player p : players) {
            System.out.println("\nPlayer #" + (players.indexOf(p) + 1) + " " + p.getName()); // player ID and Name
            System.out.println("Cash = $ " + p.getCash());                                   // player cash
            System.out.println("Properties = " + p.getProperties());                         // player properties
            System.out.println("Current location =  " + p.getLocation().toString());         // player location
        }
        System.out.println("+-------------------+\n");
    }


    /** refresh the players list
     * */
    public void refreshPlayer(ArrayList<Player> tempList) {
        players = tempList;
    }


    /** check if the player will be in jail in this round (true for in jail, false for not)
    * */
    public boolean ifInJail(Player p){
        HashMap jail = MonopolyBoard.jail.getMap();

        if (jail != null && jail.containsKey(p)){           // if the player is currently in jail

            if (MonopolyBoard.jail.getMap().get(p) < 3){    // if the player is in jail less than 3 rounds
                System.out.println("Do you wish to go out of the jail by paying $50?:(y/n) ");
                if (checkCommand()) {                       // if player pay $50
                    p.decreaseCash(50);
                    MonopolyBoard.jail.goOutJail(p);        // the player get out immediately
                    return false;
                } else {                                    // if the player do not pay $50,
                    return true;                            // The player stay in jail
                }
            } else {                                        // if the player is in jail for 3 rounds
                MonopolyBoard.jail.goOutJail(p);            // the player get out of jail
                p.decreaseCash(50);                 // the player pays $50
                System.out.println("You have go out of the jail!");
            }
        }
        return false;                                       // the player is not in jail
    }


}