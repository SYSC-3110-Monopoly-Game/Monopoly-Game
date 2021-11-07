package view;

import Controller.MonopolyGameController;
import Model.Player;
import Model.PropertySquare;

import javax.swing.*;
import java.awt.*;

public class InfoDisplayGUI extends JPanel {

    JLabel name, cash, propertyList, currentLocation, buyPrice, rentPrice, housePrice, hotelPrice;
    JButton buy, sell, rollDice, nextTurn;

    public InfoDisplayGUI(Player playerInTurn) {
        this.setBackground(Color.LIGHT_GRAY);
        //this.add(new JLabel("Welcome to Monopoly Game!"));
        this.setPreferredSize(new Dimension(360, 670));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //player name, cash, property list
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

        currentLocation.setText("Current Location: " + playerInTurn.getLocation().getName());
        String buyPriceString = "", rentPriceString ="";
        if(playerInTurn.getLocation() instanceof PropertySquare){
             buyPriceString = ((PropertySquare) playerInTurn.getLocation()).getPrice()+"";
             rentPriceString = ((PropertySquare) playerInTurn.getLocation()).getRentFee()+"";
        }
        buyPrice.setText("Buy Price: " + buyPriceString);
        rentPrice.setText("Rent Price: " + rentPriceString);
        housePrice.setText("House Price: 20" ); // will be changed in milestone
        hotelPrice.setText("Hotel Price: 30" ); // will be changed in milestone

        currentProperty.add(currentLocation);
        currentProperty.add(buyPrice);
        currentProperty.add(rentPrice);
        currentProperty.add(housePrice);
        currentProperty.add(hotelPrice);


        //buttons: buy, sell, roll dice next turn
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        //buttons.setPreferredSize(new Dimension(150, 100));
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
    }

    public void setButtonControllers(MonopolyGameController controller) {
        this.buy.addActionListener(controller);
        sell.addActionListener(controller);
        rollDice.addActionListener(controller);
        nextTurn.addActionListener(controller);
    }

    public void setName(String name) {
        this.name.setText("Player Name: " + name);
        this.name.repaint();
    }

    public void setCash(int cash) {
        this.cash.setText("Cash: " + cash);
        this.cash.repaint();
    }

    public void setPropertyList(String propertyList) {
        this.propertyList.setText("Property List: " + propertyList);
        this.propertyList.repaint();
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation.setText("Current Location: " + currentLocation);
        this.currentLocation.repaint();
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice.setText("Buy Price: " + buyPrice);
        this.buyPrice.repaint();
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice.setText("Rent Price: " + rentPrice);
        this.rentPrice.repaint();
    }

    public void setHousePrice(int housePrice) {
        this.housePrice.setText("House Price: " + housePrice);
        this.housePrice.repaint();
    }

    public void setHotelPrice(JLabel hotelPrice) {
        this.hotelPrice.setText("Hotel Price: " + hotelPrice);
        this.hotelPrice.repaint();
    }
}
