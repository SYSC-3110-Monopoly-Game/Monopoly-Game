package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SquareGUI{

    private final JPanel colorTag;

    public PropertySquareGUI(Color color, String name, String buyPrice) {
        //layout of main  panel
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(DiceGUI.SIZE, DiceGUI.SIZE));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        // color tag panel
        colorTag = new JPanel();
        colorTag.setPreferredSize(new Dimension(DiceGUI.SIZE, 23)); // 100, 25
        colorTag.setBackground(color);
        this.add(colorTag, BorderLayout.PAGE_START);

        //square name display
        JLabel squareName = new JLabel("<html>" + name + "</html>");
        squareName.setMaximumSize(new Dimension(DiceGUI.SIZE,27));
        squareName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        this.add(squareName, BorderLayout.CENTER);

        //square price display
        JLabel squarePrice = new JLabel(buyPrice);
        this.add(squarePrice, BorderLayout.PAGE_END);
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
                this.revalidate();
                this.colorTag.repaint();
            }
        }
    }
}
