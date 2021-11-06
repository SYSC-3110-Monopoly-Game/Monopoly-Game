package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FreeParkingSquareGUI extends SquareGUI {
    private final JPanel colorTag;

    public FreeParkingSquareGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));

        colorTag = new JPanel();
        colorTag.setPreferredSize(new Dimension(100, 25));
        colorTag.setBackground(Color.BLACK);
        this.add(colorTag, BorderLayout.PAGE_START);
    }

    @Override
    public void addPlayer(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.colorTag.add(player);
    }

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

            img = ImageIO.read(new File("src/images/freeParkingSquare.png"));
            resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
