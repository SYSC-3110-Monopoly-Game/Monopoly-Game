package Model;

public class GoToJailSquare extends Square {

    JailSquare jail;

    public GoToJailSquare(String name, int number) {
        super(name, number);
    }

    public void setJail(JailSquare jail){
        this.jail = jail;
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

    @Override
    public String toXML() {
        StringBuffer string = new StringBuffer();
        string.append("<Square type=\"GoToJail\">");
        string.append("<Name>"+this.getName()+"</Name>");
        string.append("<Number>"+this.getNumber()+"</Number>");
        string.append("</Square>");

        return string.toString();
    }

}
