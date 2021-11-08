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
    private DiceGUI diceGUI;

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

        //infoPanel gui initialization
        this.infoDisplayGUI = new InfoDisplayGUI(this.game.getPlayerInTurn());
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
            infoDisplayGUI.setCurrentLocation(player.getCurrentLocation().getName());
            if (player.getCurrentLocation() instanceof PropertySquare) {
                PropertySquare location = (PropertySquare) player.getCurrentLocation();
                infoDisplayGUI.setBuyPrice(location.getPrice());
                infoDisplayGUI.setRentPrice(location.getRentFee());
            }
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(false);
            infoDisplayGUI.setNextEnabled(false);
            infoDisplayGUI.setRollEnabled(true);

        } else if (command.equals("Buy")) {
            infoDisplayGUI.setPropertyList(player.getProperties().toString());
            infoDisplayGUI.setBuyEnabled(false);
            infoDisplayGUI.setSellEnabled(false);

        } else if (command.equals("Roll Dice")) {
            Square newLocation = player.getCurrentLocation();
            Square lastLocation = player.getLastLocation();

            //remove player gui form last location and add to new location
            squareGUI.changePlayerGUILocation(player, lastLocation.getNumber(), newLocation.getNumber());

            //set dice value
            int diceValues[] = game.dice.getDice();
            diceGUI.setDiceImages(diceValues[0], diceValues[1]);

            //enable or disable appropriate buttons
            infoDisplayGUI.setBuyEnabled(true);
            infoDisplayGUI.setSellEnabled(true);
            infoDisplayGUI.setNextEnabled(true);
            infoDisplayGUI.setRollEnabled(false);

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

