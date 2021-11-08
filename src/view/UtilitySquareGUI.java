package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilitySquareGUI extends SquareGUI {

    private String type;
    private JPanel colorTag;

    public UtilitySquareGUI(int buyPrice, String type) {
        this.type = type;
        setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));

        setPreferredSize(new Dimension(100, 100));

        colorTag = new JPanel();
        colorTag.setPreferredSize(new Dimension(100, 25));
        colorTag.setBackground(Color.BLACK);
        this.add(colorTag, BorderLayout.PAGE_START);

        //square price display
        JLabel squarePrice = new JLabel(String.valueOf(buyPrice));
        this.add(squarePrice, BorderLayout.AFTER_LAST_LINE);
    }

    public void addPlayer(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.colorTag.add(player);
    }

    public void removePlayer(String name, boolean inJail) {
        Component components[] = this.colorTag.getComponents();
        for (Component label : components
        ) {
            JLabel player = (JLabel) label;
            if (player.getText().equals(name)) {
                player.setVisible(false);
                this.colorTag.remove(label);
                this.revalidate();
                this.repaint();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage img = null;
        Image resizedImage = null;
        try {

            if (type.equals("Water Works")) {
                img = ImageIO.read(new File("src/images/waterSquare.png"));
            } else {
                img = ImageIO.read(new File("src/images/electricSquare.png"));
            }
            resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
