package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RailRoadSquareGUI extends SquareGUI {
    private final JPanel colorTag;

    public RailRoadSquareGUI(String name, int buyPrice) {
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(DiceGUI.SIZE, DiceGUI.SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));

        colorTag = new JPanel();
        colorTag.setPreferredSize(new Dimension(DiceGUI.SIZE, 23));
        colorTag.setBackground(Color.BLACK);
        this.add(colorTag);


        JLabel label = new JLabel(name);
        label.setBackground(new Color(0, 0, 0, 60));
        label.setForeground(Color.white);
        label.setOpaque(true);
        this.add(label);

        //square price display
        JLabel squarePrice = new JLabel(String.valueOf(buyPrice));
        this.add(squarePrice );
    }

    /**
     * in gui add player to the square
     */
    public void addPlayer(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.colorTag.add(player);
        this.revalidate();
        this.repaint();
    }

    /**
     * in gui remove player from the square
     */
    public void removePlayer(String name, boolean inJail) {
        Component[] components = this.colorTag.getComponents();
        for (Component label : components
        ) {
            JLabel player = (JLabel) label;
            if (player.getText().equals(name)) {
                player.setVisible(false);
                this.colorTag.remove(label);
                this.colorTag.repaint();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage img;
        Image resizedImage = null;
        try {

            img = ImageIO.read(new File("src/images/railroadSquare.png"));
            resizedImage = img.getScaledInstance(DiceGUI.SIZE, DiceGUI.SIZE, java.awt.Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
