package view;

import Controller.MonopolyGameController;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoDisplayGUI extends JPanel {

    private final JLabel name, cash, propertyList, currentLocation, buyPrice, rentPrice, housePrice, hotelPrice;
    private final JLabel[] names = new JLabel[3];
    private final JLabel[] cashes = new JLabel[3];
    private final JButton buy, sell, rollDice, nextTurn, build, sellH;

    /**
     * Initialize JPanel which shows player information and buttons
     */
    public InfoDisplayGUI(Player playerInTurn, ArrayList<Player> players) {
        this.setBackground(Color.LIGHT_GRAY);
        //this.add(new JLabel("Welcome to Monopoly Game!"));
        this.setPreferredSize(new Dimension(360, 670));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // player name, cash, property list + other players' name and cash
        //player name, cash, property list
        JPanel playerInfo = new JPanel(new GridLayout(3, 2));
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

        //other players' name and cash
        JPanel otherPlayer = new JPanel(new GridLayout(4,1));
        otherPlayer.setBorder(BorderFactory.createLineBorder(Color.black,1));

        JPanel line1 = new JPanel(new GridLayout(1,2));
        JLabel nameColumn = new JLabel("Name");
        JLabel cashColumn = new JLabel("Cash");
        line1.add(nameColumn);
        line1.add(cashColumn);
        otherPlayer.add(line1);

        //information about other players (player not in turn)
        for(int i=0; i<3; i++){
            JPanel line = new JPanel(new GridLayout(1,2));
            names[i] = new JLabel();
            cashes[i] = new JLabel();
            names[i].setText(players.get(i).getName());
            cashes[i].setText(String.valueOf(players.get(i).getCash()));
            line.add(names[i]);
            line.add(cashes[i]);
            otherPlayer.add(line);
        }

        // total
        JPanel otherPlayers = new JPanel(new GridLayout(1, 2));
        playerInfo.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        otherPlayers.add(playerInfo);
        otherPlayers.add(otherPlayer);


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
        housePrice.setText("House Price: Cannot build"); // will be changed in milestone
        hotelPrice.setText("Hotel Price: Cannot build"); // will be changed in milestone

        currentProperty.add(currentLocation);
        currentProperty.add(buyPrice);
        currentProperty.add(rentPrice);
        currentProperty.add(housePrice);
        currentProperty.add(hotelPrice);


    //buttons: buy, sell, roll dice next turn
        JPanel buttons = new JPanel(new GridLayout(3, 2));
        buttons.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        buy = new JButton("Buy");
        sell = new JButton("Sell");
        rollDice = new JButton("Roll Dice");
        nextTurn = new JButton("Next Turn");
        build = new JButton("Build On Property");
        sellH = new JButton("Sell Houses/Hotels");


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
        this.add(currentProperty);
        this.add(buttons);

        this.setBuyEnabled(false);
        this.setSellEnabled(false);
        this.setNextEnabled(false);
        this.setBuildEnabled(false);
        this.setSellHEnabled(false);
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
     * show the name of the player who is currently playing
     */
    public void setName(String name) {
        this.name.setText("Player Name: " + name);
        this.name.repaint();
    }

    /**
     * show the names of the players who are not currently playing
     */
    public void setNames(String name, int i) {
        this.names[i].setText(name);
        this.names[i].repaint();
    }

    /**
     * show the cash of the player who is currently playing
     */
    public void setCash(int cash) {
        this.cash.setText("Cash: " + cash);
        this.cash.repaint();
    }

    /**
     * show the cashes of the players who are not currently playing
     */
    public void setCashes(int cash, int i) {
        this.cashes[i].setText(String.valueOf(cash));
        this.cashes[i].repaint();
    }

    /**
     * show the properties of the player who is currently playing
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
     * show the price of current location which the player who is currently playing lands on
     */
    public void setBuyPrice(int buyPrice) {
        if(buyPrice == -1) this.buyPrice.setText("Buy Price: Non-sale" );
        else if(buyPrice == -2) this.buyPrice.setText("Buy Price: SOLD");
        else this.buyPrice.setText("Buy Price: " + buyPrice);
        this.buyPrice.repaint();
    }

    /**
     * show the rent fee of current location which the player who is currently playing lands on
     */
    public void setRentPrice(int rentPrice) {
        if(rentPrice == -1) this.rentPrice.setText("Rent Price: Non-rental" );
        else this.rentPrice.setText("Rent Price: " + rentPrice);
        this.rentPrice.repaint();
    }

    /**
     * show the price to build a house on current location which the player who is currently playing lands on
     */
    public void setHousePrice(int housePrice) {
        if(housePrice == -1) this.buyPrice.setText("House Price: Cannot build" );
        else if(housePrice == -2) this.buyPrice.setText("House Price: SOLD");
        else this.housePrice.setText("House Price: " + housePrice);
        this.housePrice.repaint();
    }

    /**
     * show the price to build a hotel on current location which the player who is currently playing lands on
     */
    public void setHotelPrice(int hotelPrice) {
        if(hotelPrice == -1) this.buyPrice.setText("Hotel Price: Cannot build" );
        else if(hotelPrice == -2) this.buyPrice.setText("Hotel Price: SOLD");
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
