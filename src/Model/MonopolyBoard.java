package Model;

import java.awt.*;

public class MonopolyBoard {


    private static final int SIZE = 33;
    private Square[] squares;
    private int buyPrice = 50, rentPrice = 30;


    public MonopolyBoard() {
        squares = new Square[SIZE];
        makeSquares();
    }

    /**
     * Populates the board with Squares.
     */
    private void makeSquares() {
        int i = 0;
        int buyPrice = 50;
        int rentPrice = 30;
        squares[i] = new PropertySquare("Mediterranean Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squares[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squares[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, Color.BLUE);
        squares[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, Color.BLUE);
        squares[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, Color.BLUE);

        squares[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, Color.PINK);
        squares[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice,  Color.PINK);
        squares[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice,  Color.PINK);

        squares[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice,  Color.PINK);
        squares[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice,  Color.PINK);
        squares[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice,  Color.PINK);

        squares[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, Color.RED);
        squares[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, Color.RED);
        squares[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, Color.RED);

        squares[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, Color.YELLOW);
        squares[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, Color.YELLOW);
        squares[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, Color.YELLOW);

        squares[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, Color.GREEN);
        squares[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, Color.GREEN);
        squares[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, Color.GREEN);

        squares[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, Color.BLUE);
        squares[i] = new PropertySquare("Boardwalk", i++, buyPrice, rentPrice, Color.BLUE);

    }

    /**
     * Returns the square located at the specified distance from the specified square
     */
    public Square getNextSquare(Square location, int distance) {
        int i = location.getNumber() + distance;
        if (i > 21/*squares.length*/) return squares[i - 21];
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

        for (int i = 0; i < 22; i++) {

            System.out.println(squares[i].toString());
        }
        System.out.println();
    }
}