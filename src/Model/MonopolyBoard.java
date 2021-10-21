package Model;

public class MonopolyBoard {

    private static final int SIZE = 40;
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
        squares[i] = new PropertySquare("Mediterranean Avenue", i++, buyPrice, rentPrice, "brown");
        squares[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, "brown");

        squares[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, "light blue");
        squares[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, "light blue");
        squares[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, "light blue");

        squares[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, "pink");
        squares[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice, "pink");
        squares[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice, "pink");

        squares[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice, "orange");
        squares[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice, "orange");
        squares[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice, "orange");

        squares[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, "red");
        squares[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, "red");
        squares[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, "red");

        squares[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, "yellow");
        squares[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, "yellow");
        squares[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, "yellow");

        squares[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, "green");
        squares[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, "green");
        squares[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, "green");

        squares[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, "dark blue");
        squares[i] = new PropertySquare("Boardwalk", i++, buyPrice, rentPrice, "dark blue");

    }

    /**
     * Returns the square located at the specified distance from the specified square
     */
    public Square getNextSquare(Square location, int distance) {
        int i = location.getId() + distance;
        if (i > squares.length) return squares[i - squares.length];
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
        for (Square square :
                this.squares) {
            square.toString();
        }
    }
}
