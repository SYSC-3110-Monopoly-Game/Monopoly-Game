package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SimpleSquareGUI{
    JPanel building1, building2, building3, building4;

    public PropertySquareGUI(Color color, String name, String buyPrice) {
        super("src/images/empty.png", color);

        //square name display + building
        //name part
        JLabel squareName = new JLabel("<html>" + name + "</html>");
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
}
