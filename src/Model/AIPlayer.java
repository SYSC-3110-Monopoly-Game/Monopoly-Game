package Model;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {

    Random random = new Random();

    public AIPlayer(String name, Square square) {
        super(name+"AI", square);
    }

    public AIPlayer(String name, int cash, boolean b, boolean d, String decision, Square lastLocation, Square currentLocation, ArrayList<PropertySquare> sOwned, PropertySquare selectedSquare) {
        super(name, cash, b, d, decision, lastLocation, currentLocation, sOwned, selectedSquare);
    }


    /**
     * get a random boolean
     *
     * @return boolean
     */
    public boolean getBoolean() {
        return random.nextBoolean();
    }

    /**
     * get a random square from the given square list
     */
    public PropertySquare getRandomSquare(ArrayList<PropertySquare> p)
    {
        Random r = new Random();
        if(!p.isEmpty()){
            int randomIndex = r.nextInt(p.size());
            return p.get(randomIndex);
        }
        return null;
    }

    /**
     * get all sellable properties of the AI Player
     */
    public ArrayList<PropertySquare> getSellProperties(){
        ArrayList<PropertySquare> p = this.getProperties();
        ArrayList<PropertySquare> temp = this.hasBuilding();
        if(temp != null){
            p.remove(temp);
        }
        return p;
    }

    /**
     * sell properties until AI is not bankrupt
     */
    public void sellSomeThing(){
        while(this.isBankrupt()){
            PropertySquare temp = this.getRandomSquare(this.getSellProperties());
            if(temp != null) {
                this.sellProperty(temp);
            } else {
                System.out.println("No properties");
                break;
            }
        }
    }

    /**
     * build a house on a random property which is owned by this AI
     */
    public boolean buildBuildings(){
        ArrayList<PropertySquare> propertyList = this.removeRailroadUtility(this.hasWholeSet());
        if(!propertyList.isEmpty()){
            propertyList = this.getAvailableProperties(propertyList);
            if(!propertyList.isEmpty() && this.getBoolean()){
                PropertySquare temp = this.getRandomSquare(propertyList);
                if(temp != null) {
                    this.setSelectedSquare(temp);
                    if (this.getBoolean()) {
                        return this.buildH("House") > 0;
                    } else {
                        System.out.println("No properties");
                    }
                }
            }else {
                System.out.println("not enough money");
            }
        } else {
            System.out.println("not hold a set");
        }
        return false;
    }

    /**
     * sell a house on a random property which is owned by this AI and
     * has at least one house on it
     */
    public void sellBuildings() {
        ArrayList<PropertySquare> propertyList = this.hasBuilding();
        if (!propertyList.isEmpty()) {
            PropertySquare temp = this.getRandomSquare(propertyList);
            if(temp != null) {
                this.setSelectedSquare(temp);
                this.sellH("House");
            } else {
                System.out.println("No properties");
            }
        } else {
            System.out.println("No houses neither hotels");
        }
    }
}