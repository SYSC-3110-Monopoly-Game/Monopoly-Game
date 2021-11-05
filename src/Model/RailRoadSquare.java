package Model;

import view.RailRoadSquareGUI;

import java.awt.Color;
import java.util.HashMap;

public class RailRoadSquare extends PropertySquare {

    public RailRoadSquareGUI gui;

    public RailRoadSquare(String name, int number, int buy, int rent, Color color) {
        super(name, number, buy, rent, color);
        this.gui = new RailRoadSquareGUI(buy);
    }
}


