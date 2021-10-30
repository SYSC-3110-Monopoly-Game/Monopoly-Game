package Model;

public class UtilitySquare extends Square{
    private final int buyPrice; //price for the player buy this land
    private final int rentPrice; //the price that other players need to pay to the owner.
    private boolean sold;
    private Player owner = null;


    public UtilitySquare(String name, int number, int buy, int rent) {
        super(name, number);
        this.buyPrice = buy;
        this.rentPrice = rent;
        this.sold = false;
    }
    public int getPrice() {
        return buyPrice;
    }
    public int getRentFee() {
        return rentPrice;
    }
    public boolean isSold() {
        return sold;
    }
    public Player getOwner() {
        return owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /*
     * action applied when player lands on this square
     * */
    @Override
    public void landOn(Player p) {
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
