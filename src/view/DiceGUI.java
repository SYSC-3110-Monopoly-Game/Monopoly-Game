package view;

import Model.Dice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceGUI extends JPanel {

    private Dice d = new Dice();

    public DiceGUI() {
        JPanel image = new JPanel(new GridLayout(1,2));
        JPanel full = new JPanel(new BorderLayout());

        image.setPreferredSize(new Dimension(200,100));
        full.setPreferredSize(new Dimension(200,150));

        JLabel totalDiceValue = new JLabel();
        totalDiceValue.setText("Total Dice Value: " + d.getTotalValue());


        full.add(image, BorderLayout.CENTER);
        full.add(totalDiceValue, BorderLayout.PAGE_END);
    }

}
