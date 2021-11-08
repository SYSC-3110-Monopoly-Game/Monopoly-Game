package Model;

import view.JailSquareGUI;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class JailSquare extends Square {

    private HashMap<Player, Integer> map;

    public JailSquare(String name,int number) {
        super(name,number);
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
        System.out.println("You are visiting jail");
    }

    /** Adds player to jail hashmap
     *
     * @param p
     */
    public void goJail(Player p) {
        p.setLocation(this);
        this.map.put(p, 0);
    }

    public void goOutJail(Player p) {
        this.map.remove(p);
    }

    /** increment all counters for players in jail
     * */
    public void IncrementJail() {
        if(!map.isEmpty()){
            for(Map.Entry<Player, Integer> entry : map.entrySet()) {
                entry.setValue(entry.getValue() + 1);
            }
        }
    }

    public HashMap<Player, Integer> getMap() {
        return map;
    }
}
