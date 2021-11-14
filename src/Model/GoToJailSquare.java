package Model;

public class GoToJailSquare extends Square {

    JailSquare jail;

    public GoToJailSquare(String name, int number, JailSquare jail) {
        super(name, number);
        this.jail = jail;
        message = " is on GoToJail and you have to go to jail!!\n";
    }

    /** Sends player to Jail Square when player lands on square
     *
     * @param p
     */
    @Override
    public void landOn(Player p){
        this.jail.goJail(p);
        p.setInJail(true);
        System.out.println("Oh no! You have to go to jail player " + p.getName());
    }

}
