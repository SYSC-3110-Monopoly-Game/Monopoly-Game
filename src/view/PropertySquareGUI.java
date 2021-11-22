package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SimpleSquareGUI{
    public static final int SIZE = 90;

    JPanel building1, building2, building3, building4, building5;

    public PropertySquareGUI(Color color, String name, String buyPrice) {
        super("src/images/empty.png", color);

        //square name display + building
        //name part
        JLabel squareName = new JLabel("<html>" + name + "</html>");
        squareName.setMaximumSize(new Dimension(SIZE,27));
        squareName.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        //building part
        JPanel buildings = new JPanel(new GridLayout(1,5));
        //building1
        building1 = new JPanel(new GridLayout(1,1));
        building1.setBackground(Color.WHITE);
        //building2
        building2 = new JPanel(new GridLayout(1,1));
        building2.setBackground(Color.WHITE);
        //building3
        building3 = new JPanel(new GridLayout(1,1));
        building3.setBackground(Color.WHITE);
        //building4
        building4 = new JPanel(new GridLayout(1,1));
        building4.setBackground(Color.WHITE);
        //building5
        building5 = new JPanel(new GridLayout(1,1));
        building5.setBackground(Color.WHITE);
        //buildings
        buildings.add(building1);
        buildings.add(building2);
        buildings.add(building3);
        buildings.add(building4);
        buildings.add(building5);
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
        if (buildingNumber == 1){
            if (building1.getBackground() == Color.white){
                return true;
            }
        }else if (buildingNumber == 2){
            if (building2.getBackground() == Color.white){
                return true;
            }
        }else if(buildingNumber == 3){
            if (building3.getBackground() == Color.white){
                return true;
            }
        }else if(buildingNumber == 4){
            if (building4.getBackground() == Color.white){
                return true;
            }
        }else if(buildingNumber == 5){
            if (building5.getBackground() == Color.white){
                return true;
            }
        }
        return false;
    }


    //build No. x building
    public void setBuildingX (Color buildingType, int buildingNumber){
        if (buildingNumber == 1){
            this.building1.setBackground(buildingType);
            this.building1.repaint();
        }else if (buildingNumber == 2){
            this.building2.setBackground(buildingType);
            this.building2.repaint();
        }else if(buildingNumber == 3){
            this.building3.setBackground(buildingType);
            this.building3.repaint();
        }else if(buildingNumber == 4){
            this.building4.setBackground(buildingType);
            this.building4.repaint();
        }else if(buildingNumber == 5){
            this.building5.setBackground(buildingType);
            this.building5.repaint();
        }
    }
}
