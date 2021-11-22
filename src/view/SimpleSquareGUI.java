package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SimpleSquareGUI extends SquareGUI {

    private JPanel colorTag;
    private String path;
    public static final int SIZE = 90;

    /**
     * set the square on map
     */
    public SimpleSquareGUI(String path, Color color){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(SIZE, SIZE));
        this.setBorder( BorderFactory.createLineBorder(Color.black, 2));
        this.colorTag = new JPanel();
        this.path = path;
        this.colorTag.setPreferredSize(new Dimension(SIZE, 23));
        this.colorTag.setBackground(color);
        this.add(colorTag, BorderLayout.PAGE_START);
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
        Arrays.stream(components).forEach(label -> {
            JLabel player = (JLabel) label;
            if (player.getText().equals(name)) {
                player.setVisible(false);
                this.colorTag.remove(label);
                this.revalidate();
                this.repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage img;
        Image resizedImage = null;

            try {

                img = ImageIO.read(new File(path));
                resizedImage = img.getScaledInstance(SIZE, SIZE, java.awt.Image.SCALE_SMOOTH);

            } catch (IOException e) {
                e.printStackTrace();
            }
            super.paintComponent(g);
            g.drawImage(resizedImage, 0, 0, null);

    }
}
