package view;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SquareGridGUI extends JPanel {
    private final Square[] square;
    private final SquareGUI[] squareGUIs;
    private JTextArea message;
    private DiceGUI diceGUI;

    /**
     * initialize the game map (left part)
     */
    public SquareGridGUI(Square[] square, ArrayList<Player> players) {
        this.square = square;
        this.squareGUIs = new SquareGUI[square.length];
        this.setBackground(new Color(205, 230, 208));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(920, 680));

        this.createSquareGUI();
        for (Player p : players) {
            this.squareGUIs[p.getCurrentLocation().getNumber()].addPlayer(p.getName());
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

        makeSquares(c);

        message = new JTextArea();
        message.setPreferredSize(new Dimension(350, 60));
        message.setBackground(new Color(213, 218, 213));
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 4;
        this.add(message, c);

        //add dice to board
        diceGUI= new DiceGUI();
        c.weightx = 0.0;
        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 2;
        this.add(diceGUI, c);
    }

    /**
     * initialization of the map called by createSquareGUI()
     */
    public void makeSquares(GridBagConstraints c){
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

            SquareGUI s = null;
            if (square[j] instanceof GoSquare){
                s = new GoSquareGUI();

            } else if (square[j] instanceof UtilitySquare utility) {
                s = new UtilitySquareGUI(utility.getPrice(), utility.getName());

            } else if (square[j] instanceof RailRoadSquare railRoad) {
                s = new RailRoadSquareGUI(railRoad.getName(), railRoad.getPrice());

            } else if (square[j] instanceof PropertySquare property) {
                s = new PropertySquareGUI(property.getColor(), property.getName(), String.valueOf(property.getPrice()));

            } else if (square[j] instanceof TaxSquare incomeTax) {
                s = new TaxSquareGUI(incomeTax.getTax(), incomeTax.getName());

            } else if (square[j] instanceof GoToJailSquare) {
                s = new GoToJailGUI();

            } else if (square[j] instanceof JailSquare) {
                s = new JailSquareGUI();

            } else if (square[j] instanceof FreeParkingSquare) {
                s = new FreeParkingSquareGUI();
            }
            assert s != null;
            this.add(s, c);
            this.squareGUIs[j] = s;
        }
    }


    /**
     * get the property by its index
     * @param index
     * @return
     */
    public SquareGUI getPropertySquareGUI(int index){
        return squareGUIs[index];
    }


    /**
     * load the squares from Monopoly board.
     * @param board
     */
    public void loadSquaresOnMap(MonopolyBoard board){
        for(int i=0; i<board.getSquares().length; i++){
            if(board.getSquareAt(i) instanceof PropertySquare
                    && !(board.getSquareAt(i) instanceof RailRoadSquare)
                    && !(board.getSquareAt(i) instanceof UtilitySquare)){
                if(((PropertySquare) board.getSquareAt(i)).hasHouses()){
                    for(int j=0; j<((PropertySquare) board.getSquareAt(i)).getHouseNumber(); j++){
                        buildSellBuilding(Enums.BUILD, Enums.HOUSE, i);
                    }
                    if(((PropertySquare) board.getSquareAt(i)).hasHotel()){
                        buildSellBuilding(Enums.BUILD, Enums.HOTEL, i);
                    }
                }
            }
        }
        JailSquare jail = (JailSquare) board.getSquareAt(Constants.JAIL_SQUARE_INDEX);
        JailSquareGUI jailGUI = (JailSquareGUI) squareGUIs[Constants.JAIL_SQUARE_INDEX];
        String name;
        if(!jail.getMap().isEmpty()){
            for(Player p: jail.getMap().keySet()){
                if(p instanceof AIPlayer){
                    name = String.valueOf(p.getName().charAt(0));
                } else {
                    name = p.getName();
                }
                jailGUI.removePlayer(name, false);
                jailGUI.addPlayerToJail(name);
            }
        }
    }


    /**
     * refresh player's location on the map
     */
    public void changePlayerGUILocation(Player player, int currentLocationIndex, int nextLocationIndex) {
        String name = player.getName();
        if(player instanceof AIPlayer){
            name = String.valueOf(player.getName().charAt(0));
        }
        squareGUIs[currentLocationIndex].removePlayer(name, player.isInJail());
        JailSquareGUI jail = (JailSquareGUI) squareGUIs[Constants.JAIL_SQUARE_INDEX];
        if (player.isInJail() && nextLocationIndex == Constants.JAIL_SQUARE_INDEX) {

            //if player already not in jail
            jail.removePlayer(name, false);
            jail.addPlayerToJail(player.getName());
        } else {
            if (!player.isInJail() && nextLocationIndex == Constants.JAIL_SQUARE_INDEX &&
                    currentLocationIndex == Constants.JAIL_SQUARE_INDEX) {
                name = player.getName();
                if(player instanceof AIPlayer){
                    name = String.valueOf(player.getName().charAt(0));
                }
                jail.removePlayer(name, true);
            }
            System.out.println("current location Index in square GUI " + currentLocationIndex);
            squareGUIs[nextLocationIndex].addPlayer(player.getName());
            System.out.println("next Location Index in square GUI " + nextLocationIndex);
        }

    }

    /**
     * remove the player from the map
     */
    public void removePlayerGUILocation(Player player, int currentLocationIndex) {
        String name = player.getName();
        if(player instanceof AIPlayer){
            name = String.valueOf(player.getName().charAt(0));
        }
        squareGUIs[currentLocationIndex].removePlayer(name, player.isInJail());
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


    /**
     * refresh dice images
     * @param diceValue
     * @param diceValue1
     */
    public void setDiceImages(int diceValue, int diceValue1) {
        this.diceGUI.setDiceImages( diceValue,  diceValue1);
        this.repaint();
    }


    /**
     * build/sell (command) house/hotel (decision) on property on index.
     * @param command
     * @param decision
     * @param index
     */
    public void buildSellBuilding(Enums command, Enums decision, Integer index){
        if (command == Enums.BUILD) {
            if (decision== Enums.HOUSE) {
                for (int i = 1; i <= Constants.MAX_HOUSE_NUMBER; i++) {
                    if (((PropertySquareGUI) this.getPropertySquareGUI(index)).isBuilding(i)) {
                        ((PropertySquareGUI) this.getPropertySquareGUI(index)).setBuildingX(Color.GREEN, i);
                        break;
                    }
                }
            } else if (decision== Enums.HOTEL) {
                // when building a hotel, the property will build the 4 houses automatically
                for (int i = 1; i <= Constants.MAX_HOUSE_NUMBER; i++) {
                    if (((PropertySquareGUI) this.getPropertySquareGUI(index)).isBuilding(i)) {
                        ((PropertySquareGUI) this.getPropertySquareGUI(index)).setBuildingX(Color.GREEN, i);
                    }
                }
                ((PropertySquareGUI) this.getPropertySquareGUI(index)).setBuildingX(Color.RED, 5);
            }
        } else if (command == Enums.SELLH) {
            if (decision== Enums.HOUSE) {
                for (int i = Constants.MAX_HOUSE_NUMBER; i > 0; i--) {
                    if (!(((PropertySquareGUI) this.getPropertySquareGUI(index)).isBuilding(i))) {
                        ((PropertySquareGUI) this.getPropertySquareGUI(index)).setBuildingX(Color.WHITE, i);
                        break;
                    }
                }
            } else if (decision== Enums.HOTEL) {
                ((PropertySquareGUI) this.getPropertySquareGUI(index)).setBuildingX(Color.WHITE, 5);
            }

        }
    }
}

