package view;

import Controller.MonopolyGameController;
import Model.MonopolyBoard;
import Model.MonopolyGame;
import Model.Player;
import Model.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonopolyGameGUI extends JFrame {
    private MonopolyGame game;
    private InfoDisplayGUI infoDisplayGUI;

//TODO requires a square array like Square[] squares
    public MonopolyGameGUI(MonopolyGame game) {
        //subscribe to game
        this.game=game;

        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1400, 670));

        this.setLayout(new BorderLayout());

        //pass the squares to the view
        Square[] squares =this.game.getBoard().getSquares();
        ArrayList<Player> players = this.game.players;
        SquareGridGUI square = new SquareGridGUI(squares,players);
        this.add(square, BorderLayout.WEST);

        this.infoDisplayGUI = new InfoDisplayGUI(this.game.getPlayerInTurn());
        this.add(infoDisplayGUI, BorderLayout.EAST);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setController(MonopolyGameController controller){
        this.infoDisplayGUI.setButtonControllers(controller);
    }

    public void handleUpdate(Player player, Square nextLocation) {



        infoDisplayGUI.setCash(player.getCash());
        infoDisplayGUI.setPropertyList(player.getProperties().toString());
        infoDisplayGUI.setCurrentLocation(player.getLocation().getName());
        infoDisplayGUI.setName(player.getName());


        //if nextLocation
        //edit player info in the info display
        //delete player icon from the current square view
        //add player icon in
    }
}
