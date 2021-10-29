package Model;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String name, int number) {
        super(name, number);
    }

    @Override
    public void landOn(Player p) {
        System.out.println("!!!Oh, it's free!!!");
    }
}
