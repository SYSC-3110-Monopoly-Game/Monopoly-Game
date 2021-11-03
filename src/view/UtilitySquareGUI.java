package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilitySquareGUI extends JPanel {
    public UtilitySquareGUI(int gridx, int gridy, String type) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(100, 100));
        BufferedImage img = null;
        Image resizedImage = null;
        try {

            if (type.equals("water")) {
                img = ImageIO.read(new File("src/images/waterSquare.png"));
            }else{
                img = ImageIO.read(new File("src/images/electricSquare.png"));
            }
            resizedImage = img.getScaledInstance(100, 80, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
        add(picLabel);


    }

    public void addPlayer(String name) {
        JLabel player = new JLabel(name);
        add(player);

    }
}
