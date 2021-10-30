package Model;

import java.util.HashMap;

public class JailSquare extends Square {

    private HashMap<Player, Integer>map;
    private int round=3;
    private Player player;
    private Object Player;


    public JailSquare(String name, int number) {
        super(name, number);
    }

    public int getCounter(Player player){
        if(round == 0){
            map.remove(player);
        }else{
            this.round -= round;
        }
        return round;
    }

    @Override
    public void landOn(Player p) {
        JailSquare.landOn(p);
        map.put((Model.Player) Player,round);
    }
}
