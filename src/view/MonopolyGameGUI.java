package view;

import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MonopolyGameGUI extends JFrame {
    private final InfoDisplayGUI infoDisplayGUI;
    private final SquareGridGUI squareGUI;
    private final DiceGUI diceGUI;
    private String message;
    private int index;

    /**
     * Initialize the gui frame
     */
    public MonopolyGameGUI(MonopolyGame game) {
        //subscribe to game

        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1320, 670));

        this.setLayout(new BorderLayout());

        //pass the squares to the view
        Square[] squares = MonopolyGame.board.getSquares();
        ArrayList<Player> players = game.players;

        squareGUI = new SquareGridGUI(squares, players);
        this.add(squareGUI, BorderLayout.WEST);

        //infoPanel gui initialization
        this.infoDisplayGUI = new InfoDisplayGUI(game.getPlayerInTurn(), game.getPlayersNotInTurn());
        this.add(infoDisplayGUI, BorderLayout.EAST);

        //dice gui
        diceGUI= new DiceGUI();
        squareGUI.addDiceGUI(diceGUI);

        //propertySquare gui


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * add action listener to buttons in JPanel: SquareGridGUI
     */
    public void setController(MonopolyGameController controller) {
        this.infoDisplayGUI.setButtonControllers(controller);
    }

    /**
     * apply change to gui according to the command
     */
    public void handleUpdate(Player player, String command, ArrayList<Player> players) {
        Square newLocation = player.getCurrentLocation();
        Square lastLocation = player.getLastLocation();
        switch (command) {
            case "Next Turn" -> {
                //update the info panel with the current player info
                infoDisplayGUI.setName(player.getName());
                infoDisplayGUI.setCash(player.getCash());
                infoDisplayGUI.setPropertyList(player.getProperties().toString());

                //update the info panel about not current players info
                int i;
                for(i=0; i<players.size(); i++){
                    infoDisplayGUI.setNames(players.get(i).getName(), i);
                    infoDisplayGUI.setCashes(players.get(i).getCash(), i);
                }
                for(int j=i; j<3; j++){
                    infoDisplayGUI.setNames("Bankrupted", j);
                    infoDisplayGUI.setCashes(0, j);
                }


                // refresh location, buy, rent.
                //start here
                infoDisplayGUI.setCurrentLocation(newLocation.getName());
                if (newLocation instanceof PropertySquare location) {
                    Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();
                    if (owner == null) {
                        infoDisplayGUI.setBuyPrice(location.getPrice());
                        infoDisplayGUI.setHousePrice(location.getHousePrice());
                        infoDisplayGUI.setHotelPrice(location.getHotelPrice());
                    }
                    else {
                        infoDisplayGUI.setBuyPrice(-2);
                        infoDisplayGUI.setHousePrice(-2);
                        infoDisplayGUI.setHotelPrice(-2);
                    }
                    infoDisplayGUI.setRentPrice(location.getRentFee());
                } else {
                    infoDisplayGUI.setBuyPrice(-1);
                    infoDisplayGUI.setHousePrice(-1);
                    infoDisplayGUI.setHotelPrice(-1);
                    infoDisplayGUI.setRentPrice(-1);
                }
                //ends here
                if(!player.getAvailableProperties(player.removeRailroadUtility(player.hasWholeSet())).isEmpty()){
                    infoDisplayGUI.setBuildEnabled(true);
                } else {
                    infoDisplayGUI.setBuildEnabled(false);
                }
                if(!player.hasBuilding().isEmpty()){
                    infoDisplayGUI.setSellHEnabled(true);
                }else {
                    infoDisplayGUI.setSellEnabled(false);
                }

                infoDisplayGUI.setBuyEnabled(false);
                infoDisplayGUI.setSellEnabled(!player.getProperties().isEmpty());
                infoDisplayGUI.setNextEnabled(false);
                infoDisplayGUI.setRollEnabled(true);
                squareGUI.setMessage("");
            }
            case "Buy" -> {
                ArrayList<PropertySquare> property = player.getProperties();
                infoDisplayGUI.setPropertyList(property.toString());
                infoDisplayGUI.setCash(player.getCash());
                infoDisplayGUI.setBuyEnabled(false);
                infoDisplayGUI.setSellEnabled(true);
                squareGUI.addMessage(player.getName() +
                        " just bought " + property.get(property.size() - 1).getName() +
                        "[" + property.get(property.size() - 1).getNumber() + "]\n");
                if(!player.getAvailableProperties(player.removeRailroadUtility(player.hasWholeSet())).isEmpty()){
                    infoDisplayGUI.setBuildEnabled(true);
                } else {
                    infoDisplayGUI.setBuildEnabled(false);
                }
                if(!player.hasBuilding().isEmpty()){
                    infoDisplayGUI.setSellHEnabled(true);
                }else {
                    infoDisplayGUI.setSellEnabled(false);
                }
                infoDisplayGUI.setBuyEnabled(false);
            }
            case "Sell" -> {
                infoDisplayGUI.setPropertyList(player.getProperties().toString());
                infoDisplayGUI.setCash(player.getCash());
                if(!player.getAvailableProperties(player.removeRailroadUtility(player.hasWholeSet())).isEmpty()){
                    infoDisplayGUI.setBuildEnabled(true);
                } else {
                    infoDisplayGUI.setBuildEnabled(false);
                }
                if(!player.hasBuilding().isEmpty()){
                    infoDisplayGUI.setSellHEnabled(true);
                }else {
                    infoDisplayGUI.setSellEnabled(false);
                }
            }
            case "Roll Dice" -> {
                StringBuilder message = new StringBuilder(player.getName() +
                        " move from " + lastLocation.getName() + "[" +
                        lastLocation.getNumber() + "]   to   "
                        + newLocation.getName() + "["
                        + newLocation.getNumber() + "]\n");

                //remove player gui form last location and add to new location
                squareGUI.changePlayerGUILocation(player, lastLocation.getNumber(), newLocation.getNumber());


                //set dice value
                int[] diceValues = MonopolyGame.dice.getDice();
                diceGUI.setDiceImages(diceValues[0], diceValues[1]);

                // refresh location, buy, rent and cash.
                //start here
                infoDisplayGUI.setCurrentLocation(newLocation.getName());
                if (newLocation instanceof PropertySquare) {
                    PropertySquare location = (PropertySquare) player.getCurrentLocation();
                    Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();
                    if (owner == null) {
                        infoDisplayGUI.setBuyPrice(location.getPrice());
                        infoDisplayGUI.setHousePrice(location.getHousePrice());
                        infoDisplayGUI.setHotelPrice(location.getHotelPrice());
                        infoDisplayGUI.setBuyEnabled(true);
                    } else {
                        infoDisplayGUI.setBuyPrice(-2);
                        infoDisplayGUI.setHousePrice(-2);
                        infoDisplayGUI.setHotelPrice(-2);
                        if (owner != player) {
                            message.append(player.getName()).append(" just paid ").append(owner.getName()).append("       ").append(location.getRentFee()).append(" as rent fee\n");
                        }
                        infoDisplayGUI.setBuyEnabled(false);
                    }
                    infoDisplayGUI.setRentPrice(location.getRentFee());
                } else {
                    infoDisplayGUI.setBuyPrice(-1);
                    infoDisplayGUI.setHousePrice(-1);
                    infoDisplayGUI.setHotelPrice(-1);
                    infoDisplayGUI.setRentPrice(-1);
                    infoDisplayGUI.setBuyEnabled(false);
                    message.append(player.getName()).append(player.getCurrentLocation().message);
                }
                infoDisplayGUI.setBuildEnabled(!player.getAvailableProperties(player.removeRailroadUtility(player.hasWholeSet())).isEmpty());
                infoDisplayGUI.setSellHEnabled(!player.hasBuilding().isEmpty());
                infoDisplayGUI.setCash(player.getCash());
                //ends here

                //enable or disable appropriate buttons
                infoDisplayGUI.setSellEnabled(!player.getProperties().isEmpty());
                infoDisplayGUI.setNextEnabled(true);
                infoDisplayGUI.setRollEnabled(false);
                squareGUI.setMessage(message.toString());
            }
            case "Bankrupt" -> {
                squareGUI.removePlayerGUILocation(player, newLocation.getNumber());
                JOptionPane.showMessageDialog(this, "Player Bankrupt " + player.getName());
                infoDisplayGUI.setBuyEnabled(false);
                infoDisplayGUI.setSellEnabled(false);
                infoDisplayGUI.setNextEnabled(true);
                infoDisplayGUI.setRollEnabled(false);
            }
            case "Winner" -> {
                squareGUI.removePlayerGUILocation(player, lastLocation.getNumber());
                JOptionPane.showMessageDialog(this, "We have a Winner!! Player " + player.getName());
                infoDisplayGUI.setBuyEnabled(false);
                infoDisplayGUI.setSellEnabled(false);
                infoDisplayGUI.setNextEnabled(false);
                infoDisplayGUI.setRollEnabled(false);
            }
            case "build", "sellH" -> HotelOrHouse(player, command);
        }
    }


    public void getDecision(ArrayList<PropertySquare> p, MonopolyGame game){
        JFrame popup = new JFrame("Select a property to build");
        popup.setBounds(500, 400, 640, 120);
        GridLayout grid = new GridLayout(0, 4);
        popup.setLayout(grid);
        JButton btn;
        for(PropertySquare property: p){
            btn = new JButton(property.getName());
            btn.addActionListener(e -> {
                JButton b = (JButton)e.getSource();
                index = game.setSelectedProperty(b.getText());
            });
            popup.add(btn);
        }
        popup.setVisible(true);
    }

    public void HotelOrHouse(Player player, String command){
        JFrame popup = new JFrame("House or Hotel");
        popup.setBounds(500, 400, 160, 120);
        GridLayout grid = new GridLayout(1, 2);
        popup.setLayout(grid);
        JButton house = new JButton("House"), hotel = new JButton("Hotel");
        ActionListener al = e -> {
            int price = -1;
            String temp="", decision = ((JButton) e.getSource()).getText();
            if(command.equals("build")){
                price = player.buildH(decision);
                temp = "build";
                if (decision.equals("House")){
                    if (squareGUI.getPropertySquareGUI(index) instanceof PropertySquareGUI){
                        for (int i = 1; i < 5; i++) {
                            if (((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).isBuilding(i)) {
                                ((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).setBuildingX(Color.GREEN, i);
                                break;
                            }
                        }
                    }
                }else if (decision.equals("Hotel")) {
                    if (squareGUI.getPropertySquareGUI(index) instanceof PropertySquareGUI) {
                        for (int i = 1; i < 5; i++) {
                            if (((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).isBuilding(i)) {
                                ((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).setBuildingX(Color.GREEN, i);
                            }
                        }
                        ((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).setBuildingX(Color.RED, 5);

                    }
                }
            } else if(command.equals("sellH")) {
                price = player.sellH(decision);
                temp = "sell";
                if (decision.equals("House")){
                    if (squareGUI.getPropertySquareGUI(index) instanceof PropertySquareGUI){
                        for (int i = 1; i < 5; i++) {
                            if (((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).isBuilding(i)) {
                                ((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).setBuildingX(Color.WHITE, i - 1);
                                break;
                            }
                        }
                    }
                }else if (decision.equals("Hotel")) {
                    if (squareGUI.getPropertySquareGUI(index) instanceof PropertySquareGUI) {
                        ((PropertySquareGUI) squareGUI.getPropertySquareGUI(index)).setBuildingX(Color.WHITE, 5);
                    }
                }
            }
            infoDisplayGUI.setCash(player.getCash());
            if(price >= 0){
                setMessage(player.getName()+" has " + temp +" a "+decision + " on "
                        +player.getSelectedSquare().getName() + "\n" + player.getName() +
                        " paid $" + price + "to build it\n");
            } else if (price == -1){
                setMessage(player.getName()+" cannot " + temp + " on "+
                        player.getSelectedSquare().getName() + "\n");
            }else{
                setMessage(null);
            }
            if (!player.hasBuilding().isEmpty()){
                infoDisplayGUI.setSellHEnabled(true);
            }
            squareGUI.setMessage(message);
            setMessage(null);
        };
        house.addActionListener(al);
        hotel.addActionListener(al);
        popup.add(house);
        popup.add(hotel);


        if(player.getCash() < player.getSelectedSquare().getHotelPrice()) {
            hotel.setEnabled(false);
        }
        house.setEnabled(true);


        popup.setVisible(true);
    }

    private void setMessage(String m) {
        this.message = m;
    }
}