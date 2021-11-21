package Model;
import java.util.HashMap;


public class JailSquare extends Square {

    public HashMap<Player, Integer> map;
    private final int jailFee;



    public JailSquare(String name, int number, int jailFee) {
        super(name,number);
        this.map = new HashMap<>();
        this.jailFee = jailFee;
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
        p.setCurrentLocation(this);
        System.out.println("You are visiting jail");
        message = " is visiting jail\n";
    }

    /** Adds player to jail hashmap
     *
     * @param p
     */
    public void goJail(Player p) {
        p.setCurrentLocation(this);
        this.map.put(p, 0);
        message = " is in jail\n";
    }

    public void goOutJail(Player p) {

        this.map.remove(p);
        this.landOn(p);
        p.setInJail(false);
        message = " is out of jail\n";
    }
    public int getJailFee(){
        return this.jailFee;
    }

    public HashMap<Player, Integer> getMap() {
        return this.map;
    }

}
