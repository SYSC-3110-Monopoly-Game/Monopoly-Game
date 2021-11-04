package view;

import javax.swing.*;
import java.awt.*;

public class PlayerGUI extends JLabel {
    public  PlayerGUI(String name){
        super(name);
        this.setBackground(Color.white);
        this.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        this.setForeground(Color.black);
        this.setBounds(30, 33, 20, 28); // need to fix here for adjustable player numbers
        this.setOpaque(true);
    }

}
