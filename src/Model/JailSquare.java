package Model;

import java.awt.Color;
import java.util.HashMap;

public class JailSquare extends Square {

    private HashMap<Player, Integer> map;

    public JailSquare(String name,int number) {
        super(name,number);
    }

    /**
     * Adds 1 to the player's counter
     *
     * @param p
     */
    public void addCounter(Player p) {
        map.put(p, map.get(p) + 1);
    }

    /** Adds player to jail hashmap
     *
     * @param p
     */
    @Override
    public void landOn(Player p) {
        p.setLocation(this);
        map.put(p, 1);
    }

    /** Player is passing by and is not in jail
     *
     * @param p
     */
    public void passingBy(Player p){
        p.setLocation(this);
    }
}
