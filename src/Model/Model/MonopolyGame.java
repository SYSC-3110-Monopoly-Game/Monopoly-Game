package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyGame {
    public static MonopolyBoard board;
    public static ArrayList<Player> players;
    public static Scanner input;
    private Dice dice;

    MonopolyGame(MonopolyBoard b, ArrayList<Player> p) {
        board = b;
        players = p;
    }

    /*
     * ask user to input the number of players and their name
     * */
    private static ArrayList<Player> createPlayers() {
        ArrayList<Player> playerTempList = new ArrayList<>();
        System.out.println("Welcome to Monopoly Game!!");
        System.out.print("Please input the number of player (2~4): ");
//        get the number input
        int number = input.nextInt();
//        if out of range, input again
        while (number < 2 || number > 4) {
            System.out.print("Please input number in the range 2~4: ");
            number = input.nextInt();
        }
//        pass the input item in register
        input.nextLine();
        for (int i = 0; i < number; i++) {
//            input name for each player
            System.out.print("Please input the name for Player " + (i + 1) + ": ");
            String name = input.nextLine();
//            add the initiated players to a temp arraylist
            playerTempList.add(new Player(name, board.startingSquare()));
        }
        return playerTempList;
    }

    /*
        check what the command is
    * */
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

    public static void main(String[] args) {
        board = new MonopolyBoard();
        input = new Scanner(System.in);
        MonopolyGame game = new MonopolyGame(board, createPlayers());
        board.displayBoard();
        while (!game.playerWin()) {
            game.printPlayersInfo();
            ArrayList<Player> tempList = game.playRound();
            game.refreshPlayer(tempList);
        }
        System.out.println("Game Over, the winner is: " + game.getWinner().getName());
        input.close();
    }

    /*
     * move the player with a random distance
     * */
    private void movePlayer(Player p) {
//        roll dice and record the distance.
        int distance = rollDices();
//        print current location
        System.out.println("Current location: " + p.getLocation().toString());
//            Move players to the square
        Square nextSquare = board.getNextSquare(p.getLocation(), distance);
        nextSquare.landOn(p);

//        print location after moving
        System.out.println("New location: " + p.getLocation().toString() + "\n");
    }

    /*
     * check if there is a winner
     * */
    private boolean playerWin() {
//        if more than 1 player exist, return true (keep playing game)
        //        otherwise, return the false (end the game)
        return players.size() == 1;
    }

    /*
     * return the winner of the game
     * */
    private Player getWinner() {
//        if more than 1 player exist, return null
        if (players.size() != 1)
            return null;
//        otherwise, return the player
        else
            return players.get(0);
    }

    /*
     *do all needed check when rolling dices
     */
    private int rollDices() {
        dice = new Dice();
//        roll dices
        dice.rollDice();
//        if two dices are same, roll again
        while (dice.hasDoubles()) {
            dice.rollDice();
        }
//        distance equals to the sum of 2 dices
        int distance = dice.getTotalValue();
//        print the number of dices
        System.out.println("\t Dice Rolled!!\n" + dice.toString());
        System.out.println("+-------------------------+");


        return distance;
    }

    private boolean checkBankrupt(Player p, ArrayList<Player> tempList) {
//                    check if player is bankrupt
        if (p.isBankrupt()) {
            // if can sell properties to be not bankrupt?
            // if so, sell the selected properties
            // return false;
//                        print bankrupt information
            System.out.println(p.getName() + " is bankrupt, removed from player list");
//                        if so, remove player from the templist
            tempList.remove(p);
        }
        return true;
    }

    /*
    A round of playing for every player
    */
    private ArrayList<Player> playRound() {
        ArrayList<Player> tempList = new ArrayList<>(players);
        for (Player p : players) {
//            move the current player with a random distance
            System.out.println("+----------+");
            System.out.println("NEXT ROUND : " + p.getName());
            System.out.println("+----------+");

            movePlayer(p);

//                    check if player is bankrupt
            if (checkBankrupt(p, tempList)) {
//                        go to next player
                continue;
            }
//            press enter to go to the next player turn
            System.out.println("Press <Enter> to pass your turn to next player");
            Scanner input = new Scanner(System.in);
            input.nextLine();
            input.close();
        }
        return tempList;
    }

    /*
     * display all needed information about players
     * */
    private void printPlayersInfo() {
        System.out.println("\n+-------------------+");
        for (Player p : players) {
            System.out.println("\nPlayer #" + (players.indexOf(p)+1) + " " + p.getName());
            System.out.println("Cash = $ " + p.getCash());
            System.out.println("Properties = " + p.getProperties());
            System.out.println("Current location =  " + p.getLocation().toString());
        }
        System.out.println("+-------------------+\n");
    }

    /*
     * refresh the players list
     * */
    private void refreshPlayer(ArrayList<Player> tempList) {
        players = tempList;
    }


}
