package view;

import javax.swing.*;
import java.awt.*;

public class TaxSquareGUI extends SimpleSquareGUI {
    private String type;


    public TaxSquareGUI(int tax, String type) {
        super("src/images/" + type + "Square.png", Color.BLACK);
        this.type = type;
        JLabel taxVal = new JLabel("" + tax);
        add(taxVal, BorderLayout.PAGE_END);

    }

    public String getType() {
        return type;
    }
}
