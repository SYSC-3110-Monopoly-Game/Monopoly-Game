package Model;

import java.awt.*;

public class MonopolyBoard {


    private static final int SIZE = 33;
    private final Square[] squares;
    public static JailSquare jail;
    private int buyPrice = 60, rentPrice = 70;


    public MonopolyBoard() {
        squares = new Square[SIZE];
        makeSquares();
    }

    public Square[] getSquares() {
        return squares;
    }

    /**
     * Populates the board with Squares.
     */
    private void makeSquares() {
        int i = 0;
        int goMoney = 50;
        int incomeTax = 100;
        int jailFee = 100;

        squares[i] = new GoSquare("GO", i++, Color.WHITE, goMoney);
        squares[i] = new PropertySquare("Mediterranean Avenue", i++, buyPrice, rentPrice, Color.GRAY);
        squares[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, Color.GRAY);
        squares[i] = new IncomeTaxSquare("Income Tax", i++, incomeTax);

        squares[i] = new RailRoadSquare("Reading RailRoad", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, Color.CYAN);
        squares[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, Color.CYAN);
        squares[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, Color.CYAN);

        squares[i] = new JailSquare("Jail", i, jailFee);

        jail = (JailSquare) squares[i];
        i++;
        squares[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, Color.PINK);
        squares[i] = new UtilitySquare("Electric Company", i++, buyPrice, rentPrice, Color.WHITE);
        squares[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice, Color.PINK);
        squares[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice, Color.PINK);

        squares[i] = new RailRoadSquare("Pennsylvania RailRoad", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice, Color.ORANGE);
        squares[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice, Color.ORANGE);
        squares[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice, Color.ORANGE);

        squares[i] = new FreeParkingSquare("Free Parking", i++);

        squares[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, Color.RED);
        squares[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, Color.RED);
        squares[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, Color.RED);

        squares[i] = new RailRoadSquare("B.& O. RailRoad", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, Color.YELLOW);
        squares[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, Color.YELLOW);
        squares[i] = new UtilitySquare("Water Works", i++, buyPrice, rentPrice, Color.WHITE);
        squares[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, Color.YELLOW);

        squares[i] = new GoToJailSquare("Go to jail", i++, jail);

        squares[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, Color.GREEN);
        squares[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, Color.GREEN);
        squares[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, Color.GREEN);

        squares[i] = new RailRoadSquare("Short Line", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, Color.BLUE);
        squares[i] = new PropertySquare("Boardwalk", i, buyPrice, rentPrice, Color.blue);
    }

    /**
     * Returns the square located at the specified distance from the specified square
     */
    public Square getNextSquare(Square location, int distance) {
        int i = location.getNumber() + distance;
        if (i >= squares.length) return squares[i - SIZE];
        else return squares[i];
    }

    /**
     * Returns the Square where all players should be located at the start of the game.
     */
    public Square startingSquare() {
        return squares[0];
    }

    /**
     * Prints each square of the board
     */
    public void displayBoard() {
        System.out.println("\n+--------------------------+");
        System.out.println("Board Squares with positions");
        System.out.println("+--------------------------+\n");

        for (int i = 0; i < squares.length; i++) {

            System.out.println(squares[i].toString());
        }
        System.out.println();
    }
}