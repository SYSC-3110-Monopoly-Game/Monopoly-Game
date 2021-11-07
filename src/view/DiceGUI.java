package view;

import javax.swing.*;
import java.awt.*;

public class DiceGUI extends JPanel {

    public DiceGUI() {
        JPanel image = new JPanel(new GridLayout(1,2));
        JPanel full = new JPanel(new BorderLayout());

        image.setPreferredSize(new Dimension(200,100));
        full.setPreferredSize(new Dimension(200,150));

        JLabel totalDiceValue = new JLabel();


        full.add(image, BorderLayout.CENTER);
        full.add(totalDiceValue, BorderLayout.PAGE_END);
    }

}
