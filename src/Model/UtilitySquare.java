package Model;

import view.UtilitySquareGUI;

import java.awt.Color;
public class UtilitySquare extends PropertySquare{

    public UtilitySquareGUI gui;

    public UtilitySquare(String name, int number, int buy, int rent, Color color) {
        super(name, number, buy, rent, color);
        this.gui = new UtilitySquareGUI(buy, name);
    }
}
