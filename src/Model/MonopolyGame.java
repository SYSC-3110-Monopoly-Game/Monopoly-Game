package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class MonopolyGame {
    private static MonopolyBoard board;
    private static ArrayList<Player> players;
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
        p.setLocation(MonopolyBoard.getNextSquare(p.getLocation(), distance));
//        print location after moving
        System.out.println("The square after moving is " + p.getLocation().toString());
    }

    /*
     * check if there is a winner
     * */
    private boolean playerWin() {
//        if more than 1 player exist, return 1 (keep playing game)
        //        otherwise, return the 0 (end the game)
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
            playerTempList.add(new Player(name, 0));//MonopolyBoard.square[0]));
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
            int rentFee;

//            check if the square can be bought
            if (p.getLocation() instanceof PropertySquare) {
//            check if the player need to pay rent fee for this property
                rentFee = checkRent(p);
//                if the player need to pay rent fee
                if (rentFee > 0) {
//                    player's cash decreases
                    p.decreaseCash(rentFee);
//                    print the player pay the rentfee
                    System.out.println(p.getName() + " has paid $" + rentFee);
//                    check if player is bankrupt
                    if (p.isBankrupt()) {
//                        print bankrupt information
                        System.out.println(p.getName() + " is bankrupt, removed from player list");
//                        if so, remove player from the templist
                        tempList.remove(p);
//                        go to next player
                        continue;
                    }
//                    if not bankrupt the landlord get the cash
                    ((PropertySquare) p.getLocation()).getOwner().increaseCash(((PropertySquare) p.getLocation()).getRentFee());
//                    print the landlord get the rent fee
                    System.out.println(((PropertySquare) p.getLocation()).getOwner() + " has received $" + rentFee);
                }
//                check if the player has enough money to buy the square
                if (p.getCash() - ((PropertySquare) p.getLocation()).getPrice() >= 0) {
//                    check if the player want to buy the square
                    if (ifWantToBuy((PropertySquare) p.getLocation())) {
//                        player buy the square
                        p.buyProperty(p.getLocation());
                    }
                }
            }
//        add other types of square here in else if

            //                    check if player is bankrupt
            if (p.isBankrupt()) {
//                        if so, remove player from the templist
                tempList.remove(p);
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
    check is the player want to buy the property
    * */
    private boolean ifWantToBuy(PropertySquare square) {
        Scanner input = new Scanner(System.in);
//        print out the information of the property that the player can buy
        System.out.println("If you want to buy " + square.getName() + "? (y/n)");
        System.out.println("Price: " + square.getPrice());
        System.out.println("Color: " + square.getColor());
//        get command from player
        String op = input.next();
//        if command not valid, get command again
        while (checkCommand(op) == -1) {
            System.out.print("\nPlease input a valid command:(y/n) ");
            op = input.next();
        }
        input.close();

//        y->return true | n->return false
        if (op.equals("y")) return true;
        else return false;
    }

    /*
        check what the command is
    * */
    private int checkCommand(String op) {
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
        }
        board.displayBoard();
    }

    /*
     * check if the player need to pay rent fee
     * */
    private int checkRent(Player p) {
//        get the square where the player is standing on
        PropertySquare currentLocation = (PropertySquare) p.getLocation();
//        get the name of the square's owner
        String owner = currentLocation.getOwner().getName();
//        if the property has owner and is not this player, return the payment amount
        if (owner != null && !owner.equals(p.getName())) {
            return currentLocation.getRentFee();
//            else the rent fee is 0
        } else {
            return 0;
        }
    }

    /*
     * refresh the players list
     * */
    private void refreshPlayer(ArrayList<Player> tempList) {
        players = tempList;
    }


    public static void main(String[] args) {
        MonopolyGame game = new MonopolyGame(new MonopolyBoard(), createPlayers());
        while (!game.playerWin()) {
            game.printPlayersInfo();
            ArrayList<Player> tempList = game.playRound();
            game.refreshPlayer(tempList);
        }
        System.out.println("Game Over, the winner is: " + game.getWinner().getName());
    }


}
