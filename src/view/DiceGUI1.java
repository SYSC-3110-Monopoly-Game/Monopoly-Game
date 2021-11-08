package view;

import Model.Dice;
import Model.MonopolyGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DiceGUI1 extends JPanel {

    MonopolyGame game = new MonopolyGame();

    public DiceGUI1() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.black,3));
    }

    public String getPathname (Dice dice){
        if (dice.getDice1() == 1){
            return "src/images/Dice1.png";
        }else if (dice.getDice1() == 2){
            return "src/images/Dice2.png";
        }else if (dice.getDice1() == 3){
            return "src/images/Dice3.png";
        }else if (dice.getDice1() == 4){
            return "src/images/Dice4.png";
        }else if (dice.getDice1() == 5){
            return "src/images/Dice5.png";
        }else if (dice.getDice1() == 6){
            return "src/images/Dice6.png";
        }else{
            return "src/images/Dice1.png";
        }
    }

    protected void paintComponent(Graphics g) {

        BufferedImage img;
        Image resizedImage = null;
        try {
            img = ImageIO.read(new File(getPathname(game.getDice())));
            resizedImage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        g.drawImage(resizedImage, 0, 0, null);
    }

    public static void main(String[] args) {
        DiceGUI1 d1 = new DiceGUI1();
        Dice  dice = new Dice();
        dice.rollDice();
        System.out.println(dice.getDice1());
        System.out.println(d1.getPathname(dice));
        dice.rollDice();
        System.out.println(dice.getDice1());
        System.out.println(d1.getPathname(dice));
    }
}
