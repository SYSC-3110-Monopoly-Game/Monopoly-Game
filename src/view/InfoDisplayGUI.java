package view;

import Controller.MonopolyGameController;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class InfoDisplayGUI extends JPanel {

    private final JLabel name, cash, propertyList, currentLocation, buyPrice, rentPrice, housePrice, hotelPrice;
    private final JButton buy, sell, rollDice, nextTurn;

    /**
     * Initialize JPanel which shows player information and buttons
     */
    public InfoDisplayGUI(Player playerInTurn) {
        this.setBackground(Color.LIGHT_GRAY);
        //this.add(new JLabel("Welcome to Monopoly Game!"));
        this.setPreferredSize(new Dimension(360, 670));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // player name, cash, property list
        JPanel playerInfo = new JPanel(new GridLayout(3, 2));
        playerInfo.setBorder(BorderFactory.createLineBorder(Color.black, 3));

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
        housePrice.setText("House Price: 20"); // will be changed in milestone
        hotelPrice.setText("Hotel Price: 30"); // will be changed in milestone

        currentProperty.add(currentLocation);
        currentProperty.add(buyPrice);
        currentProperty.add(rentPrice);
        currentProperty.add(housePrice);
        currentProperty.add(hotelPrice);


    //buttons: buy, sell, roll dice next turn
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        buttons.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        buy = new JButton("Buy");
        sell = new JButton("Sell");
        rollDice = new JButton("Roll Dice");
        nextTurn = new JButton("Next Turn");


        buy.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        sell.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        rollDice.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        nextTurn.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        buttons.add(buy);
        buttons.add(sell);
        buttons.add(rollDice);
        buttons.add(nextTurn);


        this.add(playerInfo);
        this.add(currentProperty);
        this.add(buttons);

        this.setBuyEnabled(false);
        this.setSellEnabled(false);
        this.setNextEnabled(false);
    }

    /**
     * add action listener to the 4 buttons
     */
    public void setButtonControllers(MonopolyGameController controller) {
        this.buy.addActionListener(controller);
        sell.addActionListener(controller);
        rollDice.addActionListener(controller);
        nextTurn.addActionListener(controller);
    }

    /**
     * show the name of the player who is currently playing
     */
    public void setName(String name) {
        this.name.setText("Player Name: " + name);
        this.name.repaint();
    }

    /**
     * show the cash of the player who is currently playing
     */
    public void setCash(int cash) {
        this.cash.setText("Cash: " + cash);
        this.cash.repaint();
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
        this.housePrice.setText("House Price: " + housePrice);
        this.housePrice.repaint();
    }

    /**
     * show the price to build a hotel on current location which the player who is currently playing lands on
     */
    public void setHotelPrice(JLabel hotelPrice) {
        this.hotelPrice.setText("Hotel Price: " + hotelPrice);
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
}
