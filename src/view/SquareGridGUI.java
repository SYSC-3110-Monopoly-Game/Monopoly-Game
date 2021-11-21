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

    public SquareGUI getPropertySquareGUI(int index){
        return squareGUIs[index];
    }


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

