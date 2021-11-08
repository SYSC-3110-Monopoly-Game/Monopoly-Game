package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceGUI2 extends JPanel{

    public DiceGUI2() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.black,3));


    }

    @Override
    protected void paintComponent(Graphics g) {

        BufferedImage img;
        Image resizedImage = null;
        try {

            img = ImageIO.read(new File("src/images/Dice1.png"));
            resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
