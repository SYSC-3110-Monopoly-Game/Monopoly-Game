package Model;

public class RailRoadSquare extends PropertySquare {

    private final int rentPrice;
    private final int railRoadNum;

    public RailRoadSquare(String name, int number, int buy, int rent, String color, int rentPrice, int railRoadNum) {
        super(name, number, buy, rent, color);
        this.rentPrice = rentPrice;
        this.railRoadNum = railRoadNum;
    }
    public int getRentFee() {
        return rentPrice * railRoadNum;
    }

}
