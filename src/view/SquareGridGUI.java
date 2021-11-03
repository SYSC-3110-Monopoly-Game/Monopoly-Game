package view;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class SquareGridGUI extends JPanel {
    Square squareList[];
    int buyPrice = 50;
    int rentPrice =30;

    public SquareGridGUI(Square squareList[]) {
        this.squareList = squareList;
        this.setBackground(new Color(205, 230, 208));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(1000, 800));

        this.makeSquares();
        this.createButtons();

    }
    /**
     * Populates the board with Squares.
     */
    private void makeSquares() {
        int i = 0;
        squareList[i] = new GoSquare("GO", i++, Color.BLACK, 200);

        squareList[i] = new PropertySquare("Mediterranean Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new IncomeTaxSquare("Tax",i++ , 150, Color.BLACK);
        squareList[i] = new RailRoadSquare("Rail", i++,buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new JailSquare("Jail", i++, Color.BLACK);
        squareList[i] = new PropertySquare("St. Charles Place", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new UtilitySquare("electric", i++, buyPrice, rentPrice, Color.LIGHT_GRAY);

        squareList[i] = new PropertySquare("States Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Virginia Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("St. James Place", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Tennessee Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("New York Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Kentucky Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Indiana Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Illinois Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Atlantic Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Ventnor Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Marvin Gardens", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Pacific Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("North Carolina Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Boardwalk", i++, buyPrice, rentPrice, Color.BLACK);

    }

    private void createButtons() {

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.insets = new Insets(1,1,1,0);  //top padding


        // 0th Square : Starting Point
        c.gridx = 8;
        c.gridy = 7;
        GoSquare goSquare = (GoSquare) squareList[0];
        GoSquareGUI goSquareGUI = new GoSquareGUI(c.gridx, c.gridy, goSquare.getColor());

        this.add(goSquareGUI, c);

        // 1st Square
        c.gridx = 7;
        c.gridy = 7;
        PropertySquare property1 = (PropertySquare) squareList[1];
        PropertySquareGUI propertyGUI1 = new PropertySquareGUI(c.gridx, c.gridy, property1.getColor(), property1.getName(), String.valueOf(property1.getPrice()));

        this.add(propertyGUI1, c);

        //2nd Square
        c.gridx = 6;
        c.gridy = 7;
        PropertySquare property2 = (PropertySquare) squareList[2];
        PropertySquareGUI propertyGUI2 = new PropertySquareGUI(c.gridx, c.gridy, property2.getColor(), property2.getName(), String.valueOf(property2.getPrice()));

        this.add(propertyGUI2, c);

        //3rd Square
        IncomeTaxSquare incomeTax1 = (IncomeTaxSquare) squareList[3];
        IncomeTaxSquareGUI incomeTaxGUI1 = new IncomeTaxSquareGUI(c.gridx, c.gridy);
        c.gridx = 5;
        c.gridy = 7;
        this.add(incomeTaxGUI1, c);

        //4th Square
        RailRoadSquare railRoad1 = (RailRoadSquare) squareList[4];
        RailRoadSquareGUI railRoadGUI1 = new RailRoadSquareGUI(c.gridx, c.gridy);
        c.gridx = 4;
        c.gridy = 7;
        this.add(railRoadGUI1, c);

        //5th Square
        c.gridx = 3;
        c.gridy = 7;
        PropertySquare property3 = (PropertySquare) squareList[5];
        PropertySquareGUI propertyGUI3 = new PropertySquareGUI(c.gridx, c.gridy, property3.getColor(), property3.getName(), String.valueOf(property3.getPrice()));

        this.add(propertyGUI3, c);

        //6th Square
        c.gridx = 2;
        c.gridy = 7;
        PropertySquare property4 = (PropertySquare) squareList[6];
        PropertySquareGUI propertyGUI4 = new PropertySquareGUI(c.gridx, c.gridy, property4.getColor(), property4.getName(), String.valueOf(property4.getPrice()));

        this.add(propertyGUI4, c);

        //7th Square
        c.gridx = 1;
        c.gridy = 7;
        PropertySquare property5 = (PropertySquare) squareList[7];
        PropertySquareGUI propertyGUI5 = new PropertySquareGUI(c.gridx, c.gridy, property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI5, c);

        //8th Square
        c.gridx = 0;
        c.gridy = 7;
        JailSquare jail = (JailSquare) squareList[8];
        JailSquareGUI jailGUI = new JailSquareGUI(c.gridx, c.gridy);

        this.add(jailGUI, c);

        //9th Square
        c.gridy = 6;
        PropertySquare property6 = (PropertySquare) squareList[9];
        PropertySquareGUI propertyGUI6 = new PropertySquareGUI(c.gridx, c.gridy, property6.getColor(), property6.getName(), String.valueOf(property6.getPrice()));

        this.add(propertyGUI6, c);

        //10th Square
        c.gridy = 5;
        UtilitySquare utility1 = (UtilitySquare) squareList[10];
        UtilitySquareGUI utilityGUI1 = new UtilitySquareGUI(c.gridx, c.gridy, "electric");

        this.add(utilityGUI1, c);

        //11th Square
        c.gridy = 4;
        PropertySquare property7 = (PropertySquare) squareList[11];
        PropertySquareGUI propertyGUI7= new PropertySquareGUI(c.gridx, c.gridy, property7.getColor(), property7.getName(), String.valueOf(property7.getPrice()));

        this.add(propertyGUI7, c);

        //12th Square
        c.gridy = 3;
        PropertySquare property8 = (PropertySquare) squareList[12];
        PropertySquareGUI propertyGUI8 = new PropertySquareGUI(c.gridx, c.gridy, property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI8, c);

        //13th Square
        c.gridy = 2;
        PropertySquare property9 = (PropertySquare) squareList[12];
        PropertySquareGUI propertyGUI9 = new PropertySquareGUI(c.gridx, c.gridy, property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI9, c);

        //13th Square
        c.gridx = 8;
        c.gridy = 2;

        //F property9 = (PropertySquare) squareList[12];
        FreeParkingSquareGUI freeParking = new FreeParkingSquareGUI(c.gridx, c.gridy);

        this.add(freeParking, c);


    }

}

