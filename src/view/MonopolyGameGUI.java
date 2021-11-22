package view;

import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MonopolyGameGUI extends JFrame {
    private final InfoDisplayGUI infoDisplayGUI;
    private final SquareGridGUI squareGUI;
    private final MonopolyGame game;
    private String message;
    private int selectedPropertyIndex;

    /**
     * Initialize the gui frame
     */
    public MonopolyGameGUI(MonopolyGame game) {
        this.message="";
        this.game = game;
        //subscribe to game
        this.game.addView(this);
        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1320, 670));

        this.setLayout(new BorderLayout());

        //pass the squares to the view
        Square[] squares = MonopolyGame.board.getSquares();
        ArrayList<Player> players = game.players;

        //board gui
        squareGUI = new SquareGridGUI(squares, players);
        this.add(squareGUI, BorderLayout.WEST);

        //infoPanel gui initialization
        this.infoDisplayGUI = new InfoDisplayGUI(game.getPlayerInTurn(), game.getPlayersNotInTurn());
        this.add(infoDisplayGUI, BorderLayout.EAST);


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
        //information about player used
        Square currentLocation = player.getCurrentLocation();
        Square lastLocation = player.getLastLocation();
        String propertyList = player.getProperties().toString();
        int cash = player.getCash();
        String name = player.getName();
        switch (command) {
            case "Next Turn" -> {
                if(currentLocation instanceof PropertySquare square && square.getOwner()==player)
                    squareGUI.setMessage("You own this property!");
                else
                    squareGUI.setMessage("");
                //update the info panel with the current player info
                infoDisplayGUI.setName(name);
                infoDisplayGUI.setCash(cash);
                infoDisplayGUI.setPropertyList(propertyList);

                //update the info panel about not current players info
                infoDisplayGUI.setOtherPlayersInfo(players);

                // refresh location, buy, rent.
                infoDisplayGUI.setCurrentLocation(currentLocation.getName());

                setPriceInfo(currentLocation, player);
                setButtons(player);
                infoDisplayGUI.setBuyEnabled(false);


                infoDisplayGUI.setNextEnabled(false);
                infoDisplayGUI.setRollEnabled(true);


            }
            case "Buy" -> {
                ArrayList<PropertySquare> property = player.getProperties();
                infoDisplayGUI.setPropertyList(property.toString());
                infoDisplayGUI.setCash(cash);
                squareGUI.addMessage(player.getName() +
                        " just bought " + property.get(property.size() - 1).getName() +
                        "[" + property.get(property.size() - 1).getNumber() + "]\n");

                setButtons(player);

                infoDisplayGUI.setBuyEnabled(false);
            }
            case "Sell" -> {
                infoDisplayGUI.setPropertyList(propertyList);
                infoDisplayGUI.setCash(cash);

                setButtons(player);

                ArrayList<PropertySquare> PropertiesCanBeSell = player.getProperties();
                PropertiesCanBeSell.remove(player.hasBuilding());
                this.getSellDecision(PropertiesCanBeSell, player);
            }
            case "Roll Dice" -> {

                StringBuilder message = new StringBuilder(player.getName() +
                        " moved from " + lastLocation.getName() + "[" +
                        lastLocation.getNumber() + "] to "
                        + currentLocation.getName() + "["
                        + currentLocation.getNumber() + "]\n");

                //remove player gui form last location and add to new location
                squareGUI.changePlayerGUILocation(player, lastLocation.getNumber(), currentLocation.getNumber());

                //set dice value
                int[] diceValues = MonopolyGame.dice.getDice();
                squareGUI.setDiceImages(diceValues[0], diceValues[1]);

                if (player.isInJail()) {
                    if (MonopolyGame.dice.hasDoubles())
                        JOptionPane.showMessageDialog(squareGUI, "You rolled a double!! You get to get out of jail fo free!");
                    else {
                        message.append("You did not roll a double!! You cannot get out of jail fo free!");
                        infoDisplayGUI.setNextEnabled(true);
                        infoDisplayGUI.setRollEnabled(false);
                    }
                }

                // refresh location, buy, rent and cash.
                //start here
                infoDisplayGUI.setCurrentLocation(currentLocation.getName());

                setPriceInfo(currentLocation, player);

                if (currentLocation instanceof PropertySquare location) {
                    Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();

                    if (owner != null && owner != player) {
                        message.append(player.getName()).append(" just paid ").append(owner.getName()).append(" $").append(location.getRentFee()).append(" as rent fee\n");
                    }
                } else {
                    if (currentLocation instanceof TaxSquare location)
                        message.append(player.getName() + " paid " + player.getCurrentLocation().getName());
                    else
                        message.append(player.getName() + " is on Square " + player.getCurrentLocation().getName());
                }

                setButtons(player);

                infoDisplayGUI.setNextEnabled(true);
                infoDisplayGUI.setRollEnabled(false);
                squareGUI.setMessage(message.toString());
            }
            case "Bankrupt" -> {
                int result = JOptionPane.showConfirmDialog(squareGUI, "You are Bankrupt!! Do you want to sell your properties and stay in the game?");
                switch (result) {
                    case JOptionPane.YES_OPTION -> this.getSellDecision(player.getProperties(), player);
                    case JOptionPane.NO_OPTION, JOptionPane.CLOSED_OPTION, JOptionPane.CANCEL_OPTION -> {
                        squareGUI.removePlayerGUILocation(player, currentLocation.getNumber());
                        game.removeBankruptPlayer(player);
                    }
                }

                infoDisplayGUI.setBuyEnabled(false);
                infoDisplayGUI.setSellEnabled(false);
                infoDisplayGUI.setNextEnabled(true);
                infoDisplayGUI.setRollEnabled(false);
            }
            case "Doubles" -> {
                //set dice value
                int[] diceValues = MonopolyGame.dice.getDice();
                squareGUI.setDiceImages(diceValues[0], diceValues[1]);

                if (player.isInJail()) {
                    JOptionPane.showMessageDialog(squareGUI, "You rolled a double!! You are going out of jail.");

                    //setting player out of jail
                    MonopolyBoard.jail.goOutJail(player);
                    squareGUI.changePlayerGUILocation(player, 8, 8);
                    infoDisplayGUI.setNextEnabled(true);
                    infoDisplayGUI.setRollEnabled(false);
                } else {

                    if (game.getDoubleCounter() == 2) {
                        JOptionPane.showMessageDialog(squareGUI, "You rolled a double 3 times! You are going to jail");
                        JailSquare jail = (JailSquare) MonopolyGame.board.getSquareAt(8);
                        jail.goJail(player);
                        squareGUI.changePlayerGUILocation(player, currentLocation.getNumber(), 8);
                        infoDisplayGUI.setNextEnabled(true);
                        infoDisplayGUI.setRollEnabled(false);
                    } else {
                        if (player instanceof AIPlayer ai)
                            JOptionPane.showMessageDialog(squareGUI, "AI rolled a double!!Click ok to continue.");
                        else
                            JOptionPane.showMessageDialog(squareGUI, "You rolled a double!!Roll Dice again.");
                        infoDisplayGUI.setNextEnabled(false);
                        infoDisplayGUI.setRollEnabled(true);
                    }
                }
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
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
    }

    /**
     * popup a window and ask player to select a property to sell
     */
    public void getSellDecision(ArrayList<PropertySquare> p, Player player) {
        JFrame popup = new JFrame("Select a property to sell");

        popup.setBounds(500, 400, 740, 120);
        popup.setLayout(new GridLayout());
        JButton btn;
        for (PropertySquare property : p) {
            btn = new JButton(property.getName());
            btn.setBackground(property.getColor());
            btn.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                player.sellProperty(player.getPropertyFromName(b.getText()));
                infoDisplayGUI.setCash(player.getCash());
                infoDisplayGUI.setPropertyList(player.getProperties().toString());
                popup.getContentPane().remove(b);
                popup.getContentPane().revalidate();
                popup.getContentPane().repaint();
                if (popup.getContentPane().getComponents().length == 0) {
                    if (player.isBankrupt()) {
                        JOptionPane.showMessageDialog(squareGUI, "You are still Bankrupt!! Unfortunately you can't stay in the game");
                        squareGUI.removePlayerGUILocation(player, player.getCurrentLocation().getNumber());
                        game.removeBankruptPlayer(player);
                    }
                    popup.dispose();
                }
            });
            popup.add(btn);
        }
        popup.setVisible(true);
        popup.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (player.isBankrupt()) {
                    JOptionPane.showMessageDialog(squareGUI, "You are still Bankrupt!! Unfortunately you can't stay in the game");
                    squareGUI.removePlayerGUILocation(player, player.getCurrentLocation().getNumber());
                    game.removeBankruptPlayer(player);
                }
            }
        });
    }

    /**
     * popup a window and ask player to select a property to build or sell buildings
     */
    public void getDecision(ArrayList<PropertySquare> p, MonopolyGame game) {
        JFrame popup = new JFrame("Select a property");
        popup.setBounds(500, 400, 640, 120);
        GridLayout grid = new GridLayout(0, 4);
        popup.setLayout(grid);
        JButton btn;
        for (PropertySquare property : p) {
            btn = new JButton(property.getName());
            btn.setBackground(property.getColor());
            btn.addActionListener(e -> {
                JButton b = (JButton) e.getSource();
                selectedPropertyIndex = game.setSelectedProperty(b.getText());
                popup.dispose();
            });
            popup.add(btn);
        }
        popup.setVisible(true);
    }

    /**
     * popup a window and ask player to decide whether to build/sell a house or a hotel
     * on the selected property
     */
    public void HotelOrHouse(Player player, String command) {
        JFrame popup = new JFrame("House or Hotel");
        popup.setBounds(500, 400, 160, 120);
        GridLayout grid = new GridLayout(1, 2);
        popup.setLayout(grid);
        JButton house = new JButton("House"), hotel = new JButton("Hotel");

        ActionListener al = e -> {

            String temp = "", decision = ((JButton) e.getSource()).getText();
            int price = sellBuildBuilding(command, decision, player);
            if (command.equals("build")) {
                temp = "build";
                if (player.getCash() < player.getSelectedSquare().getHousePrice()) {
                    popup.dispose();
                }
                if (player.getSelectedSquare().hasHotel()) {
                    popup.dispose();
                }
            } else {
                temp = "sell";
                hotel.setEnabled(player.getSelectedSquare().hasHotel());
                house.setEnabled(!player.getSelectedSquare().hasHotel());
                if (!player.getSelectedSquare().hasHouses()) {
                    popup.dispose();
                }
            }
            infoDisplayGUI.setCash(player.getCash());
            if (price >= 0) {
                setMessage(player.getName() + " has " + temp + " a " + decision + " on "
                        + player.getSelectedSquare().getName() + "\n" + player.getName() +
                        " paid $" + price + " to build it\n");
            } else if (price == -1) {
                setMessage(player.getName() + " cannot " + temp + " on " +
                        player.getSelectedSquare().getName() + "\n");
            } else {
                setMessage(null);
            }
            if (!player.hasBuilding().isEmpty()) {
                infoDisplayGUI.setSellHEnabled(true);
            }
            squareGUI.setMessage(message);
            setMessage(null);
        };
        house.addActionListener(al);
        hotel.addActionListener(al);
        popup.add(house);
        popup.add(hotel);

        if (command.equals("build")) {
            if (player.getCash() < player.getSelectedSquare().getHotelPrice()) {
                hotel.setEnabled(false);
            }
            house.setEnabled(true);
        } else {
            hotel.setEnabled(player.getSelectedSquare().hasHotel());
            house.setEnabled(!player.getSelectedSquare().hasHotel());
        }

        popup.setVisible(true);
    }

    private void setMessage(String m) {
        this.message = m;
    }

    /**
     * set build, sellH and sell buttons
     */
    private void setButtons(Player player) {
        infoDisplayGUI.setBuildEnabled(!player.getAvailableProperties(player.removeRailroadUtility(player.hasWholeSet())).isEmpty());
        infoDisplayGUI.setSellHEnabled(!player.hasBuilding().isEmpty());
        infoDisplayGUI.setSellEnabled(!player.getProperties().isEmpty());
    }

    /**
     * refresh players information
     */
    private void setPriceInfo(Square currentLocation, Player player) {
        if (currentLocation instanceof PropertySquare location) {
            Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();

            if (owner == null) {
                infoDisplayGUI.setBuyEnabled(player.getCash() >= location.getPrice());
                infoDisplayGUI.setBuyPrice(location.getPrice());
                infoDisplayGUI.setHousePrice(location.getHousePrice());
                infoDisplayGUI.setHotelPrice(location.getHotelPrice());
            } else {
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
            infoDisplayGUI.setBuyEnabled(false);
        }
        infoDisplayGUI.setCash(player.getCash());
    }

    /**
     * a part of action listener in HouseOrHotel()
     * build or sell houses or hotels on GUI map
     */
    public int sellBuildBuilding(String command, String decision, Player player) {
        int price = -1;
        if (command.equals("build")) {
            price = player.buildH(decision);
            if (decision.equals("House")) {
                for (int i = 1; i < 5; i++) {
                    if (((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).isBuilding(i)) {
                        ((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).setBuildingX(Color.GREEN, i);
                        break;
                    }
                }
            } else if (decision.equals("Hotel")) {
                for (int i = 1; i < 5; i++) {
                    if (((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).isBuilding(i)) {
                        ((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).setBuildingX(Color.GREEN, i);
                    }
                }
                ((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).setBuildingX(Color.RED, 5);
            }
        } else if (command.equals("sellH")) {
            price = player.sellH(decision);
            if (decision.equals("House")) {
                for (int i = 4; i > 0; i--) {
                    if (!(((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).isBuilding(i))) {
                        ((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).setBuildingX(Color.WHITE, i);
                        break;
                    }
                }
            } else if (decision.equals("Hotel")) {
                ((PropertySquareGUI) squareGUI.getPropertySquareGUI(selectedPropertyIndex)).setBuildingX(Color.WHITE, 5);
            }

        }
        return price;
    }
}

