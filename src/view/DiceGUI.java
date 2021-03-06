package view;

import javax.swing.*;
import java.awt.*;


public class DiceGUI extends JPanel {


    /**
     * initialize the dice panel
     */
    public DiceGUI() {
        this.setPreferredSize(new Dimension(150, 80));
        this.setLayout(new BorderLayout());
    }

    /**
     * set dice image according to dice values
     */
    public void setDiceImages(int dice1Value, int dice2Value) {
        String dice1Path = ".\\src\\images\\Dice" + dice1Value + ".png";
        String dice2Path = ".\\src\\images\\Dice" + dice2Value + ".png";

        ImageIcon dice1Image = new ImageIcon(dice1Path);
        ImageIcon dice2Image = new ImageIcon(dice2Path);

        this.removeAll();
        JLabel dice1 = new JLabel(dice1Image);
        this.add(dice1, BorderLayout.WEST);
        JLabel dice2 = new JLabel(dice2Image);
        this.add(dice2, BorderLayout.EAST);
        this.revalidate();
        this.repaint();
    }

}
