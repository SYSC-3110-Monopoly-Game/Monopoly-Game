package Model;

public class GoToJailSquare extends Square {

    public GoToJailSquare(String name, int number) {
        super(name, number);
    }

    @Override
    public void landOn(Player p){
        p.setLocation(this);
        System.out.println("GO TO JAIL");
    }

}
