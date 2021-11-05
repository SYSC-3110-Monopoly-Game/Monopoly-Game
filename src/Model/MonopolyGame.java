package Model;

import view.MonopolyGameGUI;
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
//        get the number input
        int number = 4;

//        create players
        for (int i = 0; i < number; i++) {
            String name = "Player "+(i+1);
//            add the initiated players to a temp arraylist
            playerTempList.add(new Player(name, board.startingSquare()));
        }
        return playerTempList;
    }

    /**check what the command is
    */
    public static boolean checkCommand() {
        String op = input.next();
        while (op.charAt(0) != 'y' && op.charAt(0) != 'n') {
            System.out.print("\nPlease input a valid command:(y/n) ");
            op = input.next();
        }
        if (op.charAt(0) == 'y') {
            return true;
        } else {
            return false;
        }
    }

    /** move the player with a random distance
     * */
    public void movePlayer(Player p, int distance) {
        Square currentLocation = p.getLocation();
        currentLocation.landOff(p);
//        print current location
        //System.out.println("Current location: " + currentLocation.toString());
//            Move players to the square
        Square nextSquare = board.getNextSquare(p.getLocation(), distance);

        if(currentLocation.getNumber() > nextSquare.getNumber()) {
            board.startingSquare().landOn(p);
        }
        //        print location after moving
        //System.out.println("New location: " + nextSquare.toString() + "\n");
        nextSquare.landOn(p);
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
//        if more than 1 player exist, return null
        if (players.size() != 1)
            return null;
//        otherwise, return the player
        else
            return players.get(0);
    }

    /**do all needed check when rolling dices
    */
    public int rollDices(boolean inJail) {
        int counter = 0;
        int distance;
        dice = new Dice();
        if (inJail) {
            dice.rollDice();
            System.out.println("\t Dice Rolled!!\n" + dice.toString());
            System.out.println("+-------------------------+");
            if (dice.hasDoubles()){
                System.out.println("You rolled a double, you can go out!");
                distance = dice.getTotalValue();
            } else {
                System.out.println("You did not roll a double!");
                distance = -1;
            }
        } else {
            do {
                //        roll dices
                dice.rollDice();
                //        print the number of dices
                System.out.println("\t Dice Rolled!!\n" + dice.toString());
                System.out.println("+-------------------------+");
            }
            //counter count the number of dices being rolled
//        if two dices are same and have rolled less than 3 times, roll again
            while (dice.hasDoubles() && counter++ < 3);

//        if rolled 3 doubles
            if (counter >= 3){
                distance = -1;
            } else {
//        distance equals to the sum of 2 dices
                distance = dice.getTotalValue();
            }
        }

        return distance;
    }

    /**check is the player is bankrupt
    */
    public boolean checkBankrupt(Player p, ArrayList<Player> tempList) {
//                    check if player is bankrupt
        if (p.isBankrupt()){
            do {
//                sell properties until the player is not bankrupt
                if (p.getProperties().size() != 0) {
                    System.out.println(p.toString());
                    System.out.println("balance: " + p.getCash());
                    System.out.println("You are now bankrupt, please sell properties to gain money to avoid bankrupt\n" +
                            "Please input the index of property you want to sell: ");
                    int index = input.nextInt();
                    p.sellProperty(p.getProperties().get(index - 1));
                } else {
                    break;
                }
            } while (p.isBankrupt());

            if (p.isBankrupt()){
//                        if so, remove player from the templist
                System.out.println(p.getName() + " is bankrupt, removed from player list");
                tempList.remove(p);
                return true;
            }
            return false;
        }
        return false;
    }

    /**A round of playing for every player
    */
    public ArrayList<Player> playRound() {
        ArrayList<Player> tempList = new ArrayList<>(players);
        int distance;
        for (Player p : players) {
//            move the current player with a random distance
            System.out.println("+----------+");
            System.out.println("NEXT ROUND : " + p.getName());
            System.out.println("+----------+");

            boolean injail = ifInJail(p);
//            if the player is in jail
            if (injail){
                System.out.println("type ENTER to roll the dices!");
                input.nextLine();
                distance = rollDices(true);
            } else {
                distance = rollDices(false);
            }

            if (distance > 0){
                movePlayer(p, distance);

//              check if player is bankrupt
                if (checkBankrupt(p, tempList)) {
//              go to next player
                    continue;
                }
            } else if (distance == -1 && !injail) {
                System.out.println("You have rolled three doubles");
                MonopolyBoard.jail.landOn(p);
            } else if (distance == -1 && injail){
                System.out.println("You are still in jail");
            }
//            press enter to go to the next player turn
            System.out.println("Press <Enter> to pass your turn to next player");
            input.nextLine();

        }
        return tempList;
    }

    /** display all needed information about players
     * */
    public void printPlayersInfo() {
        System.out.println("\n+-------------------+");
        for (Player p : players) {
            System.out.println("\nPlayer #" + (players.indexOf(p) + 1) + " " + p.getName());
            System.out.println("Cash = $ " + p.getCash());
            System.out.println("Properties = " + p.getProperties());
            System.out.println("Current location =  " + p.getLocation().toString());
        }
        System.out.println("+-------------------+\n");
    }

    /*
     * refresh the players list
     * */
    public void refreshPlayer(ArrayList<Player> tempList) {
        players = tempList;
    }

    /** check if the player will be in jail in this round
    * */
    public boolean ifInJail(Player p){
        HashMap jail = MonopolyBoard.jail.getMap();
        if (jail != null && jail.containsKey(p)){
            if (MonopolyBoard.jail.getMap().get(p) <= 3){
                System.out.println("Do you wish to go out of the jail by paying $50?:(y/n) ");
                if (!checkCommand()) {
                    p.decreaseCash(50);
                    MonopolyBoard.jail.goOutJail(p);
                    return true;
                }
            }
            MonopolyBoard.jail.goOutJail(p);
        }
        return false;
    }


}