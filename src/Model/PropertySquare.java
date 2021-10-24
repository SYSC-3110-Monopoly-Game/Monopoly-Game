package Model;

public class PropertySquare extends Square{

    private int buyPrice; //price for the player buy this land
    private int rentPrice; //the price that other players need to pay to the owner.
    private String color;
    private boolean sold;
    private Player owner;


    public PropertySquare(String name, int number, int buy, int rent, String color) {
        super(name, number);
        this.buyPrice = buy;
        this.rentPrice = rent;
        this.color = color;
        this.sold = false;
    }

    /*
    get the color of the square
     */
    public String getColor() {
        return color;
    }

    /*
     get the land sale price
     */
    public int getBuyPrice() {
        return buyPrice;
    }

    /*
    get the land rent price (square toll)
     */
    public int getRentPrice() {
        return rentPrice;
    }

    /*
    if the square has an owner
     */
    public boolean isSold() {
        return sold;
    }

    /*
    player who buy this land
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /*
    get the player who own this land
     */
    public Player getOwner() {
        return owner;
    }


    public void landOn (Player p){
        if (p != this.owner){
            if (p.getCash < getRentPrice()){
                this.owner.increaseCash(p.getCash());
                p.setCash(p.getCash - this.rentPrice);
            }
            else {
                p.decreaseCash(getRentPrice());
                this.owner.increaseCash(getRentPrice());
            }
        }
    }

}
