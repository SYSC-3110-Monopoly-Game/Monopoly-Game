package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GoSquareGUI extends JPanel {
    public GoSquareGUI(int gridx, int gridy, Color color) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(100, 110));
        BufferedImage img = null;
        Image resizedImage = null;
        try {

            img = ImageIO.read(new File("src/images/goSquare.png"));
            resizedImage = img.getScaledInstance(100, 80, java.awt.Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
        add(picLabel);


    }

    public void addPlayer(String name){
        JLabel player = new JLabel(name);
        add(player);

    }
}
