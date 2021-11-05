package Model;

import view.JailSquareGUI;

import java.awt.Color;
import java.util.HashMap;

public class JailSquare extends Square {

    private HashMap<Player, Integer> map;
    public JailSquareGUI gui;

    public JailSquare(String name,int number) {
        super(name,number);
        this.gui = new JailSquareGUI();
        this.map = new HashMap<>();
    }

    /**
     * Adds 1 to the player's counter
     *
     * @param p
     */
    public void addCounter(Player p) {
        map.put(p, map.get(p) + 1);
    }

    /** Player is passing by and is not in jail
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setLocation(this);
        this.gui.addPlayer(p.getName());
    }

    /** Adds player to jail hashmap
     *
     * @param p
     */
    public void goJail(Player p) {
        p.setLocation(this);
        this.gui.addPlayerToJail(p.getName());
        map.put(p, 0);
    }

    public void goOutJail(Player p) {
        this.gui.removePlayer(p.getName(), true);
        map.remove(p);
    }

    public HashMap<Player, Integer> getMap() {
        return map;
    }

    @Override
    public void landOff(Player p) {
        this.gui.removePlayer(p.getName(), false);
    }
}
