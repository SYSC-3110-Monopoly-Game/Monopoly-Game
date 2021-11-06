package view;

import Model.MonopolyBoard;
import Model.MonopolyGame;
import Model.Square;

import javax.swing.*;
import java.awt.*;

public class FrameGUI extends JFrame {

//TODO requires a square array like Square[] squares
    public FrameGUI() {
        //adding frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1700, 1000));

        this.setLayout(new BorderLayout(10,10));

        //TODO get the square array properly from constructor
        //MonopolyGame game = new MonopolyGame();
        Square[] squares = new Square[32];
        SquareGridGUI square = new SquareGridGUI(squares);
        this.add(square, BorderLayout.WEST);

        InfoDisplayGUI infoDisplayGUI = new InfoDisplayGUI();
        this.add(infoDisplayGUI, BorderLayout.EAST);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
