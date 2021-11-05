package view;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class SquareGridGUI extends JPanel {
    Square[] square;

    public SquareGridGUI(MonopolyBoard board) {
        this.square = board.getSquares();
        this.setBackground(new Color(205, 230, 208));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(1300, 600));

        //this.makeSquares();
        this.createSquareGUI();

    }

    /** Will get deleted once GUI is set up with model
     */
    int buyPrice = 50;
    int rentPrice = 30;
    private void makeSquares() {
        int i = 0;
        square[i] = new GoSquare("GO", i, Color.BLACK, 200);
        i++;
        square[i] = new PropertySquare("Mediterranean longnamennn Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new IncomeTaxSquare("Tax", i++, 150);
        square[i] = new RailRoadSquare("Rail", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new JailSquare("Jail", i++);
        square[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new UtilitySquare("electric", i++, buyPrice, rentPrice, Color.LIGHT_GRAY);

        square[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        square[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, Color.BLACK);
        square[i] = new PropertySquare("Boardwalk", i++, buyPrice, rentPrice, Color.BLACK);

    }

    private void LoadSquareGUI(Square square, GridBagConstraints c) {
        if (square instanceof GoSquare){
            this.add(((GoSquare) square).gui, c);

        } else if (square instanceof UtilitySquare utility) {
            this.add(utility.gui, c);

        } else if (square instanceof RailRoadSquare railRoad) {
            this.add(railRoad.gui, c);

        } else if (square instanceof PropertySquare property) {
            this.add(property.gui, c);

        } else if (square instanceof IncomeTaxSquare incomeTax) {
            this.add(incomeTax.gui, c);

        } else if (square instanceof GoToJailSquare) {
            this.add(((GoToJailSquare) square).gui, c);

        } else if (square instanceof JailSquare) {
            this.add(((JailSquare) square).gui, c);

        } else if (square instanceof FreeParkingSquare) {
            this.add(((FreeParkingSquare) square).gui, c);
        }
    }

    private void createSquareGUI() {

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.insets = new Insets(1, 0, 1, 0);  //top padding

        NewMethod(c);
        //originalMethod(c);
    }

    public void NewMethod(GridBagConstraints c){
        for(int j=0; j < 4; j++){
            for(int i=0; i < 10; i++){
                switch (j) {
                    case 0: {
                        c.gridx = 10 - i;
                        c.gridy = 10;
                        break;
                    }
                    case 1: {
                        c.gridx = 0;
                        c.gridy = 10 - i;
                        break;
                    }
                    case 2: {
                        c.gridx = i;
                        c.gridy = 0;
                        break;
                    }
                    case 3: {
                        c.gridx = 10;
                        c.gridy = i;
                    }
                }
                //this.add(square[j*10 + i].gui, c);
                LoadSquareGUI(square[j*10 + i], c);
            }
        }
    }

    public void originalMethod(GridBagConstraints c) {

        // 0th Square : Starting Point
        c.gridx = 8;
        c.gridy = 7;
        GoSquare goSquare = (GoSquare) square[0];
        GoSquareGUI goSquareGUI = new GoSquareGUI();

        this.add(goSquareGUI, c);

        // 1st Square
        c.gridx = 7;
        c.gridy = 7;
        PropertySquare property1 = (PropertySquare) square[1];
        PropertySquareGUI propertyGUI1 = new PropertySquareGUI(property1.getColor(), property1.getName(), String.valueOf(property1.getPrice()));

        this.add(propertyGUI1, c);

        //2nd Square
        c.gridx = 6;
        c.gridy = 7;
        PropertySquare property2 = (PropertySquare) square[2];
        PropertySquareGUI propertyGUI2 = new PropertySquareGUI(property2.getColor(), property2.getName(), String.valueOf(property2.getPrice()));

        this.add(propertyGUI2, c);

        //3rd Square
        IncomeTaxSquare incomeTax1 = (IncomeTaxSquare) square[3];
        IncomeTaxSquareGUI incomeTaxGUI1 = new IncomeTaxSquareGUI(incomeTax1.getTax());
        c.gridx = 5;
        c.gridy = 7;
        this.add(incomeTaxGUI1, c);

        //4th Square
        RailRoadSquare railRoad1 = (RailRoadSquare) square[4];
        RailRoadSquareGUI railRoadGUI1 = new RailRoadSquareGUI(railRoad1.getPrice());
        c.gridx = 4;
        c.gridy = 7;
        this.add(railRoadGUI1, c);

        //5th Square
        c.gridx = 3;
        c.gridy = 7;
        PropertySquare property3 = (PropertySquare) square[5];
        PropertySquareGUI propertyGUI3 = new PropertySquareGUI(property3.getColor(), property3.getName(), String.valueOf(property3.getPrice()));

        this.add(propertyGUI3, c);

        //6th Square
        c.gridx = 2;
        c.gridy = 7;
        PropertySquare property4 = (PropertySquare) square[6];
        PropertySquareGUI propertyGUI4 = new PropertySquareGUI(property4.getColor(), property4.getName(), String.valueOf(property4.getPrice()));

        this.add(propertyGUI4, c);

        //7th Square
        c.gridx = 1;
        c.gridy = 7;
        PropertySquare property5 = (PropertySquare) square[7];
        PropertySquareGUI propertyGUI5 = new PropertySquareGUI(property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI5, c);

        //8th Square
        c.gridx = 0;
        c.gridy = 7;
        JailSquare jail = (JailSquare) square[8];
        JailSquareGUI jailGUI = new JailSquareGUI();

        this.add(jailGUI, c);

        //9th Square
        c.gridy = 6;
        PropertySquare property6 = (PropertySquare) square[9];
        PropertySquareGUI propertyGUI6 = new PropertySquareGUI(property6.getColor(), property6.getName(), String.valueOf(property6.getPrice()));

        this.add(propertyGUI6, c);

        //10th Square
        c.gridy = 5;
        UtilitySquare utility1 = (UtilitySquare) square[10];
        UtilitySquareGUI utilityGUI1 = new UtilitySquareGUI(300, "Water Works");

        this.add(utilityGUI1, c);

        //11th Square
        c.gridy = 4;
        PropertySquare property7 = (PropertySquare) square[11];
        PropertySquareGUI propertyGUI7 = new PropertySquareGUI(property7.getColor(), property7.getName(), String.valueOf(property7.getPrice()));

        this.add(propertyGUI7, c);

        //12th Square
        c.gridy = 3;
        PropertySquare property8 = (PropertySquare) square[12];
        PropertySquareGUI propertyGUI8 = new PropertySquareGUI(property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI8, c);

        //13th Square
        c.gridy = 2;
        PropertySquare property9 = (PropertySquare) square[12];
        PropertySquareGUI propertyGUI9 = new PropertySquareGUI(property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI9, c);

        //13th Square
        c.gridx = 8;
        c.gridy = 2;

        //F property9 = (PropertySquare) squareList[12];
        FreeParkingSquareGUI freeParking = new FreeParkingSquareGUI();

        this.add(freeParking, c);

        //F property9 = (PropertySquare) squareList[12];
        GoToJailGUI goToJail = new GoToJailGUI();
        c.gridy = 3;
        this.add(goToJail, c);
    }

}

