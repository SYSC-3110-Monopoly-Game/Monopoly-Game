package Model;

public abstract class Square {

    private final String name;
    private final int id;

    public Square(String name, int number){
        this.name = name;
//        wait for monopolyboard class
        this.id = number;
    }

    /*
    get the name of the square
     */
    public String getName() {
        return name;
    }

    public String toString() {
        return this.name + " at position: " + this.getId();
    }

    /*
    get the specific id of the square
     */
    public int getId() {
        return this.id;
    }

    public abstract void landOn (Player p);
}
