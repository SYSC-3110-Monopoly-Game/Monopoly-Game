package view;

import Model.Square;

import javax.swing.*;
import java.awt.*;

public class FrameGUI extends JFrame {

    private JPanel boardPanel;
    private JPanel infoDisplayPanel;

//TODO requires a square array like Square[] squares
    public FrameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 800));

        this.setLayout(new BorderLayout(10,10));
        this.createPanels();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createPanels() {
        //TODO get the square array properly
        Square[] squares = new Square[33];
        SquareGridGUI square = new SquareGridGUI(squares);
        this.add(square, BorderLayout.WEST);

        InfoDisplayGUI infoDisplayGUI = new InfoDisplayGUI();
        infoDisplayGUI.setPreferredSize(new Dimension(400, 600));
        this.add(infoDisplayGUI, BorderLayout.EAST);


    }




}
