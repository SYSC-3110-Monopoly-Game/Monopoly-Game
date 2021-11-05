package Model;

import view.GoToJailGUI;

import java.awt.*;

public class GoToJailSquare extends Square {

    JailSquare jail;
    public GoToJailGUI gui;

    public GoToJailSquare(String name, int number, JailSquare jail) {
        super(name, number);
        this.gui = new GoToJailGUI();
        this.jail = jail;
    }

    /** Sends player to Jail Square when player lands on square
     *
     * @param p
     */
    @Override
    public void landOn(Player p){
        this.jail.goJail(p);
        System.out.println("Oh no! You have to go to jail player " + p.getName());
    }

    @Override
    public void landOff(Player p) {
        this.gui.removePlayer(p.getName(), false);
    }

}
