package Model;

public class UtilitySquare extends PropertySquare{

    private final int rentPrice;

    public UtilitySquare(String name, int number, int buy, int rent, String color, int rentPrice) {
        super(name, number, buy, rent, color);
        this.rentPrice = rentPrice;
    }
    public int getRentFee() {
        return rentPrice;
    }

}
