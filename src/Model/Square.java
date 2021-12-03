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
     * get the name of the square
     */
    public String getName() {
        return name;
    }

    /**
     * String representation of square
     *
     * @return string
     */
    public String toString() {
        return this.name + " [" + this.getNumber() + "]";
    }

    /**
     * get the specific id of the square
     */
    public int getNumber() {
        return this.number;
    }

    public abstract void landOn(Player p);

    /**
     * convert the square to xml version
     *
     * @return xml version String
     */
    public abstract String toXML();

}