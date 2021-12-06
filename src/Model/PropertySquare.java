package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class PropertySquare extends Square {

    private final int buyPrice; //price for the player buy this land
    private final int rentPrice; //the price that other players need to pay to the owner.
    private final Color color;
    private final int HousePrice;
    private final ArrayList<House> houses;
    private Player owner = null;
    private String ownerName;
    private Hotel hotel;

    public PropertySquare(String name, int number, int buy, int rent, Color color, int housePrice, int houseAmount, int hotel, String owner) {
        super(name, number);
        this.buyPrice = buy;
        this.rentPrice = rent;
        this.color = color;
        this.HousePrice = housePrice;
        houses = new ArrayList<>();
        for (int i = 0; i < houseAmount; i++) {
            this.houses.add(new House(HousePrice));
        }
        if (hotel == 1) {
            this.hotel = new Hotel(HousePrice);
        }
        this.ownerName = owner;
    }

    public int buildHouse() {
        if (this.houses.size() < Constants.MAX_HOUSE_NUMBER) {
            House h = new House(HousePrice);
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
        return !this.houses.isEmpty();
    }

    public int getHouseNumber() {
        return this.houses.size();
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
    public int sellHouse() {
        if (hasHouses()) {
            House h = this.houses.remove(0);
            int result = h.getPrice() / 2;
            this.owner.increaseCash(result);
            System.out.println("A house has been sold from " + this.getName());
            return result;
        }
        return -1;
    }

    /**
     * build a hotel on this property square
     */
    public int buildHotel() {
        if (this.hotel == null) {
            this.hotel = new Hotel(HousePrice);
            int size = houses.size();
            int price = getHotelPrice();
            for (int i = 0; i < (Constants.MAX_HOUSE_NUMBER - size); i++) {
                this.houses.add(new House(HousePrice));
            }
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
        return HousePrice * (5 - houses.size());
    } // 5 is the number of properties that can be built ( including hotels and houses)

    /**
     * check if the square has a hotel
     *
     * @return boolean
     */
    public boolean hasHotel() {
        return !(this.hotel == null);
    }

    /**
     * sell a hotel from the square
     */
    public int sellHotel() {
        if (hasHotel()) {
            int result = this.hotel.getPrice() / 2;
            this.owner.increaseCash(result);
            this.hotel = null;
            System.out.println("A hotel has been sold from " + this.getName());
            return result;
        }
        return -1;
    }

    public void sellAll() {
        for (int i = 0; i < Constants.MAX_HOUSE_NUMBER; i++) {
            sellHouse();
        }
        sellHotel();
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
        if (this.owner == null) {
            return this.rentPrice;
        }
        if (this.owner.hasWholeSet().contains(this)) {
            if (this instanceof RailRoadSquare) {
                return this.rentPrice * this.owner.countNumber(Color.BLACK);
            }
            if (this instanceof UtilitySquare) {
                return this.rentPrice * this.owner.countNumber(Color.WHITE);
            }
            int temp = (this.rentPrice + (this.rentPrice / 2) * (houses.size() + ((hasHotel()) ? 1 : 0))) * 2;
            return temp;
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

            HashMap<Player, Integer> jail = MonopolyBoard.jail.getMap();
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

    @Override
    public String toXML() {
        StringBuilder string = new StringBuilder();
        string.append("<Square type=\"Property\">\n");
        string.append("<Name>" + this.getName() + "</Name>\n");
        string.append("<Number>" + this.getNumber() + "</Number>\n");
        string.append("<Price>" + this.getPrice() + "</Price>\n");
        string.append("<RentPrice>" + this.rentPrice + "</RentPrice>\n");
        String colorS = Integer.toString(this.getColor().getRGB());
        string.append("<Color>" + colorS + "</Color>\n");
        // call back: Color c = new Color(Integer.parseInt(colorS));
        string.append("<HousePrice>" + this.getHousePrice() + "</HousePrice>\n");
        string.append("<HouseAmount>" + this.houses.size() + "</HouseAmount>\n");
        int hotelAmount = hasHotel() ? 1 : 0;
        string.append("<HotelAmount>" + hotelAmount + "</HotelAmount>\n");
        if (this.getOwner() != null) {
            string.append("<Owner>" + this.getOwner().getName() + "</Owner>\n");
        } else {
            string.append("<Owner></Owner>\n");
        }
        string.append("</Square>\n");

        return string.toString();
    }

    public void setOwnerAccordingToOwnerName(ArrayList<Player> players) {
        for (Player p : players) {
            if (p.getName().equals(this.ownerName)) {
                this.owner = p;
            }
        }
    }

    private static class House {
        private final int price;

        public House(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }
    }

    private static class Hotel {
        private final int price;

        public Hotel(int price) {
            this.price = price;
        }

        public int getPrice() {
            return this.price;
        }

    }
}