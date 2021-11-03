package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PropertySquareGUI extends JPanel {
    private int row, column;
    private JLabel playerNum;


    public PropertySquareGUI(int column, int row, Color color, String name, String buyPrice) {
        this.column = column;
        this.row = row;

        Border blackline = BorderFactory.createLineBorder(Color.black,2);
        this.setBorder(blackline);

        //layout of main square panel
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(100, 100));

        //square color tag
        JPanel colorTag = new JPanel();
        colorTag.setBackground(color);
        this.add(colorTag);

        //adding space filler for better look
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        //square name display
        JLabel squareName = new JLabel("<html>"+ name +"</html>");
        squareName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        this.add(squareName);

        //adding space filler for better look
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        //player number display
        playerNum = new JLabel("", SwingConstants.CENTER);
        this.add(playerNum);

        //adding space filler for better look
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        //square price display
        JLabel squarePrice = new JLabel(buyPrice, SwingConstants.CENTER);
        this.add(squarePrice);


    }

    /**
     * Returns player number of the player currently on the square
     */
    public String getPlayer() {
        return this.playerNum.getText();
    }

    /**
     * Returns Row number of square
     */
    public void setPlayer(String player) {
        this.playerNum.setText(player);
    }

    /**
     * Returns Row number of square
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets Column number of square
     */
    public int getColumn() {
        return this.column;
    }
}
