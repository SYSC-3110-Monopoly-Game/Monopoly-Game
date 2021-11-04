package Model;

public class MonopolyBoard {

    private static final int SIZE = 40;
    private final Square[] squares;
    public static JailSquare jail;

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
        int goMoney = 50;
        int incomeTax = 100;

        squares[i] = new GoSquare(i++, goMoney);
        squares[i] = new PropertySquare("Mediterranean Avenue", i++, buyPrice, rentPrice, "brown");
        squares[i] = new FreeParkingSquare("Community Chest", i++);
        squares[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, "brown");
        squares[i] = new IncomeTaxSquare("Income Tax", i++, incomeTax);

        squares[i] = new RailRoadSquare("Reading ReilRoad", i++, buyPrice, rentPrice);
        squares[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, "light blue");
        squares[i] = new FreeParkingSquare("Chance", i++);
        squares[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, "light blue");
        squares[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, "light blue");

        squares[i] = new JailSquare("Jail", i);

        jail = (JailSquare) squares[i];
        i++;
        squares[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, "pink");
        squares[i] = new UtilitySquare("Electric Company", i++, buyPrice, rentPrice);
        squares[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice, "pink");
        squares[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice, "pink");

        squares[i] = new RailRoadSquare("Pennsylvania RailRoad", i++, buyPrice, rentPrice);
        squares[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice, "orange");
        squares[i] = new FreeParkingSquare("Community Chest", i++);
        squares[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice, "orange");
        squares[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice, "orange");

        squares[i] = new FreeParkingSquare("Free Parking", i++);
        squares[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, "red");
        squares[i] = new FreeParkingSquare("Chance", i++);
        squares[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, "red");
        squares[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, "red");

        squares[i] = new RailRoadSquare("B.& O. RailRoad", i++, buyPrice, rentPrice);
        squares[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, "yellow");
        squares[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, "yellow");
        squares[i] = new UtilitySquare("Water Works", i++, buyPrice, rentPrice);
        squares[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, "yellow");

        squares[i] = new GoToJailSquare("Go to jail", i++, jail);
        squares[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, "green");
        squares[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, "green");
        squares[i] = new FreeParkingSquare("Community Chest", i++);
        squares[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, "green");

        squares[i] = new RailRoadSquare("Short Line", i++, buyPrice, rentPrice);
        squares[i] = new FreeParkingSquare("Chance", i++);
        squares[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, "dark blue");
        squares[i] = new IncomeTaxSquare("Luxury Tax", i++, incomeTax);
        squares[i] = new PropertySquare("Boardwalk", i, buyPrice, rentPrice, "dark blue");

    }

    /**
     * Returns the square located at the specified distance from the specified square
     */
    public Square getNextSquare(Square location, int distance) {
        int i = location.getNumber() + distance;
        if (i > squares.length) return squares[i - SIZE];
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