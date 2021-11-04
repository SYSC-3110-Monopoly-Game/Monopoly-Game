package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JailSquareGUI extends JPanel {
    private final JPanel colorTag;
    private final JPanel jailTag;

    public JailSquareGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.black,2));

        colorTag = new JPanel();
        colorTag.setPreferredSize(new Dimension(100, 25));
        colorTag.setBackground(Color.BLACK);
        this.add(colorTag, BorderLayout.PAGE_START);

        jailTag = new JPanel();
        jailTag.setPreferredSize(new Dimension(40, 25));
        jailTag.setBackground(Color.RED);
        this.add(jailTag, BorderLayout.PAGE_END);
    }

    public void addPassingPlayer(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.colorTag.add(player);
    }
    public void addPlayerToJail(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.jailTag.add(player);
    }

    public void removePlayer(String name, boolean inJail) {
        Component[] components = new Component[0];
        if(inJail){
            components = this.jailTag.getComponents();
        }
        else{
            components = this.colorTag.getComponents();
        }

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

        BufferedImage img;
        Image resizedImage = null;
        try {

            img = ImageIO.read(new File("src/images/jailSquare.png"));
            resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
