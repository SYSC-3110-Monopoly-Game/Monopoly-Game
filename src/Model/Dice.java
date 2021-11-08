package Model;

import java.util.Random;

public class Dice {

    private Random random;
    private int[] dice;



    /**
     * Constructs the default number of dice
     */
    public Dice() {
        this.dice = new int[2];
        this.random = new Random();
    }

    /**
     * Rolls all the dice at once and returns an array of the values
     */
    public int[] rollDice() {
        for (int i = 0; i < 2; i++) {

            int singleDie = this.random.nextInt(6)+1;
            this.dice[i] = singleDie;
        }
        return this.dice;
    }

    /**
     * get dice1 value
     */
    public int getDice1 () {
        return this.dice[0];
    }

    /**
     * get dice2 value
     */
    public int getDice2 () {
        return this.dice[1];
    }

    /**
     * Adds the dice values and returns the sum
     */
    public int getTotalValue() {
        return this.dice[0] + this.dice[1];
    }

    public int[] getDice() {
        return dice;
    }

    /**
     * method which returns true if the die values are equal/doubles
     */
    public boolean hasDoubles() {
        return this.dice[0] == this.dice[1];
    }

    /**
     * method which returns a space-separated list of all the individual die-values
     */
    public String toString() {
        return "Dice #1 = " + this.dice[0] + "\t\t" + "Dice #2 = " + this.dice[1];
    }
}