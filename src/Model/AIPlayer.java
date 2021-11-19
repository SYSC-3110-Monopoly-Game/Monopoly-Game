package Model;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

    private final String name;
    Player player;
    Dice dice;
    AIPlayer ai;
    Random random = new Random();
    MonopolyGame mg;
    private PropertySquare square;
    private PropertySquare[] squaresOwned;


    public AIPlayer(String name, Square square) {
        super(name, square);
        this.name = "AI";
    }

    public boolean getBoolean() {
        return random.nextBoolean();
    }

    public Square getRandomSquare()
    {
        Random r = new Random();
        int randomIndex = 0;
        ArrayList<PropertySquare> p= new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            randomIndex = r.nextInt(p.size());
        }
        PropertySquare randomPropertySquare = p.get(randomIndex);
        return randomPropertySquare;
    }

    @Override
    public boolean buyProperty(Square location) {
        if(player.isBankrupt() == false){
            player.buyProperty(location);
        }
        return false;
    }

    public ArrayList<PropertySquare> getSellProperties(ArrayList<PropertySquare> p){
        p.remove(player.hasBuilding());
        return p;
    }

    public void sellSomeThing(){
        if(player.isBankrupt() == true){
            ai.getBoolean();
            if(ai.getBoolean() == true){
                ai.sellProperty(square);
            } else if (ai.getBoolean() == false){
                String answer = new String();
                ai.sellH(answer);
            }
        }
    }

    @Override
    public void sellProperty(Square property) {
        if(player.isBankrupt() == true){
            ArrayList<PropertySquare> p= new ArrayList<>();
            ai.getSellProperties(p);
            // random
            player.sellProperty(ai.getRandomSquare());
        }
    }

    public void rollDiceAI(){
        dice.rollDice();
    }

    @Override
    public int buildH(String answer) {
        ai.getBoolean();
        if(ai.getBoolean() == true){
            this.getSelectedSquare().buildHouse();
        } else if (ai.getBoolean() == false){
            this.getSelectedSquare().buildHotel();
        }
        return -1;
    }

    @Override
    public int sellH(String answer) {
        player.hasBuilding();
        for(PropertySquare square: squaresOwned) {
            if(square.hasHouses()){
                this.getSelectedSquare().sellHouse();
            }else if (square.hasHouses() && square.hasHotel()){
                this.getSelectedSquare().sellHouse();
            }else if(square.hasHotel()){
                this.getSelectedSquare().sellHotel();
            }
        }
        return -1;
    }
}
