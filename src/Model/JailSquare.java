package Model;

import java.awt.Color;
import java.util.HashMap;

public class JailSquare extends Square {

    private HashMap<Player, Integer> map;

    public JailSquare(String name,int number) {
        super(name,number);
        map = new HashMap<>();
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
    }

    /** Adds player to jail hashmap
     *
     * @param p
     */
    public void goJail(Player p) {
        p.setLocation(this);
        map.put(p, 0);
    }

    public HashMap<Player, Integer> getMap() {
        return map;
    }
}
