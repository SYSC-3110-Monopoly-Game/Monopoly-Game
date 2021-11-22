package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class PropertySquare extends Square {

    private final int buyPrice; //price for the player buy this land
    private final int rentPrice; //the price that other players need to pay to the owner.
    private final Color color;
    private Player owner = null;
    private final int HousePrice;


    private static class House {
        private final int price;

        public House(int price){
            this.price = price;
        }

        public int getPrice(){
            return this.price;
        }
    }

    private static class Hotel {
        private final int price;

        public Hotel(int price){
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

    }


    private Hotel hotel;
    private final ArrayList<House> houses;


    public PropertySquare(String name, int number, int buy, int rent, Color color) {
        super(name, number);
        this.buyPrice = buy;
        this.rentPrice = rent;
        this.color = color;
        houses = new ArrayList<>();
        hotel = null;
        HousePrice = 50;
    }

    public int buildHouse() {
        if(this.houses.size() < 4) {
            House h= new House(HousePrice);
            this.houses.add(h);
            this.owner.decreaseCash(h.getPrice());
            System.out.println("A house has been built on " + this.getName());
            return h.getPrice();
        }
        return -1;
    }

    /**
     * check if the square has a house
     *
     * @return boolean
     */
    public boolean hasHouses() {
        System.out.println("size of houses: "+houses.size());
        return !this.houses.isEmpty();
    }

    /**
     * return the price of building a house on this property square
     */
    public int getHousePrice() {
        return this.HousePrice;
    }

    /**
     * sell a house from this property square
     */
    public void sellHouse() {
        if(hasHouses()) {
            House h = this.houses.remove(0);
            this.owner.increaseCash(h.getPrice()/2);
            System.out.println("A house has been sold from " + this.getName());
        }
    }

    /**
     * build a hotel on this property square
     */
    public int buildHotel() {
        if(this.hotel == null){
            this.hotel = new Hotel(HousePrice);
            int size = houses.size();
            for(int i=0; i<(4 - size); i++){
                this.houses.add(new House(HousePrice));
            }
            int price = getHotelPrice();
            this.owner.decreaseCash(price);
            System.out.println("A hotel has been built on " + this.getName());
            return price;
        }
        return -1;
    }

    /**
     * return the price of building a hotel on this property square
     */
    public int getHotelPrice() {
        return HousePrice*(5 - houses.size());
    }

    /**
     * check if the square has a hotel
     *
     * @return boolean
     */
    public boolean hasHotel() {
        return !(this.hotel ==null);
    }

    /**
     * sell a hotel from the square
     */
    public void sellHotel() {
        if(hasHotel()){
            this.owner.increaseCash(this.hotel.getPrice()/2);
            this.hotel = null;
            System.out.println("A hotel has been sold from " + this.getName());
        }
    }


    /**
     * gets the color of the square
     */
    public Color getColor() {
        return color;
    }

    /**
     * get the land sale price
     */
    public int getPrice() {
        return buyPrice;
    }

    /**
     * get the land rent price (square toll)
     */
    public int getRentFee() {
        return calculateRentFee();
    }

    /**
     * get the player who own this land
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * player who buy this land
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }


    /**
     * calculate the rent fee of the square
     * according to the number of squares with the same color and the buildings on the square
     */
    private int calculateRentFee() {
        if(this.owner == null){
            return this.rentPrice;
        }
        if(this.owner.hasWholeSet().contains(this)){
            if(this instanceof RailRoadSquare || this instanceof UtilitySquare){
                return this.rentPrice * this.owner.countNumber(Color.BLACK);
            }
            return (this.rentPrice + (this.rentPrice / 2) * (houses.size() + ((hasHotel()) ? 1 : 0))) * 2;
        }
        return this.rentPrice;
    }

    /**
     * action applied when player lands on this square
     */
    @Override
    public void landOn(Player p) {
        p.setCurrentLocation(this);

        int fee = getRentFee();

        Player owner = this.getOwner();

        if (p != owner && owner != null) {              // if the property has an owner and the owner is not this player

            HashMap jail = MonopolyBoard.jail.getMap();
            if (jail != null && jail.containsKey(owner)) {                  // if the owner of this property is in jail
                System.out.println("The owner is in jail, no need to pay rent fee");    // do not pay rent fee
            } else {                                                        // if the owner is not in jail

                owner.increaseCash(fee);                                    // owner gets paid by the player
                p.decreaseCash(fee);                                        // player cash decreases

                System.out.println("!!!You need to pay rent!!!");           //
                System.out.println(p.getName() + " has paid $" + fee);      // print the player paid the rent fee
                System.out.println(owner.getName() + " has received $" + fee);//
            }
        }
    }
}