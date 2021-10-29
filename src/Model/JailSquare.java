package Model;

import java.util.HashMap;

public class JailSquare extends Square {

    public HashMap<Integer, Object> map;
    private int round=3;
    private Object Player;

    public JailSquare(String name, int number) {
        super(name, number);
    }

    public int getCounter(){
        this.round -= round;
        if(round == 0){
            map.remove(Player);
        }else{
            this.round -= round;
        }
        return round;
    }

    @Override
    public void landOn(Player p) {
        p.setLocation(this);
        map.put(getCounter(), Player);
    }
}
