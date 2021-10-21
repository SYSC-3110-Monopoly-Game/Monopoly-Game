package Model;

public abstract class Square {

    private String name;
    private int id;

    public Square(String name, int number){
        this.name = name;
        this.id = number;
    }

    /*
    get the name of the square
     */
    public String getName() {
        return name;
    }

    /*
    get the specific id of the square
     */
    public int getId() {
        return id;
    }

    public abstract void landOn (Player p);
}
