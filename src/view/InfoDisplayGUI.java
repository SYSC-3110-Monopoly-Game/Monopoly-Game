package view;

import Controller.MonopolyGameController;
import Model.Constants;
import Model.Player;
import Model.PropertySquare;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoDisplayGUI extends JPanel {

    private final JLabel propertyList, name, cash;
    private final JLabel currentLocation;
    private final JLabel buyPrice;
    private final JLabel rentPrice;
    private final JLabel housePrice;
    private final JLabel hotelPrice;
    private final JTextArea otherPlayers;
    private final JButton buy, sell, rollDice, nextTurn, build, sellH;

    /**
     * Initialize JPanel which shows player information and buttons
     */
    public InfoDisplayGUI(Player playerInTurn, ArrayList<Player> players) {
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(360, 670));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //other players' name and cash
        otherPlayers = new JTextArea();
        otherPlayers.setMaximumSize(new Dimension(360, 80));
        otherPlayers.setBackground(Color.lightGray);
        otherPlayers.setEditable(false);
        setOtherPlayersInfo(players);
        otherPlayers.setBorder(BorderFactory.createLineBorder(Color.black, 1));


        //current player name, cash, property list
        JPanel playerInfo = new JPanel(new GridLayout(3, 1));
        playerInfo.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        name = new JLabel();
        cash = new JLabel();
        propertyList = new JLabel();

        name.setText("Player Name: " + playerInTurn.getName());
        cash.setText("Cash: " + playerInTurn.getCash());
        String properties = playerInTurn.getProperties().toString();
        propertyList.setText("Property List: " + properties);


        playerInfo.add(name);
        playerInfo.add(cash);
        playerInfo.add(propertyList);

        //current property (property name, buy price, rent price, house price, hotel price)
        JPanel currentProperty = new JPanel(new GridLayout(5, 1));
        currentProperty.setPreferredSize(new Dimension(200, 150));
        currentProperty.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        currentLocation = new JLabel();
        currentLocation.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        buyPrice = new JLabel();
        rentPrice = new JLabel();
        housePrice = new JLabel();
        hotelPrice = new JLabel();

        currentLocation.setText("Location before rolling dice: " + playerInTurn.getCurrentLocation().getName());
        buyPrice.setText("Buy Price: Non-sale");
        rentPrice.setText("Rent Price: Non-rental");
        housePrice.setText("House Price: Cannot build");
        hotelPrice.setText("Hotel Price: Cannot build");

        currentProperty.add(currentLocation);
        currentProperty.add(buyPrice);
        currentProperty.add(rentPrice);
        currentProperty.add(housePrice);
        currentProperty.add(hotelPrice);


        //buttons: buy, sell, roll dice next turn
        JPanel buttons = new JPanel(new GridLayout(3, 2));
        buttons.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        buy = new JButton(Enums.BUY.toString());
        sell = new JButton(Enums.SELL.toString());
        rollDice = new JButton(Enums.ROLL_DICE.toString());
        nextTurn = new JButton(Enums.NEXT_TURN.toString());
        build = new JButton(Enums.BUILD_ON_PROPERTY.toString());
        sellH = new JButton(Enums.SELL_HOUSES_HOTELS.toString());


        buy.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        sell.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        rollDice.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nextTurn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        build.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        sellH.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        buttons.add(buy);
        buttons.add(sell);
        buttons.add(rollDice);
        buttons.add(nextTurn);
        buttons.add(build);
        buttons.add(sellH);


        this.add(otherPlayers);
        this.add(playerInfo);
        this.add(currentProperty);
        this.add(buttons);


        this.setBuyEnabled(false);
        this.setSellEnabled(false);
        this.setNextEnabled(false);
        this.setBuildEnabled(false);
        this.setSellHEnabled(false);
    }

    /**
     * show the information of players who are not in turn
     * @param players: players not in turn
     */
    public void setOtherPlayersInfo(ArrayList<Player> players) {
        String allPlayerInfo = "Information of Other Players currently in the game \n";

        for (Player p : players
        ) {
            String info = "Player " + p.getName() + ": " + p.getCash() + "\n";
            allPlayerInfo = allPlayerInfo + info;
        }
        otherPlayers.setText(allPlayerInfo);
    }

    /**
     * set player info and buttons according to players
     * @param players: players not in turns
     * @param player: player in turn
     */
    public void setPlayersInfo(ArrayList<Player> players, Player player){
        setOtherPlayersInfo(players);
        if(player.getDiceRolled()){
            this.setRollEnabled(false);
            this.setNextEnabled(true);
        } else {
            this.setRollEnabled(true);
            this.setNextEnabled(false);
        }
        this.setName(player.getName());
        this.setCash(player.getCash());
        this.setPropertyList(player.getProperties().toString());
        this.setCurrentLocation(player.getCurrentLocation().getName());
        if (player.getCurrentLocation() instanceof PropertySquare location) {
            Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();

            if (owner == null) {
                this.setBuyEnabled(player.getCash() >= location.getPrice());
                this.setBuyPrice(location.getPrice());
            } else {
                this.setBuyPrice(Constants.PROPERTY_SOLD);
            }
            this.setHousePrice(location.getHousePrice());
            this.setHotelPrice(location.getHotelPrice());
            this.setRentPrice(location.getRentFee());
        } else {
            this.setBuyPrice(Constants.PROPERTY_NON_SALE);
            this.setHousePrice(Constants.PROPERTY_NON_SALE);
            this.setHotelPrice(Constants.PROPERTY_NON_SALE);
            this.setRentPrice(Constants.PROPERTY_NON_SALE);
            this.setBuyEnabled(false);
        }
        this.setBuildEnabled(!player.getAvailableProperties(player.removeRailroadUtility(player.hasWholeSet())).isEmpty());
        this.setSellHEnabled(!player.hasBuilding().isEmpty());
        this.setSellEnabled(!player.getProperties().isEmpty());
    }


    /**
     * add action listener to the 4 buttons
     */
    public void setButtonControllers(MonopolyGameController controller) {
        this.buy.addActionListener(controller);
        sell.addActionListener(controller);
        rollDice.addActionListener(controller);
        nextTurn.addActionListener(controller);
        build.addActionListener(controller);
        sellH.addActionListener(controller);
    }

    /**
     * show the properties of the player who is currently playing
     * @param propertyList: a string of property list
     */
    public void setPropertyList(String propertyList) {
        this.propertyList.setText("<html>" + "Property List: " + propertyList + "</html>");
        this.propertyList.repaint();
    }

    /**
     * show the current location of the player who is currently playing
     */
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation.setText("Current Location: " + currentLocation);
        this.currentLocation.repaint();
    }

    /**
     * show the cash of player in turn
     */
    public void setCash(int cash) {
        this.cash.setText("Cash: " + cash);
        this.cash.repaint();
    }

    /**
     * show the name of player in turn
     */
    public void setName(String name) {
        this.name.setText("Player Name: " + name);
        this.name.repaint();
    }

    /**
     * show the price of current location which the player who is currently playing lands on
     */
    public void setBuyPrice(int buyPrice) {
        if (buyPrice == Constants.PROPERTY_NON_SALE) this.buyPrice.setText("Buy Price: Non-sale");
        else if (buyPrice == Constants.PROPERTY_SOLD) this.buyPrice.setText("Buy Price: SOLD");
        else this.buyPrice.setText("Buy Price: " + buyPrice);
        this.buyPrice.repaint();
    }

    /**
     * show the rent fee of current location which the player who is currently playing lands on
     */
    public void setRentPrice(int rentPrice) {
        if (rentPrice == Constants.PROPERTY_NON_SALE) this.rentPrice.setText("Rent Price: Non-rental");
        else this.rentPrice.setText("Rent Price: " + rentPrice);
        this.rentPrice.repaint();
    }

    /**
     * show the price to build a house on current location which the player who is currently playing lands on
     */
    public void setHousePrice(int housePrice) {
        if (housePrice == Constants.PROPERTY_NON_SALE) this.housePrice.setText("House Price: Cannot build");
        else if (housePrice == Constants.PROPERTY_SOLD) this.housePrice.setText("House Price: SOLD");
        else this.housePrice.setText("House Price: " + housePrice);
        this.housePrice.repaint();
    }

    /**
     * show the price to build a hotel on current location which the player who is currently playing lands on
     */
    public void setHotelPrice(int hotelPrice) {
        if (hotelPrice == Constants.PROPERTY_NON_SALE) this.hotelPrice.setText("Hotel Price: Cannot build");
        else if (hotelPrice == Constants.PROPERTY_SOLD) this.hotelPrice.setText("Hotel Price: SOLD");
        else this.hotelPrice.setText("Hotel Price: " + hotelPrice);
        this.hotelPrice.repaint();
    }

    /**
     * enable or disable the Buy button
     */
    public void setBuyEnabled(boolean b) {
        this.buy.setEnabled(b);
        this.buy.repaint();
    }

    /**
     * enable or disable the Sell button
     */
    public void setSellEnabled(boolean b) {
        this.sell.setEnabled(b);
        this.sell.repaint();
    }

    /**
     * enable or disable the Next button
     */
    public void setNextEnabled(boolean b) {
        this.nextTurn.setEnabled(b);
        this.nextTurn.repaint();
    }

    /**
     * enable or disable the Roll Dice button
     */
    public void setRollEnabled(boolean b) {
        this.rollDice.setEnabled(b);
        this.rollDice.repaint();
    }

    /**
     * enable or disable the Sell on Property button
     */
    public void setBuildEnabled(boolean b) {
        this.build.setEnabled(b);
        this.build.repaint();
    }

    /**
     * enable or disable the Sell Houses/Hotels button
     */
    public void setSellHEnabled(boolean b) {
        this.sellH.setEnabled(b);
        this.sellH.repaint();
    }
}
