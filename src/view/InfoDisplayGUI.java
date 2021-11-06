package view;

import Model.Player;

import javax.swing.*;
import java.awt.*;

public class InfoDisplayGUI extends JPanel {

    private Player p;

    public InfoDisplayGUI() {
        this.setBackground(Color.LIGHT_GRAY);
        this.add(new JLabel("Welcome to Tutorials Point"));
        this.setPreferredSize(new Dimension(400, 600));

        /**
        //player name, cash, property list
        JPanel playerInfo = new JPanel(new GridLayout(3,1));
        playerInfo.setPreferredSize(new Dimension(350,100));
        playerInfo.setBorder(BorderFactory.createLineBorder(Color.black,3));

        JLabel name = new JLabel();
        JLabel cash = new JLabel();
        JLabel propertyList = new JLabel();

        name.setText("Player Name: " + p.getName());
        cash.setText("Cash: " + p.getCash());
        propertyList.setText("Property List: " + p.getProperties());

        playerInfo.add(name);
        playerInfo.add(cash);
        playerInfo.add(playerInfo);


        this.add(playerInfo);
        ***/


    }
}
