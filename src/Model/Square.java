package Model;

public abstract class Square {

    private final String name;
    private final int number;
    public String message;

    public Square(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
    get the name of the square
     */
    public String getName() {
        return name;
    }

    public String toString() {
        return this.name + " [" + this.getNumber() + "]";
    }

    /**
    get the specific id of the square
     */
    public int getNumber() {
        return this.number;
    }

    public abstract void landOn(Player p);
}
