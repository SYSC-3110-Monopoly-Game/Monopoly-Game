package view;

import Controller.*;
import Model.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class MonopolyGameGUI extends JFrame {
    private final InfoDisplayGUI infoDisplayGUI;
    private final SquareGridGUI squareGUI;
    private final MonopolyGame game;
    private String message;

    /**
     * Initialize the gui frame
     */
    public MonopolyGameGUI(MonopolyGame game) {
        this.message = "";
        this.game = game;

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

        //load/export gui
        JPanel lAndE = new JPanel(new GridLayout(1, 2));
        JButton exportCurrentGame = new JButton("Export Current Game");
        exportCurrentGame.addActionListener(e -> this.game.exportGameToXML("SavedGame.xml"));
        lAndE.add(exportCurrentGame);
        this.add(lAndE, BorderLayout.PAGE_START);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //subscribe to game
        this.game.addView(this);

    }

    /**
     * add action listener to buttons in JPanel: SquareGridGUI
     */
    public void setController(MonopolyGameController controller) {
        this.infoDisplayGUI.setButtonControllers(controller);
    }

    public void loadGameGUI(MonopolyBoard board, ArrayList<Player> players, Player player) {
        squareGUI.loadSquaresOnMap(board);
        ArrayList<Player> playerNotInTurn = new ArrayList<>(players);
        playerNotInTurn.remove(player);
        infoDisplayGUI.setPlayersInfo(playerNotInTurn, player);
    }

    /**
     * apply change to gui according to the command
     */
    public void handleUpdate(Player player, Enums command, ArrayList<Player> players) {
        //information about player used
        Square currentLocation = player.getCurrentLocation();
        Square lastLocation = player.getLastLocation();
        switch (command) {
            case NEXT_TURN -> {
                handleNextTurn(currentLocation, player, players);
                break;
            }
            case BUY -> {
                handleBuy(player, players);
                break;
            }
            case SELL -> {
                handleSell(player, players);
                break;
            }
            case ROLL_DICE -> {
                handleRollDice(currentLocation, lastLocation, player, players);
                break;
            }
            case BANKRUPT -> {
                handleBankrupt(currentLocation, player);
                break;
            }
            case NO_DOUBLES -> {
                handleNoDoubles(player);
                break;
            }
            case DOUBLES -> {
                handleDoubles(currentLocation, player);
                break;
            }
            case WINNER -> {
                handleWinner(lastLocation, player);
                break;
            }
            case BUILD, SELLH -> {
                HotelOrHouse(player, command);
                break;
            }
            default -> throw new IllegalStateException("Unexpected value: " + command);
        }
    }


    public void handleNextTurn(Square currentLocation, Player player, ArrayList<Player> players) {
        if (currentLocation instanceof PropertySquare square && square.getOwner() == player)
            squareGUI.setMessage("You own this property!");
        else
            squareGUI.setMessage("");

        infoDisplayGUI.setPlayersInfo(players, player);

        infoDisplayGUI.setBuyEnabled(false);
        infoDisplayGUI.setNextEnabled(false);
        infoDisplayGUI.setRollEnabled(true);
    }

    public void handleBuy(Player player, ArrayList<Player> players) {
        ArrayList<PropertySquare> property = player.getProperties();
        squareGUI.addMessage(player.getName() +
                " just bought " + property.get(property.size() - 1).getName() +
                "[" + property.get(property.size() - 1).getNumber() + "]\n");

        infoDisplayGUI.setPlayersInfo(players, player);

        infoDisplayGUI.setBuyEnabled(false);
    }

    public void handleSell(Player player, ArrayList<Player> players) {
        infoDisplayGUI.setPlayersInfo(players, player);

        ArrayList<PropertySquare> PropertiesCanBeSell = player.getProperties();
        PropertiesCanBeSell.remove(player.hasBuilding());
        this.getSellDecision(PropertiesCanBeSell, player);
    }

    public void handleRollDice(Square currentLocation, Square lastLocation, Player player, ArrayList<Player> players) {

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
        infoDisplayGUI.setPlayersInfo(players, player);

        if (currentLocation instanceof PropertySquare location) {
            Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();

            if (owner != null && owner != player) {
                message.append(player.getName()).append(" just paid ").append(owner.getName()).append(" $").append(location.getRentFee()).append(" as rent fee\n");
            }
        } else {
            if (currentLocation instanceof TaxSquare location)
                message.append(player.getName()).append(" paid ").append(location.getName());
            else
                message.append(player.getName()).append(" is on Square ").append(player.getCurrentLocation().getName());
        }

        infoDisplayGUI.setNextEnabled(true);
        infoDisplayGUI.setRollEnabled(false);
        squareGUI.setMessage(message.toString());
    }

    public void handleBankrupt(Square currentLocation, Player player) {
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

    public void handleNoDoubles(Player player) {
        JOptionPane.showMessageDialog(squareGUI, "This is your 3rd rounds in jail, you can leave the jail next round");
        squareGUI.changePlayerGUILocation(player, 8, 8);
        infoDisplayGUI.setNextEnabled(true);
        infoDisplayGUI.setRollEnabled(false);
    }

    public void handleDoubles(Square currentLocation, Player player) {
        //set dice value
        int[] diceValues = MonopolyGame.dice.getDice();
        squareGUI.setDiceImages(diceValues[0], diceValues[1]);

        if (player.isInJail()) {
            if (game.getDoubleCounter() == 1) {
                JOptionPane.showMessageDialog(squareGUI, "You rolled a double!! You are going out of jail.");

                //setting player out of jail
                MonopolyBoard.jail.goOutJail(player);
                squareGUI.changePlayerGUILocation(player, 8, 8);
                infoDisplayGUI.setNextEnabled(true);
                infoDisplayGUI.setRollEnabled(false);
            }
        } else {
            if (game.getDoubleCounter() == 3) {
                JOptionPane.showMessageDialog(squareGUI, "You rolled a double 3 times! You are going to jail");
                MonopolyBoard.jail.goJail(player);
                squareGUI.changePlayerGUILocation(player, currentLocation.getNumber(), 8);
                infoDisplayGUI.setNextEnabled(true);
                infoDisplayGUI.setRollEnabled(false);
            } else {
                if (player instanceof AIPlayer) {
                    JOptionPane.showMessageDialog(squareGUI, "AI rolled a double " + game.getDoubleCounter() + "times" + "!!Click ok to continue.");
                } else
                    JOptionPane.showMessageDialog(squareGUI, "You rolled a double!!Roll Dice again.");
                infoDisplayGUI.setNextEnabled(false);
                infoDisplayGUI.setRollEnabled(true);
            }
        }
    }

    public void handleWinner(Square lastLocation, Player player) {
        squareGUI.removePlayerGUILocation(player, lastLocation.getNumber());
        JOptionPane.showMessageDialog(this, "We have a Winner!! Player " + player.getName());
        infoDisplayGUI.setBuyEnabled(false);
        infoDisplayGUI.setSellEnabled(false);
        infoDisplayGUI.setNextEnabled(false);
        infoDisplayGUI.setRollEnabled(false);
        infoDisplayGUI.setBuildEnabled(false);
        infoDisplayGUI.setSellHEnabled(false);
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
            btn = new JButton("<html>" + property.getName() + "<br>Sell Price: " + property.getPrice() / 2 + "</html>");
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
                        infoDisplayGUI.setPropertyList("");
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
                    infoDisplayGUI.setPropertyList("");
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
                try {
                    game.setSelectedProperty(b.getText());
                } catch (IOException | SAXException | ParserConfigurationException ioException) {
                    ioException.printStackTrace();
                }
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
    public void HotelOrHouse(Player player, Enums command) {
        JFrame popup = new JFrame("House or Hotel");
        popup.setBounds(500, 400, 160, 120);
        GridLayout grid = new GridLayout(1, 2);
        popup.setLayout(grid);
        JButton house = new JButton(Enums.HOUSE.toString()), hotel = new JButton(Enums.HOTEL.toString());

        ActionListener al = e -> {
            String message;
            String temp;
            Enums decision = null;
            if (((JButton) e.getSource()).getText().equals(Enums.HOTEL.toString())) decision = Enums.HOTEL;
            else if (((JButton) e.getSource()).getText().equals(Enums.HOUSE.toString())) decision = Enums.HOUSE;

            int price = sellBuildBuilding(command, decision, player);
            if (command == Enums.BUILD) {
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
                message = player.getName() + " has " + temp + " a " + decision + " on "
                        + player.getSelectedSquare().getName() + "\n" + player.getName() +
                        " paid $" + price + " to build it\n";
            } else if (price == -1) {
                message = player.getName() + " cannot " + temp + " on " +
                        player.getSelectedSquare().getName() + "\n";
            } else {
                message = "";
            }
            if (!player.hasBuilding().isEmpty()) {
                infoDisplayGUI.setSellHEnabled(true);
            }
            squareGUI.setMessage(message);
        };
        house.addActionListener(al);
        hotel.addActionListener(al);
        popup.add(house);
        popup.add(hotel);

        if (command == Enums.BUILD) {
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

    /**
     * a part of action listener in HouseOrHotel()
     * build or sell houses or hotels on GUI map
     */
    public int sellBuildBuilding(Enums command, Enums decision, Player player) {
        int price;
        if (command == Enums.BUILD) {
            price = player.buildH(decision);
        } else {
            price = player.sellH(decision);
        }

        int index = player.getSelectedSquare().getNumber();
        squareGUI.buildSellBuilding(command, decision, index);
        return price;
    }
}

