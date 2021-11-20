package view;

import javax.swing.*;
import java.awt.*;

public class RailRoadSquareGUI extends SimpleSquareGUI {

    public RailRoadSquareGUI(String name, int buyPrice) {
        super("src/images/railroadSquare.png", Color.BLACK);
        setLayout(new GridLayout(3,1));

        JLabel label = new JLabel(name);
        label.setBackground(new Color(0, 0, 0, 60));
        label.setForeground(Color.white);
        label.setOpaque(true);
        this.add(label);

        //square price display
        JLabel squarePrice = new JLabel(String.valueOf(buyPrice));
        this.add(squarePrice );
    }

}
