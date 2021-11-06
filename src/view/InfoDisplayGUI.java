package view;

import Model.Player;

import javax.swing.*;
import java.awt.*;

public class InfoDisplayGUI extends JPanel {

    //private Player p;

    public InfoDisplayGUI() {
        this.setBackground(Color.LIGHT_GRAY);
        this.add(new JLabel("Welcome to Tutorials Point"));
        this.setPreferredSize(new Dimension(400, 1000));


        //player name, cash, property list
        JPanel playerInfo = new JPanel(new GridLayout(3,1));
        playerInfo.setPreferredSize(new Dimension(350,100));
        playerInfo.setBorder(BorderFactory.createLineBorder(Color.black,3));

        JLabel name = new JLabel();
        JLabel cash = new JLabel();
        JLabel propertyList = new JLabel();

        name.setText("Player Name: ");
        cash.setText("Cash: ");
        propertyList.setText("Property List: ");

        playerInfo.add(name);
        playerInfo.add(cash);
        playerInfo.add(propertyList);


        //current property (property name, buy price, rent price, house price, hotel price)
        JPanel currentProperty = new JPanel(new GridLayout(5,1));
        currentProperty.setPreferredSize(new Dimension(200,250));
        currentProperty.setBorder(BorderFactory.createLineBorder(Color.black,3));

        JLabel propertyName = new JLabel();
        propertyName.setBorder(BorderFactory.createLineBorder(Color.black,2));
        JLabel buyPrice = new JLabel();
        JLabel rentPrice = new JLabel();
        JLabel housePrice  = new JLabel();
        JLabel hotelPrice = new JLabel();

        propertyName.setText("Current Location: " );
        buyPrice.setText("Buy Price: ");
        rentPrice.setText("Rent Price: ");
        housePrice.setText("House Price: ");
        hotelPrice.setText("Hotel Price: ");

        currentProperty.add(propertyName);
        currentProperty.add(buyPrice);
        currentProperty.add(rentPrice);
        currentProperty.add(housePrice);
        currentProperty.add(hotelPrice);


        //buttons: buy, sell, roll dice next turn
        JPanel buttons = new JPanel(new GridLayout(2,2));
        buttons.setPreferredSize(new Dimension(350,100));
        buttons.setBorder(BorderFactory.createLineBorder(Color.black,3));

        JButton buy = new JButton("Buy");
        JButton sell = new JButton("Sell");
        JButton rollDice = new JButton("Roll Dice");
        JButton nextTurn = new JButton("Next Turn");

        buy.setBorder(BorderFactory.createLineBorder(Color.black,1));
        sell.setBorder(BorderFactory.createLineBorder(Color.black,1));
        rollDice.setBorder(BorderFactory.createLineBorder(Color.black,1));
        nextTurn.setBorder(BorderFactory.createLineBorder(Color.black,1));

        buttons.add(buy);
        buttons.add(sell);
        buttons.add(rollDice);
        buttons.add(nextTurn);



        this.add(playerInfo);
        this.add(currentProperty);
        this.add(buttons);
    }
}
