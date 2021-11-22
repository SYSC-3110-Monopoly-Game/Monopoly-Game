package Model;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

    Random random = new Random();
    int index;

    public AIPlayer(String name, Square square) {
        super(name+"AI", square);
    }

    public boolean getBoolean() {
        return random.nextBoolean();
    }

    public PropertySquare getRandomSquare(ArrayList<PropertySquare> p)
    {
        Random r = new Random();
        int randomIndex = r.nextInt(p.size());
        return p.get(randomIndex);
    }

    public ArrayList<PropertySquare> getSellProperties(){
        ArrayList<PropertySquare> p = this.getProperties();
        ArrayList<PropertySquare> temp = this.hasBuilding();
        if(temp != null){
            p.remove(temp);
        }
        return p;
    }

    public void sellSomeThing(){
        while(this.isBankrupt()){
            if(this.isBankrupt()){
                this.sellProperty(this.getRandomSquare(this.getSellProperties()));
            }
        }
    }

    public boolean buildBuildings(){
        ArrayList<PropertySquare> propertyList = this.removeRailroadUtility(this.hasWholeSet());
        if(!propertyList.isEmpty()){
            propertyList = this.getAvailableProperties(propertyList);
            if(!propertyList.isEmpty() && this.getBoolean()){
                this.setSelectedSquare(this.getRandomSquare(propertyList));
                if(this.getBoolean()){
                    return this.buildH("House") > 0;
                }
            }else {
                System.out.println("not enough money");
            }
        } else {
            System.out.println("not hold a set");
        }
        return false;
    }

    public void sellBuildings() {
        ArrayList<PropertySquare> propertyList = this.hasBuilding();
        if (!propertyList.isEmpty()) {
            this.setSelectedSquare(this.getRandomSquare(propertyList));
            this.sellH("House");
        } else {
            System.out.println("No houses neither hotels");
        }
    }
}