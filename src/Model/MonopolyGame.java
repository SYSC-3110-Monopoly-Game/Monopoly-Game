package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyGame {
    public static MonopolyBoard board;
    public static ArrayList<Player> players;
    private Dice dice;

    MonopolyGame() {
    }

    MonopolyGame(MonopolyBoard board) {
    }

    MonopolyGame(MonopolyBoard b, ArrayList<Player> p) {
        board = b;
        players = p;
    }

    /*
     * move the player with a random distance
     * */
    private void movePlayer(Player p) {
//        roll dice and record the distance.
        int distance = rollDices();
//        print current location
        System.out.println("The current square is " + p.getLocation().toString());
//            Move players to the square
        p.setLocation(board.getNextSquare(p.getLocation(), distance));
//        print location after moving
        System.out.println("The square after moving is " + p.getLocation().toString());
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
        while (dice.hasDoubles()){
            dice.rollDice();
        }
//        distance equals to the sum of 2 dices
        int distance = dice.getTotalValue();
//        print the number of dices
        System.out.println("The number of the dice is: " + dice.toString());

        return distance;
    }

    private boolean checkBankrupt(Player p, ArrayList<Player> tempList){
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
     * ask user to input the number of players and their name
     * */
    private static ArrayList<Player> createPlayers() {
        ArrayList<Player> playerTempList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Please input the number of player (2~4): ");
//        get the number input
        int number = input.nextInt();
//        if out of range, input again
        if (number < 2 || number > 4) {
            System.out.print("\nPlease input number in the range 2~4: ");
            number = input.nextInt();
        }
//        pass the input item in register
        input.nextLine();
        for (int i = 0; i < number; i++) {
//            input name for each player
            System.out.print("\nPlease input the name for Player " + (i + 1) + ": ");
            String name = input.nextLine();
//            add the initiated players to a temp arraylist
            Player p = new Player(name);
            p.setLocation(board.startingSquare());
            playerTempList.add(p);//MonopolyBoard.square[0]));
        }
        input.close();
        return playerTempList;
    }

    /*
    A round of playing for every player
    */
    private ArrayList<Player> playRound() {
        ArrayList<Player> tempList = players;
        for (Player p : players) {
//            move the current player with a random distance
            movePlayer(p);

            p.getLocation().landOn(p);

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
        check what the command is
    * */
    public static int checkCommand(String op) {
        if (op.equals("y")) {
            return 1;
        } else if (op.equals("n")) {
            return 0;
        } else {
            return -1;
        }
    }

    /*
     * display all needed information about players
     * */
    private void printPlayersInfo() {
        for (Player p : players) {
            System.out.println(p.getName());
            System.out.println("The player has $" + p.getCash());
            System.out.println("The player has properties: " + p.getProperties());
            System.out.println("The player is at " + p.getLocation().toString());
        }
        board.displayBoard();
    }

    /*
     * refresh the players list
     * */
    private void refreshPlayer(ArrayList<Player> tempList) {
        players = tempList;
    }


    public static void main(String[] args) {
        board = new MonopolyBoard();
        MonopolyGame game = new MonopolyGame(board, createPlayers());
        while (!game.playerWin()) {
            game.printPlayersInfo();
            ArrayList<Player> tempList = game.playRound();
            game.refreshPlayer(tempList);
        }
        System.out.println("Game Over, the winner is: " + game.getWinner().getName());
    }


}
