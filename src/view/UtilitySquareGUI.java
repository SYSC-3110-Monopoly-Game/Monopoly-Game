package view;

import javax.swing.*;
import java.awt.*;

public class UtilitySquareGUI extends SimpleSquareGUI {

    public UtilitySquareGUI(int buyPrice, String type) {
        super("src/images/" + type + "Square.png", Color.BLACK);

        //square price display
        JLabel squarePrice = new JLabel(String.valueOf(buyPrice));
        this.add(squarePrice, BorderLayout.AFTER_LAST_LINE);
    }
}
