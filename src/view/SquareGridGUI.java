package view;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SquareGridGUI extends JPanel {
    private Square[] square;
    private SquareGUI squareGUIs[];

    public SquareGridGUI(Square[] square, ArrayList<Player> players) {
        this.square = square;
        this.squareGUIs= new SquareGUI[33];
        this.setBackground(new Color(205, 230, 208));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(920, 670));

        //this.makeSquares();
        this.createSquareGUI();
        for(Player p: players){
            this.squareGUIs[0].addPlayer(p.getName());
        }


    }

   /* private void LoadSquareGUI(Square square, GridBagConstraints c) {
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
    }*/

    private void createSquareGUI() {

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.insets = new Insets(1, 0, 1, 0);  //top padding

        //NewMethod(c);
        originalMethod(c);
    }

    /*public void NewMethod(GridBagConstraints c){
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
    }*/

    public void originalMethod(GridBagConstraints c) {

        // 0th Square : Starting Point
        c.gridx = 8;
        c.gridy = 7;
        GoSquare goSquare = (GoSquare) square[0];
        GoSquareGUI goSquareGUI = new GoSquareGUI();
        this.add(goSquareGUI, c);
        this.squareGUIs[0] = goSquareGUI;

        // 1st Square
        c.gridx = 7;
        PropertySquare property1 = (PropertySquare) square[1];
        PropertySquareGUI propertyGUI1 = new PropertySquareGUI(property1.getColor(), property1.getName(), String.valueOf(property1.getPrice()));
        this.add(propertyGUI1, c);

        //2nd Square
        c.gridx = 6;
        PropertySquare property2 = (PropertySquare) square[2];
        PropertySquareGUI propertyGUI2 = new PropertySquareGUI(property2.getColor(), property2.getName(), String.valueOf(property2.getPrice()));
        this.add(propertyGUI2, c);

        //3rd Square
        IncomeTaxSquare incomeTax1 = (IncomeTaxSquare) square[3];
        IncomeTaxSquareGUI incomeTaxGUI1 = new IncomeTaxSquareGUI(incomeTax1.getTax());
        c.gridx = 5;
        this.add(incomeTaxGUI1, c);

        //4th Square
        RailRoadSquare railRoad1 = (RailRoadSquare) square[4];
        RailRoadSquareGUI railRoadGUI1 = new RailRoadSquareGUI(railRoad1.getName(),railRoad1.getPrice());
        c.gridx = 4;
        this.add(railRoadGUI1, c);

        //5th Square
        c.gridx = 3;
        PropertySquare property3 = (PropertySquare) square[5];
        PropertySquareGUI propertyGUI3 = new PropertySquareGUI(property3.getColor(), property3.getName(), String.valueOf(property3.getPrice()));
        this.add(propertyGUI3, c);

        //6th Square
        c.gridx = 2;
        PropertySquare property4 = (PropertySquare) square[6];
        PropertySquareGUI propertyGUI4 = new PropertySquareGUI(property4.getColor(), property4.getName(), String.valueOf(property4.getPrice()));
        this.add(propertyGUI4, c);

        //7th Square
        c.gridx = 1;
        PropertySquare property5 = (PropertySquare) square[7];
        PropertySquareGUI propertyGUI5 = new PropertySquareGUI(property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));
        this.add(propertyGUI5, c);

        //8th Square
        c.gridx = 0;
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
        UtilitySquareGUI utilityGUI1 = new UtilitySquareGUI(300, "Electric");
        this.add(utilityGUI1, c);

        //11th Square
        c.gridy = 4;
        PropertySquare property8 = (PropertySquare) square[11];
        PropertySquareGUI propertyGUI8 = new PropertySquareGUI(property8.getColor(), property8.getName(), String.valueOf(property8.getPrice()));
        this.add(propertyGUI8, c);

        //12th Square
        c.gridy = 3;
        PropertySquare property9 = (PropertySquare) square[12];
        PropertySquareGUI propertyGUI9 = new PropertySquareGUI(property9.getColor(), property9.getName(), String.valueOf(property9.getPrice()));
        this.add(propertyGUI9, c);

        //13th Square
        c.gridy = 2;
        RailRoadSquare railRoad2 = (RailRoadSquare) square[13];
        RailRoadSquareGUI railRoadGUI2 = new RailRoadSquareGUI(railRoad2.getName(),railRoad2.getPrice());
        this.add(railRoadGUI2, c);

        //14th Square
        c.gridx = 1;
        PropertySquare property10 = (PropertySquare) square[14];
        PropertySquareGUI propertyGUI10 = new PropertySquareGUI(property10.getColor(), property10.getName(), String.valueOf(property10.getPrice()));
        this.add(propertyGUI10, c);

        //15th Square
        c.gridx = 1;
        PropertySquare property11 = (PropertySquare) square[15];
        PropertySquareGUI propertyGUI11 = new PropertySquareGUI(property11.getColor(), property11.getName(), String.valueOf(property11.getPrice()));
        this.add(propertyGUI11, c);

        //16th Square
        c.gridx = 2;
        PropertySquare property12 = (PropertySquare) square[16];
        PropertySquareGUI propertyGUI12 = new PropertySquareGUI(property12.getColor(), property12.getName(), String.valueOf(property12.getPrice()));
        this.add(propertyGUI12, c);

        //17th Square
        c.gridx = 2;
        c.gridy = 3;
        FreeParkingSquare freeParking = (FreeParkingSquare) square[17];
        FreeParkingSquareGUI freeParkingGUI = new FreeParkingSquareGUI();
        this.add(freeParkingGUI, c);

        //18th Square
        c.gridy = 4;
        PropertySquare property13 = (PropertySquare) square[18];
        PropertySquareGUI propertyGUI13 = new PropertySquareGUI(property13.getColor(), property13.getName(), String.valueOf(property13.getPrice()));
        this.add(propertyGUI13, c);

        //19th Square
        c.gridy = 5;
        PropertySquare property14 = (PropertySquare) square[19];
        PropertySquareGUI propertyGUI14 = new PropertySquareGUI(property14.getColor(), property14.getName(), String.valueOf(property14.getPrice()));
        this.add(propertyGUI14, c);

        //20th Square
        c.gridx = 3;
        PropertySquare property15 = (PropertySquare) square[20];
        PropertySquareGUI propertyGUI15 = new PropertySquareGUI(property15.getColor(), property15.getName(), String.valueOf(property15.getPrice()));
        this.add(propertyGUI15, c);

        //21st Square
        c.gridx = 4;
        RailRoadSquare railRoad3 = (RailRoadSquare) square[21];
        RailRoadSquareGUI railRoad3GUI = new RailRoadSquareGUI(railRoad3.getName(),railRoad3.getPrice());
        this.add(railRoad3GUI, c);

        //22nd Square
        c.gridx = 5;
        PropertySquare property16 = (PropertySquare) square[22];
        PropertySquareGUI propertyGUI16 = new PropertySquareGUI(property16.getColor(), property16.getName(), String.valueOf(property16.getPrice()));
        this.add(propertyGUI16, c);

        //23rd Square
        c.gridx = 6;
        PropertySquare property17 = (PropertySquare) square[23];
        PropertySquareGUI propertyGUI17 = new PropertySquareGUI(property17.getColor(), property17.getName(), String.valueOf(property17.getPrice()));
        this.add(propertyGUI17, c);

        //24th Square
        c.gridy = 4;
        UtilitySquare utility2 = (UtilitySquare) square[24];
        UtilitySquareGUI utilityGUI2 = new UtilitySquareGUI(300, "Water Works");
        this.add(utilityGUI2, c);

        //26th Square
        c.gridy = 3;
        PropertySquare property18 = (PropertySquare) square[25];
        PropertySquareGUI propertyGUI18 = new PropertySquareGUI(property18.getColor(), property18.getName(), String.valueOf(property18.getPrice()));
        this.add(propertyGUI18, c);

        //27th Square
        c.gridy = 2;
        GoToJailSquare goToJailSquare = (GoToJailSquare) square[26];
        GoToJailGUI goToJail = new GoToJailGUI();
        this.add(goToJail, c);

        //28th Square
        c.gridx = 7;
        PropertySquare property19 = (PropertySquare) square[27];
        PropertySquareGUI propertyGUI19= new PropertySquareGUI(property19.getColor(), property19.getName(), String.valueOf(property19.getPrice()));
        this.add(propertyGUI19, c);

        //30th Square
        c.gridx = 8;
        PropertySquare property20 = (PropertySquare) square[28];
        PropertySquareGUI propertyGUI20 = new PropertySquareGUI(property20.getColor(), property20.getName(), String.valueOf(property20.getPrice()));
        this.add(propertyGUI20, c);

        //31st Square
        c.gridy = 3;
        PropertySquare property21 = (PropertySquare) square[29];
        PropertySquareGUI propertyGUI21 = new PropertySquareGUI(property21.getColor(), property21.getName(), String.valueOf(property21.getPrice()));
        this.add(propertyGUI21, c);

        //32nd Square
        c.gridy = 4;
        RailRoadSquare railRoad4 = (RailRoadSquare) square[30];
        RailRoadSquareGUI railRoad4GUI = new RailRoadSquareGUI(railRoad4.getName(),railRoad4.getPrice());
        this.add(railRoad4GUI, c);

        //33rd Square
        c.gridy = 5;
        PropertySquare property22 = (PropertySquare) square[31];
        PropertySquareGUI propertyGUI22 = new PropertySquareGUI(property22.getColor(), property22.getName(), String.valueOf(property22.getPrice()));
        this.add(propertyGUI22, c);

        //34th Square
        c.gridy = 6;
        PropertySquare property23 = (PropertySquare) square[32];
        PropertySquareGUI propertyGUI23 = new PropertySquareGUI(property23.getColor(), property23.getName(), String.valueOf(property23.getPrice()));
        this.add(propertyGUI23, c);
    }

    public void changePlayerGUILocation(Player player, int currentLocationIndex, int nextLocationIndex) {
       // squareGUIs[currentLocationIndex].removePlayer();
    }

}

