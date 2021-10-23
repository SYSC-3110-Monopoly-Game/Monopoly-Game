package Model;

public class PropertySquare extends Square{

    private final int buyPrice; //price for the player buy this land
    private final int rentPrice; //the price that other players need to pay to the owner.
    private final String color;
    private boolean sold;
    private Player owner = null;


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
    public int getPrice() {
        return buyPrice;
    }

    /*
    get the land rent price (square toll)
     */
    public int getRentFee() {
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


    /*
    * action applied when player lands on this square
    * */
    @Override
    public void landOn (Player p){
        p.setLocation(this);

//        set the rent fee of the location to fee
        int fee = this.getRentFee();
//            set the price of the location to price
        int price = this.getPrice();
//            set the owner of the location to owner
        Player owner = this.getOwner();
//            if the square has an owner and the owner is not this player
        if (p != owner && owner != null) {
//                if the player can't afford the rent fee
            if (p.getCash() < fee) {
//                    if the player can't sell some properties to keep not bankrupt
                if (!p.selectWhichToSell(fee)) {
//                        set the amount to pay  to the player's cash
                    fee = p.getCash();
                }
            }
//                owner get the amount to be paid by the player
            owner.increaseCash(fee);
            p.decreaseCash(fee);

//                    print the player paid the rent fee
            System.out.println("!!!You need to pay rent!!!");
            System.out.println(p.getName() + " has paid $" + fee);
//                    print the landlord get the rent fee
            System.out.println(owner.getName() + " has received $" + fee);
        } else if (owner == null) {
//                check if the player has enough money to buy the square
            if (p.getCash() - price >= 0) {
//                    check if the player want to buy the square
                if (p.ifWantToBuy(this)) {
//                        player buy the square
                    p.buyProperty(this);
                }
            }
        }
    }

}
