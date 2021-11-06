package view;

import javax.swing.*;

public abstract class SquareGUI extends JPanel {
    public abstract void addPlayer(String name);

    public abstract void removePlayer(String name, boolean inJail);
}
