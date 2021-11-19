package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JailSquareGUI extends SquareGUI {
    private final JPanel colorTag;
    private final JPanel jailTag;

    public JailSquareGUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(DiceGUI.SIZE, DiceGUI.SIZE));
        this.setBorder( BorderFactory.createLineBorder(Color.black, 2));
        this.colorTag = new JPanel();
        this.colorTag.setPreferredSize(new Dimension(DiceGUI.SIZE, 23));
        this.colorTag.setBackground(Color.BLACK);
        this.add(colorTag, BorderLayout.PAGE_START);
        jailTag = new JPanel();
        jailTag.setPreferredSize(new Dimension(36, 23));
        jailTag.setBackground(Color.RED);
        this.add(jailTag, BorderLayout.PAGE_END);
    }

    /**
     * in gui add player to jail
     */
    public void addPlayerToJail(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.jailTag.add(player);
        this.revalidate();
        this.repaint();
    }

    /**
     * in gui add player to the square
     */
    @Override
    public void addPlayer(String name) {
        PlayerGUI player = new PlayerGUI(name);
        this.colorTag.add(player);
        this.revalidate();
        this.repaint();
    }

    /**
     * in gui remove player from the square
     */
    @Override
    public void removePlayer(String name, boolean inJail) {
        Component[] components;
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
                if (inJail) {
                    this.jailTag.remove(label);
                } else {
                    this.colorTag.remove(label);
                }
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
            resizedImage = img.getScaledInstance(DiceGUI.SIZE, DiceGUI.SIZE, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }
}
