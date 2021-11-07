package view;

import Controller.MonopolyGameController;
import Model.MonopolyBoard;
import Model.MonopolyGame;
import Model.Square;

import javax.swing.*;
import java.awt.*;

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
        SquareGridGUI square = new SquareGridGUI(squares);
        this.add(square, BorderLayout.WEST);

        this.infoDisplayGUI = new InfoDisplayGUI();
        this.add(infoDisplayGUI, BorderLayout.EAST);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setController(MonopolyGameController controller){
        this.infoDisplayGUI.setButtonControllers(controller);
    }

    public void handleUpdate() {
        
    }
}
