package view;

import javax.swing.*;
import java.awt.*;

public class PropertySquareGUI extends SquareGUI{

    private final JPanel colorTag;
    JPanel building1, building2, building3, building4, building5;

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
            if (building1.getBackground() == Color.white){
                this.building1.setBackground(buildingType);
                this.building1.repaint();
            }
        }else if (buildingNumber == 2){
            if (building2.getBackground() == Color.white){
                this.building2.setBackground(buildingType);
                this.building2.repaint();
            }
        }else if(buildingNumber == 3){
            if (building3.getBackground() == Color.white){
                this.building3.setBackground(buildingType);
                this.building3.repaint();
            }
        }else if(buildingNumber == 4){
            if (building4.getBackground() == Color.white){
                this.building4.setBackground(buildingType);
                this.building4.repaint();
            }
        }else if(buildingNumber == 5){
            if (building5.getBackground() == Color.white){
                this.building5.setBackground(buildingType);
                this.building5.repaint();
            }
        }
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
