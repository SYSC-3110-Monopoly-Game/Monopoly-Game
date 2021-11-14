package view;

import Controller.MonopolyGameController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyGameGUI extends JFrame {
    private final InfoDisplayGUI infoDisplayGUI;
    private final SquareGridGUI squareGUI;
    private final DiceGUI diceGUI;

    //TODO requires a square array like Square[] squares
    public MonopolyGameGUI(MonopolyGame game) {
        //subscribe to game

        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1400, 670));

        this.setLayout(new BorderLayout());

        //pass the squares to the view
        Square[] squares = MonopolyGame.board.getSquares();
        ArrayList<Player> players = game.players;

        squareGUI = new SquareGridGUI(squares, players);
        this.add(squareGUI, BorderLayout.WEST);

        //infoPanel gui initialization
        this.infoDisplayGUI = new InfoDisplayGUI(game.getPlayerInTurn());
        this.add(infoDisplayGUI, BorderLayout.EAST);

        //dice gui
        diceGUI= new DiceGUI();
        squareGUI.addDiceGUI(diceGUI);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setController(MonopolyGameController controller) {
        this.infoDisplayGUI.setButtonControllers(controller);
    }

    public void handleUpdate(Player player, String command) {
        if (command.equals("Next Turn")) {
            //update the info panel with the current player info
            infoDisplayGUI.setName(player.getName());
            infoDisplayGUI.setCash(player.getCash());
            infoDisplayGUI.setPropertyList(player.getProperties().toString());

            // refresh location, buy, rent.
            //start here
            infoDisplayGUI.setCurrentLocation(player.getCurrentLocation().getName());
            if (player.getCurrentLocation() instanceof PropertySquare location) {
                Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();
                if (owner == null) infoDisplayGUI.setBuyPrice(location.getPrice());
                else infoDisplayGUI.setBuyPrice(-2);
                infoDisplayGUI.setRentPrice(location.getRentFee());
            } else {
                infoDisplayGUI.setBuyPrice(-1);
                infoDisplayGUI.setRentPrice(-1);
            }
            //ends here

            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(true);
            infoDisplayGUI.setNextEnabled(false);
            infoDisplayGUI.setRollEnabled(true);
            squareGUI.setMessage("");

        }
        else if (command.equals("Buy")) {

            ArrayList<PropertySquare> property = player.getProperties();

            infoDisplayGUI.setPropertyList(property.toString());
            infoDisplayGUI.setCash(player.getCash());
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(true);


            squareGUI.addMessage(player.getName() +
                    " just bought " + property.get(property.size() - 1).getName() +
                    "[" + property.get(property.size() - 1).getNumber() + "]\n");

        }
        else if (command.equals("Sell")) {
            infoDisplayGUI.setPropertyList(player.getProperties().toString());
            infoDisplayGUI.setCash(player.getCash());

        }
        else if (command.equals("Roll Dice")) {
            Square newLocation = player.getCurrentLocation();
            Square lastLocation = player.getLastLocation();
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
            if(newLocation instanceof PropertySquare) {
                PropertySquare location = (PropertySquare) player.getCurrentLocation();
                Player owner = ((PropertySquare) player.getCurrentLocation()).getOwner();
                if (owner == null) {
                    infoDisplayGUI.setBuyPrice(location.getPrice());
                    infoDisplayGUI.setBuyEnabled(true);
                }
                else {
                    infoDisplayGUI.setBuyPrice(-2);
                    if (owner != player) {
                        message.append(player.getName()).append(" just paid ").append(owner.getName()).append("       ").append(location.getRentFee()).append(" as rent fee\n");
                    }
                    infoDisplayGUI.setBuyEnabled(false);
                }
                infoDisplayGUI.setRentPrice(location.getRentFee());
            } else {
                infoDisplayGUI.setBuyPrice(-1);
                infoDisplayGUI.setRentPrice(-1);
                infoDisplayGUI.setBuyEnabled(false);
                message.append(player.getName()).append(player.getCurrentLocation().message);
            }
            infoDisplayGUI.setCash(player.getCash());
            //ends here

            //enable or disable appropriate buttons
            infoDisplayGUI.setSellEnabled(true);
            infoDisplayGUI.setNextEnabled(true);
            infoDisplayGUI.setRollEnabled(false);
            squareGUI.setMessage(message.toString());

        }
        else if (command.equals("Bankrupt")) {
            Square newLocation = player.getLastLocation();
            squareGUI.removePlayerGUILocation(player, newLocation.getNumber());
            JOptionPane.showMessageDialog(this, "Player Bankrupt " + player.getName());
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(false);
            infoDisplayGUI.setNextEnabled(true);
            infoDisplayGUI.setRollEnabled(false);

        }
        else if (command.equals("Winner")) {
            Square newLocation = player.getLastLocation();
            squareGUI.removePlayerGUILocation(player, newLocation.getNumber());
            JOptionPane.showMessageDialog(this, "We have a Winner!! Player " + player.getName());
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(false);
            infoDisplayGUI.setNextEnabled(false);
            infoDisplayGUI.setRollEnabled(false);
        }
    }
}

