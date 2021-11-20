package view;

import Controller.MonopolyGameController;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoDisplayGUI extends JPanel {

    private final JLabel name, name1, name2, name3, cash, cash1, cash2, cash3, propertyList, currentLocation, buyPrice, rentPrice, housePrice, hotelPrice;
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

        //information about one player (player not in turn)
        JPanel line2 = new JPanel(new GridLayout(1,2));
        name1 = new JLabel();
        cash1 = new JLabel();
        name1.setText(players.get(0).getName());
        cash1.setText(String.valueOf(players.get(0).getCash()));
        line2.add(name1);
        line2.add(cash1);

        //information about one player (player not in turn)
        JPanel line3 = new JPanel(new GridLayout(1,2));
        name2 = new JLabel();
        cash2 = new JLabel();
        name2.setText(players.get(1).getName());
        cash2.setText(String.valueOf(players.get(1).getCash()));
        line3.add(name2);
        line3.add(cash2);

        //information about one player (player not in turn)
        JPanel line4 = new JPanel(new GridLayout(1,2));
        name3 = new JLabel();
        cash3 = new JLabel();
        name3.setText(players.get(2).getName());
        cash3.setText(String.valueOf(players.get(2).getCash()));
        line4.add(name3);
        line4.add(cash3);

        otherPlayer.add(line1);
        otherPlayer.add(line2);
        otherPlayer.add(line3);
        otherPlayer.add(line4);

        // total
        //JPanel otherPlayers = new JPanel(new GridLayout(1, 2));
        JPanel otherPlayers = new JPanel(new GridLayout(2, 1));
        playerInfo.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        otherPlayers.add(otherPlayer);
        otherPlayers.add(playerInfo);

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
     * show the name1 of the player who is not currently playing
     */
    public void setName1(String name) {
        this.name1.setText(name);
        this.name1.repaint();
    }

    /**
     * show the name2 of the player who is not currently playing
     */
    public void setName2(String name) {
        this.name2.setText(name);
        this.name2.repaint();
    }
    /**
     * show the name1 of the player who is not currently playing
     */
    public void setName3(String name) {
        this.name3.setText(name);
        this.name3.repaint();
    }

    /**
     * show the cash of the player who is currently playing
     */
    public void setCash(int cash) {
        this.cash.setText("Cash: " + cash);
        this.cash.repaint();
    }

    /**
     * show the cash of the player1 who is not currently playing
     */
    public void setCash1(int cash) {
        this.cash1.setText(String.valueOf(cash));
        this.cash1.repaint();
    }

    /**
     * show the cash of the player2 who is not currently playing
     */
    public void setCash2(int cash) {
        this.cash2.setText(String.valueOf(cash));
        this.cash2.repaint();
    }

    /**
     * show the cash of the player1 who is not currently playing
     */
    public void setCash3(int cash) {
        this.cash3.setText(String.valueOf(cash));
        this.cash3.repaint();
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
