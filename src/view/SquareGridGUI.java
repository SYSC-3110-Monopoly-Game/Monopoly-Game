package view;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class SquareGridGUI extends JPanel {
    Square[] squareList;

    public SquareGridGUI(Square[] squareList) {
        this.squareList = squareList;
        this.setBackground(new Color(205, 230, 208));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(1000, 600));

        this.makeSquares();
        this.createSquareGUI();

    }

    /** Will get deleted once GUI is set up with model
     */
    int buyPrice = 50;
    int rentPrice = 30;
    private void makeSquares() {
        int i = 0;
        squareList[i] = new GoSquare("GO", i, Color.BLACK, 200);
        i++;
        squareList[i] = new PropertySquare("Mediterranean longnamennn Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Baltic Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new IncomeTaxSquare("Tax", i++, 150);
        squareList[i] = new RailRoadSquare("Rail", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Oriental Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Vermont Avenue", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Connecticut Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new JailSquare("Jail", i++);
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

        //not used square
        squareList[i] = new PropertySquare("Pennsylvania Avenue", i++, buyPrice, rentPrice, Color.BLACK);

        squareList[i] = new PropertySquare("Park Place", i++, buyPrice, rentPrice, Color.BLACK);
        squareList[i] = new PropertySquare("Boardwalk", i++, buyPrice, rentPrice, Color.BLACK);

    }

    private void createSquareGUI() {

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.insets = new Insets(1, 0, 1, 0);  //top padding


        // 0th Square : Starting Point
        c.gridx = 8;
        c.gridy = 7;
        GoSquare goSquare = (GoSquare) squareList[0];
        GoSquareGUI goSquareGUI = new GoSquareGUI();

        this.add(goSquareGUI, c);

        // 1st Square
        c.gridx = 7;
        c.gridy = 7;
        PropertySquare property1 = (PropertySquare) squareList[1];
        PropertySquareGUI propertyGUI1 = new PropertySquareGUI(property1.getColor(), property1.getName(), String.valueOf(property1.getPrice()));

        this.add(propertyGUI1, c);

        //2nd Square
        c.gridx = 6;
        c.gridy = 7;
        PropertySquare property2 = (PropertySquare) squareList[2];
        PropertySquareGUI propertyGUI2 = new PropertySquareGUI(property2.getColor(), property2.getName(), String.valueOf(property2.getPrice()));

        this.add(propertyGUI2, c);

        //3rd Square
        IncomeTaxSquare incomeTax1 = (IncomeTaxSquare) squareList[3];
        IncomeTaxSquareGUI incomeTaxGUI1 = new IncomeTaxSquareGUI(incomeTax1.getTax());
        c.gridx = 5;
        c.gridy = 7;
        this.add(incomeTaxGUI1, c);

        //4th Square
        RailRoadSquare railRoad1 = (RailRoadSquare) squareList[4];
        RailRoadSquareGUI railRoadGUI1 = new RailRoadSquareGUI(railRoad1.getPrice());
        c.gridx = 4;
        c.gridy = 7;
        this.add(railRoadGUI1, c);

        //5th Square
        c.gridx = 3;
        c.gridy = 7;
        PropertySquare property3 = (PropertySquare) squareList[5];
        PropertySquareGUI propertyGUI3 = new PropertySquareGUI(property3.getColor(), property3.getName(), String.valueOf(property3.getPrice()));

        this.add(propertyGUI3, c);

        //6th Square
        c.gridx = 2;
        c.gridy = 7;
        PropertySquare property4 = (PropertySquare) squareList[6];
        PropertySquareGUI propertyGUI4 = new PropertySquareGUI(property4.getColor(), property4.getName(), String.valueOf(property4.getPrice()));

        this.add(propertyGUI4, c);

        //7th Square
        c.gridx = 1;
        c.gridy = 7;
        PropertySquare property5 = (PropertySquare) squareList[7];
        PropertySquareGUI propertyGUI5 = new PropertySquareGUI(property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));

        this.add(propertyGUI5, c);

        //8th Square
        c.gridx = 0;
        c.gridy = 7;
        JailSquare jail = (JailSquare) squareList[8];
        JailSquareGUI jailGUI = new JailSquareGUI();

        this.add(jailGUI, c);

        //9th Square
        c.gridy = 6;
        PropertySquare property6 = (PropertySquare) squareList[9];
        PropertySquareGUI propertyGUI6 = new PropertySquareGUI(property6.getColor(), property6.getName(), String.valueOf(property6.getPrice()));

        this.add(propertyGUI6, c);

        //10th Square
        c.gridy = 5;
        UtilitySquare utility1 = (UtilitySquare) squareList[10];
        UtilitySquareGUI utilityGUI1 = new UtilitySquareGUI(300, "Water Works");

        this.add(utilityGUI1, c);

        //11th Square
        c.gridy = 4;
        PropertySquare property7 = (PropertySquare) squareList[11];
        PropertySquareGUI propertyGUI7 = new PropertySquareGUI(property7.getColor(), property7.getName(), String.valueOf(property7.getPrice()));

        this.add(propertyGUI7, c);

        //12th Square
        c.gridy = 3;
        PropertySquare property8 = (PropertySquare) squareList[12];
        PropertySquareGUI propertyGUI8 = new PropertySquareGUI(property8.getColor(), property8.getName(), String.valueOf(property8.getPrice()));

        this.add(propertyGUI8, c);

        //13th Square
        c.gridy = 2;
        PropertySquare property9 = (PropertySquare) squareList[13];
        PropertySquareGUI propertyGUI9 = new PropertySquareGUI(property9.getColor(), property9.getName(), String.valueOf(property9.getPrice()));

        this.add(propertyGUI9, c);

        //14th Square
        c.gridx = 1;
        c.gridy = 2;
        PropertySquare property10 = (PropertySquare) squareList[14];
        PropertySquareGUI propertyGUI10 = new PropertySquareGUI(property10.getColor(), property10.getName(), String.valueOf(property10.getPrice()));

        this.add(propertyGUI10, c);

        //15th Square
        c.gridx = 2;
        c.gridy = 2;
        PropertySquare property11 = (PropertySquare) squareList[15];
        PropertySquareGUI propertyGUI11 = new PropertySquareGUI(property11.getColor(), property11.getName(), String.valueOf(property11.getPrice()));

        this.add(propertyGUI11, c);

        //16th Square
        c.gridx = 3;
        c.gridy = 2;
        PropertySquare property12 = (PropertySquare) squareList[16];
        PropertySquareGUI propertyGUI12 = new PropertySquareGUI(property12.getColor(), property12.getName(), String.valueOf(property12.getPrice()));

        this.add(propertyGUI12, c);

        //17th Square
        c.gridx = 4;
        c.gridy = 2;
        PropertySquare property13 = (PropertySquare) squareList[17];
        PropertySquareGUI propertyGUI13 = new PropertySquareGUI(property13.getColor(), property13.getName(), String.valueOf(property13.getPrice()));

        this.add(propertyGUI13, c);

        //18th Square
        c.gridx = 5;
        c.gridy = 2;
        PropertySquare property14 = (PropertySquare) squareList[18];
        PropertySquareGUI propertyGUI14 = new PropertySquareGUI(property14.getColor(), property14.getName(), String.valueOf(property14.getPrice()));

        this.add(propertyGUI14, c);

        //19th Square
        c.gridx = 6;
        c.gridy = 2;
        PropertySquare property15 = (PropertySquare) squareList[19];
        PropertySquareGUI propertyGUI15 = new PropertySquareGUI(property15.getColor(), property15.getName(), String.valueOf(property15.getPrice()));

        this.add(propertyGUI15, c);

        //20th Square
        c.gridx = 7;
        c.gridy = 2;
        PropertySquare property16 = (PropertySquare) squareList[20];
        PropertySquareGUI propertyGUI16 = new PropertySquareGUI(property16.getColor(), property16.getName(), String.valueOf(property16.getPrice()));

        this.add(propertyGUI16, c);

        //21st Square (Free Parking)
        c.gridx = 8;
        c.gridy = 2;
        FreeParkingSquareGUI freeParking = new FreeParkingSquareGUI();

        this.add(freeParking, c);

        //22nd Square (Go To Gail)
        GoToJailGUI goToJail = new GoToJailGUI();
        c.gridy = 3;
        this.add(goToJail, c);

        //23rd Square
        c.gridy = 4;
        PropertySquare property17 = (PropertySquare) squareList[21];
        PropertySquareGUI propertyGUI17 = new PropertySquareGUI(property17.getColor(), property17.getName(), String.valueOf(property17.getPrice()));

        this.add(propertyGUI17, c);

        //24th Square
        c.gridy = 5;
        PropertySquare property18 = (PropertySquare) squareList[22];
        PropertySquareGUI propertyGUI18 = new PropertySquareGUI(property18.getColor(), property18.getName(), String.valueOf(property18.getPrice()));

        this.add(propertyGUI18, c);

        //24th Square
        c.gridy = 6;
        PropertySquare property19 = (PropertySquare) squareList[23];
        PropertySquareGUI propertyGUI19 = new PropertySquareGUI(property19.getColor(), property19.getName(), String.valueOf(property19.getPrice()));

        this.add(propertyGUI19, c);

        //24th Square
        c.gridy = 7;
        PropertySquare property20 = (PropertySquare) squareList[24];
        PropertySquareGUI propertyGUI20 = new PropertySquareGUI(property20.getColor(), property20.getName(), String.valueOf(property20.getPrice()));

        this.add(propertyGUI20, c);


    }

}

