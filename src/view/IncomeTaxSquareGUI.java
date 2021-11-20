package view;

import javax.swing.*;
import java.awt.*;

public class IncomeTaxSquareGUI extends SimpleSquareGUI {
    public IncomeTaxSquareGUI(int tax) {
        super("src/images/incomeTaxSquare.png", Color.BLACK);
        JLabel taxVal = new JLabel(""+tax);
        add(taxVal, BorderLayout.PAGE_END);

    }
}
