package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SimpleSquareGUI{
    public static final int SIZE = 90;

    JPanel[] building = new JPanel[5];

    public PropertySquareGUI(Color color, String name, String buyPrice) {
        super("src/images/empty.png", color);

        //square name display + building
        //name part
        JLabel squareName = new JLabel("<html>" + name + "</html>");
        squareName.setMaximumSize(new Dimension(SIZE,27));
        squareName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        //building part
        JPanel buildings = new JPanel(new GridLayout(1,5));
        //
        for(JPanel b: building){
            b = new JPanel(new GridLayout(1,1));
            b.setBackground(Color.WHITE);
            buildings.add(b);
        }

        //name + building
        JPanel total = new JPanel(new GridLayout(2,1));
        total.add(squareName);
        total.add(buildings);
        this.add(total, BorderLayout.CENTER);

        //square price display
        JLabel squarePrice = new JLabel(buyPrice);
        this.add(squarePrice, BorderLayout.PAGE_END);
    }

    /**
     * check if the square already had No.x building
     */
    public boolean isBuilding(int buildingNumber){
        if (buildingNumber > 0 && buildingNumber < 6){
            if (building[buildingNumber-1].getBackground() == Color.white){
                return true;
            }
        }
        return false;
    }


    /**
     * build or remove No.x building on the square
     */
    public void setBuildingX (Color buildingType, int buildingNumber){
        if (buildingNumber > 0 && buildingNumber < 6) {
            this.building[buildingNumber-1].setBackground(buildingType);
            this.building[buildingNumber-1].repaint();
        }
    }
}
