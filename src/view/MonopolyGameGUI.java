package view;

import Controller.MonopolyGameController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyGameGUI extends JFrame {
    private MonopolyGame game;
    private InfoDisplayGUI infoDisplayGUI;
    private SquareGridGUI squareGUI;

    //TODO requires a square array like Square[] squares
    public MonopolyGameGUI(MonopolyGame game) {
        //subscribe to game
        this.game = game;

        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1400, 670));

        this.setLayout(new BorderLayout());

        //pass the squares to the view
        Square[] squares = this.game.board.getSquares();
        ArrayList<Player> players = this.game.players;

        squareGUI = new SquareGridGUI(squares, players);
        this.add(squareGUI, BorderLayout.WEST);

        this.infoDisplayGUI = new InfoDisplayGUI(this.game.getPlayerInTurn());
        this.add(infoDisplayGUI, BorderLayout.EAST);

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
            if (player.getCurrentLocation() instanceof PropertySquare) {
                PropertySquare location = (PropertySquare) player.getCurrentLocation();
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

        } else if (command.equals("Buy")) {

            ArrayList<PropertySquare> property = player.getProperties();

            infoDisplayGUI.setPropertyList(property.toString());
            infoDisplayGUI.setCash(player.getCash());
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(true);


            squareGUI.addMessage(player.getName() +
                    " just bought " + property.get(property.size() - 1).getName() +
                    "[" + property.get(property.size() - 1).getNumber() + "]\n");

        } else if (command.equals("Sell")) {
            infoDisplayGUI.setPropertyList(player.getProperties().toString());
            infoDisplayGUI.setCash(player.getCash());

        } else if (command.equals("Roll Dice")) {

            /*DiceGUI dice = new DiceGUI(game.dice);

            GridBagConstraints c = new GridBagConstraints();
            c.weightx = 0.5;
            c.insets = new Insets(1, 0, 1, 0);  //top padding
            c.gridx = 4;
            c.gridy = 3;
            c.gridwidth = 2;
            c.gridheight = 2;

            squareGUI.add(dice, c);*/

            Square newLocation = player.getCurrentLocation();
            Square lastLocation = player.getLastLocation();
            StringBuffer message = new StringBuffer(player.getName() +
                    " move from " + lastLocation.getName() + "[" +
                    lastLocation.getNumber() + "]   to   "
                    + newLocation.getName() + "["
                    + newLocation.getNumber() + "]\n");

            //remove player gui form last location and add to new location
            squareGUI.changePlayerGUILocation(player, lastLocation.getNumber(), newLocation.getNumber());

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
                        message.append(player.getName() + " just paid " + owner.getName() +
                        "       " + location.getRentFee() + " as rent fee\n");
                    }
                    infoDisplayGUI.setBuyEnabled(false);
                }
                infoDisplayGUI.setRentPrice(location.getRentFee());
            } else {
                infoDisplayGUI.setBuyPrice(-1);
                infoDisplayGUI.setRentPrice(-1);
                infoDisplayGUI.setBuyEnabled(false);
                message.append(player.getName() + player.getCurrentLocation().message);
            }
            infoDisplayGUI.setCash(player.getCash());
            //ends here

            //enable or disable appropriate buttons
            infoDisplayGUI.setSellEnabled(true);
            infoDisplayGUI.setNextEnabled(true);
            infoDisplayGUI.setRollEnabled(false);
            squareGUI.setMessage(message.toString());
            //squareGUI.add

        } else if (command.equals("Bankrupt")) {
            Square newLocation = player.getLastLocation();
            squareGUI.removePlayerGUILocation(player, newLocation.getNumber());
            JOptionPane.showMessageDialog(this, "Player Bankrupt " + player.getName());
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(false);
            infoDisplayGUI.setNextEnabled(true);
            infoDisplayGUI.setRollEnabled(false);

        } else if (command.equals("Winner")) {
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

