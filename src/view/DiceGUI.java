package view;

import Model.Dice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceGUI extends JPanel {

    int firstDice, secondDice;

    public DiceGUI(Dice dice) {
        JPanel image = new JPanel(new GridLayout(1,2));
        JPanel full = new JPanel(new BorderLayout());

        image.setPreferredSize(new Dimension(200,100));
        full.setPreferredSize(new Dimension(200,150));

        JLabel totalDiceValue = new JLabel(String.valueOf(dice.getTotalValue()));

        firstDice = dice.getDice()[0];
        secondDice = dice.getDice()[1];

        image.add(new JLabel((Icon) createimage(firstDice)));
        image.add(new JLabel((Icon) createimage(secondDice)));

        full.add(image, BorderLayout.CENTER);
        full.add(totalDiceValue, BorderLayout.PAGE_END);
    }

    public Image createimage(int number)
    {
        BufferedImage img;
        Image resizedImage = null;
        try {

            img = ImageIO.read(new File("src/images/Dice" + number + ".png"));
            resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resizedImage;
    }

}
