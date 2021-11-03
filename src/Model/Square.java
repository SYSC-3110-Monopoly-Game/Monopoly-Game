package Model;

import java.awt.Color;

public abstract class Square {

    private final String name;
    private final int number;
    private final Color color;

    public Square(String name, int number, Color color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

    /** Returns the color of the square
     *
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /*
    get the name of the square
     */
    public String getName() {
        return name;
    }

    public String toString() {
        return this.name + " [" + this.getNumber() + "]";
    }

    /*
    get the specific id of the square
     */
    public int getNumber() {
        return this.number;
    }

    public abstract void landOn(Player p);
}