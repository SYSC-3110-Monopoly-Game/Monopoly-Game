package Model;

import java.awt.*;

public class GoToJailSquare extends Square {

    JailSquare jail;

    public GoToJailSquare(String name, int number, JailSquare jail) {
        super(name, number, Color.BLACK);
        this.jail = jail;
    }

    /** Sends player to Jail Square when player lands on square
     *
     * @param p
     */
    @Override
    public void landOn(Player p){
        this.jail.landOn(p);
        System.out.println("Oh no! You have to go to jail player " + p.getName());
    }

}
