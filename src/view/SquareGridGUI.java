package view;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SquareGridGUI extends JPanel {
    private final Square[] square;
    private final SquareGUI[] squareGUIs;
    private JTextArea message;

    /**
     * initalize the game map (left part)
     */
    public SquareGridGUI(Square[] square, ArrayList<Player> players) {
        this.square = square;
        this.squareGUIs = new SquareGUI[square.length];
        this.setBackground(new Color(205, 230, 208));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(920, 680));

        //this.makeSquares();
        this.createSquareGUI();
        for (Player p : players) {
            this.squareGUIs[0].addPlayer(p.getName());
        }
    }

    /**
     * initialize the map and textArea
     */
    private void createSquareGUI() {

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(1, 1, 0, 0);  //top padding

        NewMethod(c);
        //originalMethod(c);

        message = new JTextArea();
        message.setPreferredSize(new Dimension(450, 80));
        message.setBackground(new Color(205, 230, 208));
        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 6;
        this.add(message, c);
    }

    /**
     * load different kinds of squares
     */
     private void LoadSquareGUI(Square square, GridBagConstraints c, int number) {
        SquareGUI s = null;
        if (square instanceof GoSquare){
            s = new GoSquareGUI();

        } else if (square instanceof UtilitySquare utility) {
            s = new UtilitySquareGUI(300, utility.getName());

        } else if (square instanceof RailRoadSquare railRoad) {
            s = new RailRoadSquareGUI(railRoad.getName(), railRoad.getPrice());

        } else if (square instanceof PropertySquare property) {
            s = new PropertySquareGUI(property.getColor(), property.getName(), String.valueOf(property.getPrice()));

        } else if (square instanceof IncomeTaxSquare incomeTax) {
            s = new IncomeTaxSquareGUI(incomeTax.getTax());

        } else if (square instanceof GoToJailSquare) {
            s = new GoToJailGUI();

        } else if (square instanceof JailSquare) {
            s = new JailSquareGUI();

        } else if (square instanceof FreeParkingSquare) {
            s = new FreeParkingSquareGUI();
        }
         assert s != null;
         this.add(s, c);
         this.squareGUIs[number] = s;
    }

    /**
     * initialization of the map called by createSquareGUI()
     */
    public void NewMethod(GridBagConstraints c){
        c.gridy = 7;
        c.gridx = 10;
        for(int j=0; j < square.length; j++){
            if(j < 10) c.gridx -= 1;
            else if(j < 15) c.gridy -= 1;
            else if(j < 17) c.gridx += 1;
            else if(j < 20) c.gridy += 1;
            else if(j < 25) c.gridx += 1;
            else if(j < 28) c.gridy -= 1;
            else if(j < 30) c.gridx += 1;
            else c.gridy += 1;

            LoadSquareGUI(square[j], c, j);
        }
    }

    /**
     * initialization of the map called by createSquareGUI()
     */
    /*public void originalMethod(GridBagConstraints c) {

        // 0th Square : Starting Point
        c.gridx = 8;
        c.gridy = 7;
        GoSquareGUI goSquareGUI = new GoSquareGUI();
        this.add(goSquareGUI, c);
        this.squareGUIs[0] = goSquareGUI;

        // 1st Square
        c.gridx = 7;
        PropertySquare property1 = (PropertySquare) square[1];
        PropertySquareGUI propertyGUI1 = new PropertySquareGUI(property1.getColor(), property1.getName(), String.valueOf(property1.getPrice()));
        this.add(propertyGUI1, c);
        this.squareGUIs[1] = propertyGUI1;

        //2nd Square
        c.gridx = 6;
        PropertySquare property2 = (PropertySquare) square[2];
        PropertySquareGUI propertyGUI2 = new PropertySquareGUI(property2.getColor(), property2.getName(), String.valueOf(property2.getPrice()));
        this.add(propertyGUI2, c);
        this.squareGUIs[2] = propertyGUI2;

        //3rd Square
        IncomeTaxSquare incomeTax1 = (IncomeTaxSquare) square[3];
        IncomeTaxSquareGUI incomeTaxGUI1 = new IncomeTaxSquareGUI(incomeTax1.getTax());
        c.gridx = 5;
        this.add(incomeTaxGUI1, c);
        this.squareGUIs[3] = incomeTaxGUI1;

        //4th Square
        RailRoadSquare railRoad1 = (RailRoadSquare) square[4];
        RailRoadSquareGUI railRoadGUI1 = new RailRoadSquareGUI(railRoad1.getName(), railRoad1.getPrice());
        c.gridx = 4;
        this.add(railRoadGUI1, c);
        this.squareGUIs[4] = railRoadGUI1;

        //5th Square
        c.gridx = 3;
        PropertySquare property3 = (PropertySquare) square[5];
        PropertySquareGUI propertyGUI3 = new PropertySquareGUI(property3.getColor(), property3.getName(), String.valueOf(property3.getPrice()));
        this.add(propertyGUI3, c);
        this.squareGUIs[5] = propertyGUI3;

        //6th Square
        c.gridx = 2;
        PropertySquare property4 = (PropertySquare) square[6];
        PropertySquareGUI propertyGUI4 = new PropertySquareGUI(property4.getColor(), property4.getName(), String.valueOf(property4.getPrice()));
        this.add(propertyGUI4, c);
        this.squareGUIs[6] = propertyGUI4;

        //7th Square
        c.gridx = 1;
        PropertySquare property5 = (PropertySquare) square[7];
        PropertySquareGUI propertyGUI5 = new PropertySquareGUI(property5.getColor(), property5.getName(), String.valueOf(property5.getPrice()));
        this.add(propertyGUI5, c);
        this.squareGUIs[7] = propertyGUI5;

        //8th Square
        c.gridx = 0;
        JailSquareGUI jailGUI = new JailSquareGUI();
        this.add(jailGUI, c);
        this.squareGUIs[8] = jailGUI;

        //9th Square
        c.gridy = 6;
        PropertySquare property6 = (PropertySquare) square[9];
        PropertySquareGUI propertyGUI6 = new PropertySquareGUI(property6.getColor(), property6.getName(), String.valueOf(property6.getPrice()));
        this.add(propertyGUI6, c);
        this.squareGUIs[9] = propertyGUI6;

        //10th Square
        c.gridy = 5;
        UtilitySquare utility1 = (UtilitySquare) square[10];
        UtilitySquareGUI utilityGUI1 = new UtilitySquareGUI(300, utility1.getName());
        this.add(utilityGUI1, c);
        this.squareGUIs[10] = utilityGUI1;

        //11th Square
        c.gridy = 4;
        PropertySquare property8 = (PropertySquare) square[11];
        PropertySquareGUI propertyGUI8 = new PropertySquareGUI(property8.getColor(), property8.getName(), String.valueOf(property8.getPrice()));
        this.add(propertyGUI8, c);
        this.squareGUIs[11] = propertyGUI8;

        //12th Square
        c.gridy = 3;
        PropertySquare property9 = (PropertySquare) square[12];
        PropertySquareGUI propertyGUI9 = new PropertySquareGUI(property9.getColor(), property9.getName(), String.valueOf(property9.getPrice()));
        this.add(propertyGUI9, c);
        this.squareGUIs[12] = propertyGUI9;

        //13th Square
        c.gridy = 2;
        RailRoadSquare railRoad2 = (RailRoadSquare) square[13];
        RailRoadSquareGUI railRoadGUI2 = new RailRoadSquareGUI(railRoad2.getName(), railRoad2.getPrice());
        this.add(railRoadGUI2, c);
        this.squareGUIs[13] = railRoadGUI2;

        //14th Square
        c.gridx = 1;
        PropertySquare property10 = (PropertySquare) square[14];
        PropertySquareGUI propertyGUI10 = new PropertySquareGUI(property10.getColor(), property10.getName(), String.valueOf(property10.getPrice()));
        this.add(propertyGUI10, c);
        this.squareGUIs[14] = propertyGUI10;

        //15th Square
        c.gridx = 2;
        PropertySquare property11 = (PropertySquare) square[15];
        PropertySquareGUI propertyGUI11 = new PropertySquareGUI(property11.getColor(), property11.getName(), String.valueOf(property11.getPrice()));
        this.add(propertyGUI11, c);
        this.squareGUIs[15] = propertyGUI11;

        //16th Square
        c.gridx = 3;
        PropertySquare property12 = (PropertySquare) square[16];
        PropertySquareGUI propertyGUI12 = new PropertySquareGUI(property12.getColor(), property12.getName(), String.valueOf(property12.getPrice()));
        this.add(propertyGUI12, c);
        this.squareGUIs[16] = propertyGUI12;

        //17th Square
        c.gridx = 2;
        c.gridy = 3;
        FreeParkingSquareGUI freeParkingGUI = new FreeParkingSquareGUI();
        this.add(freeParkingGUI, c);
        this.squareGUIs[17] = freeParkingGUI;

        //18th Square
        c.gridy = 4;
        PropertySquare property13 = (PropertySquare) square[18];
        PropertySquareGUI propertyGUI13 = new PropertySquareGUI(property13.getColor(), property13.getName(), String.valueOf(property13.getPrice()));
        this.add(propertyGUI13, c);
        this.squareGUIs[18] = propertyGUI13;

        //19th Square
        c.gridy = 5;
        PropertySquare property14 = (PropertySquare) square[19];
        PropertySquareGUI propertyGUI14 = new PropertySquareGUI(property14.getColor(), property14.getName(), String.valueOf(property14.getPrice()));
        this.add(propertyGUI14, c);
        this.squareGUIs[19] = propertyGUI14;

        //20th Square
        c.gridx = 3;
        PropertySquare property15 = (PropertySquare) square[20];
        PropertySquareGUI propertyGUI15 = new PropertySquareGUI(property15.getColor(), property15.getName(), String.valueOf(property15.getPrice()));
        this.add(propertyGUI15, c);
        this.squareGUIs[20] = propertyGUI15;

        //21st Square
        c.gridx = 4;
        RailRoadSquare railRoad3 = (RailRoadSquare) square[21];
        RailRoadSquareGUI railRoad3GUI = new RailRoadSquareGUI(railRoad3.getName(), railRoad3.getPrice());
        this.add(railRoad3GUI, c);
        this.squareGUIs[21] = railRoad3GUI;

        //22nd Square
        c.gridx = 5;
        PropertySquare property16 = (PropertySquare) square[22];
        PropertySquareGUI propertyGUI16 = new PropertySquareGUI(property16.getColor(), property16.getName(), String.valueOf(property16.getPrice()));
        this.add(propertyGUI16, c);
        this.squareGUIs[22] = propertyGUI16;

        //23rd Square
        c.gridx = 6;
        PropertySquare property17 = (PropertySquare) square[23];
        PropertySquareGUI propertyGUI17 = new PropertySquareGUI(property17.getColor(), property17.getName(), String.valueOf(property17.getPrice()));
        this.add(propertyGUI17, c);
        this.squareGUIs[23] = propertyGUI17;

        //24th Square
        c.gridy = 4;
        UtilitySquare utility2 = (UtilitySquare) square[24];
        UtilitySquareGUI utilityGUI2 = new UtilitySquareGUI(300, utility2.getName());
        this.add(utilityGUI2, c);
        this.squareGUIs[24] = utilityGUI2;

        //26th Square
        c.gridy = 3;
        PropertySquare property18 = (PropertySquare) square[25];
        PropertySquareGUI propertyGUI18 = new PropertySquareGUI(property18.getColor(), property18.getName(), String.valueOf(property18.getPrice()));
        this.add(propertyGUI18, c);
        this.squareGUIs[25] = propertyGUI18;

        //27th Square
        c.gridy = 2;
        GoToJailGUI goToJailGUI = new GoToJailGUI();
        this.add(goToJailGUI, c);
        this.squareGUIs[26] = goToJailGUI;

        //28th Square
        c.gridx = 7;
        PropertySquare property19 = (PropertySquare) square[27];
        PropertySquareGUI propertyGUI19 = new PropertySquareGUI(property19.getColor(), property19.getName(), String.valueOf(property19.getPrice()));
        this.add(propertyGUI19, c);
        this.squareGUIs[27] = propertyGUI19;

        //30th Square
        c.gridx = 8;
        PropertySquare property20 = (PropertySquare) square[28];
        PropertySquareGUI propertyGUI20 = new PropertySquareGUI(property20.getColor(), property20.getName(), String.valueOf(property20.getPrice()));
        this.add(propertyGUI20, c);
        this.squareGUIs[28] = propertyGUI20;

        //31st Square
        c.gridy = 3;
        PropertySquare property21 = (PropertySquare) square[29];
        PropertySquareGUI propertyGUI21 = new PropertySquareGUI(property21.getColor(), property21.getName(), String.valueOf(property21.getPrice()));
        this.add(propertyGUI21, c);
        this.squareGUIs[29] = propertyGUI21;

        //32nd Square
        c.gridy = 4;
        RailRoadSquare railRoad4 = (RailRoadSquare) square[30];
        RailRoadSquareGUI railRoad4GUI = new RailRoadSquareGUI(railRoad4.getName(), railRoad4.getPrice());
        this.add(railRoad4GUI, c);
        this.squareGUIs[30] = railRoad4GUI;

        //33rd Square
        c.gridy = 5;
        PropertySquare property22 = (PropertySquare) square[31];
        PropertySquareGUI propertyGUI22 = new PropertySquareGUI(property22.getColor(), property22.getName(), String.valueOf(property22.getPrice()));
        this.add(propertyGUI22, c);
        this.squareGUIs[31] = propertyGUI22;

        //34th Square
        c.gridy = 6;
        PropertySquare property23 = (PropertySquare) square[32];
        PropertySquareGUI propertyGUI23 = new PropertySquareGUI(property23.getColor(), property23.getName(), String.valueOf(property23.getPrice()));
        this.add(propertyGUI23, c);
        this.squareGUIs[32] = propertyGUI23;
    }*/

    /**
     * refresh player's location on the map
     */
    public void changePlayerGUILocation(Player player, int currentLocationIndex, int nextLocationIndex) {
        squareGUIs[currentLocationIndex].removePlayer(player.getName(), player.isInJail());

        if (player.isInJail() && nextLocationIndex == 8) {
            JailSquareGUI jail = (JailSquareGUI) squareGUIs[nextLocationIndex];
            //if player already not in jail
            jail.addPlayerToJail(player.getName());
        } else {
            if (!player.isInJail() && nextLocationIndex == 8 && currentLocationIndex == 8) {
                JailSquareGUI jail = (JailSquareGUI) squareGUIs[nextLocationIndex];
                jail.removePlayer(player.getName(), true);
            }
            System.out.println("currentlocationIndex in square GUI " + currentLocationIndex);
            squareGUIs[nextLocationIndex].addPlayer(player.getName());
            System.out.println("nextLocationIndex in square GUI " + nextLocationIndex);
        }

    }

    /**
     * remove the player from the map
     */
    public void removePlayerGUILocation(Player player, int currentLocationIndex) {
        squareGUIs[currentLocationIndex].removePlayer(player.getName(), player.isInJail());
    }

    /**
     * add 2 dices to the map
     */
    public void addDiceGUI(DiceGUI diceGUI) {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.0;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 2;
        this.add(diceGUI, c);
    }

    /**
     * set textArea message
     */
    public void setMessage(String string) {
        message.setText(string);
    }

    /**
     * add message to textArea
     */
    public void addMessage(String string) {
        message.append(string);
    }

}

