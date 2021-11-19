package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SquareGUI{

    private final JPanel colorTag;
    JPanel building1, building2, building3, building4;

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

        //square name display + building
        //name part
        JLabel squareName = new JLabel("<html>" + name + "</html>");
        squareName.setMaximumSize(new Dimension(DiceGUI.SIZE,27));
        squareName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        //building part
        JPanel buildings = new JPanel(new GridLayout(1,4));
        //building1
        building1 = new JPanel(new GridLayout(1,1));
        building1.setSize(25,25);
        building1.setBackground(Color.WHITE);
        //building2
        building2 = new JPanel(new GridLayout(1,1));
        building2.setSize(25,25);
        building2.setBackground(Color.WHITE);
        //building3
        building3 = new JPanel(new GridLayout(1,1));
        building3.setSize(25,25);
        building3.setBackground(Color.WHITE);
        //building4
        building4 = new JPanel(new GridLayout(1,1));
        building4.setSize(25,25);
        building4.setBackground(Color.WHITE);
        //buildings
        buildings.add(building1);
        buildings.add(building2);
        buildings.add(building3);
        buildings.add(building4);
        //name + building
        JPanel total = new JPanel(new GridLayout(2,1));
        total.add(squareName);
        total.add(buildings);
        this.add(total, BorderLayout.CENTER);

        //square price display
        JLabel squarePrice = new JLabel(buyPrice);
        this.add(squarePrice, BorderLayout.PAGE_END);
    }

    //build first building
    public void setBuilding1 (Color buildingType){
        this.building1.setBackground(buildingType);
        this.building1.repaint();
    }

    //build second building
    public void setBuilding2 (Color buildingType){
        this.building2.setBackground(buildingType);
        this.building2.repaint();
    }

    //build third building
    public void setBuilding3 (Color buildingType){
        this.building3.setBackground(buildingType);
        this.building3.repaint();
    }

    //build fourth building
    public void setBuilding4 (Color buildingType){
        this.building4.setBackground(buildingType);
        this.building4.repaint();
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
