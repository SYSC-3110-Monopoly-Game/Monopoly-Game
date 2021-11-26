package Model;
import java.util.HashMap;


public class JailSquare extends Square {

    public HashMap<Player, Integer> map;
    public HashMap<String, Integer> stringMap;
    private final int jailFee;

    public JailSquare(String name, int number, int jailFee) {
        super(name,number);
        this.map = new HashMap<>();
        this.jailFee = jailFee;
    }

    public JailSquare(String name, int number, int jailFee, HashMap<String, Integer> map) {
        super(name,number);
        this.map = new HashMap<>();
        this.jailFee = jailFee;
        this.stringMap = map;
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
        p.setInJail(true);
        this.map.put(p, 0);
        message = " is in jail\n";
    }

    /**
     * remove player from the jail hashmap
     */
    public void goOutJail(Player p) {
        this.map.remove(p);
        p.setInJail(false);
        this.landOn(p);
        message = " is out of jail\n";
    }

    /**
     * get the money a player need to go out of jail
     */
    public int getJailFee(){
        return this.jailFee;
    }

    public HashMap<Player, Integer> getMap() {
        return this.map;
    }

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"Jail\">");
        string.append("<Name>"+this.getName()+"</Name>");
        string.append("<Number>"+this.getNumber()+"</Number>");
        string.append("<Price>"+this.getJailFee()+"</Price>");
        string.append("<JailMap>");
        for(Player id: map.keySet()){
            string.append(id.getName()+ "=" +map.get(id)+ ",");
        }
        string.append("</JailMap>");
        string.append("</Square>");

        return string.toString();
    }

}
