package view;

import Controller.MonopolyGameController;
import Model.MonopolyBoard;
import Model.MonopolyGame;
import Model.Square;

import javax.swing.*;
import java.awt.*;

public class MonopolyGameGUI extends JFrame {
    private MonopolyGame game;

//TODO requires a square array like Square[] squares
    public MonopolyGameGUI(MonopolyGame game) {
        //subscribe to game
        this.game=game;
        this.game.addView();

        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1500, 670));

        this.setLayout(new BorderLayout());

        //TODO get the square array properly from constructor
        //MonopolyGame game = new MonopolyGame();
        Square[] squares = new Square[33];
        SquareGridGUI square = new SquareGridGUI(squares);
        this.add(square, BorderLayout.WEST);

        InfoDisplayGUI infoDisplayGUI = new InfoDisplayGUI();
        this.add(infoDisplayGUI, BorderLayout.EAST);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
