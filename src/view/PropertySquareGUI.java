package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SimpleSquareGUI{


    public PropertySquareGUI(Color color, String name, String buyPrice) {
        super("", color);

        //square name display
        JLabel squareName = new JLabel("<html>" + name + "</html>");
        squareName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        this.add(squareName, BorderLayout.CENTER);

        //square price display
        JLabel squarePrice = new JLabel(buyPrice);
        this.add(squarePrice, BorderLayout.PAGE_END);
    }
}
