package view;

import javax.swing.*;
import java.awt.*;

public class PlayerGUI extends JLabel {

    /**
     * initialize a single player panel
     */
    public  PlayerGUI(String name){
        super(String.valueOf(name.charAt(0)));
        if(name.length() > 1 && name.charAt(1) == 'A'){
            this.setBackground(Color.YELLOW);
        } else {
            this.setBackground(Color.white);
        }
        this.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        this.setForeground(Color.black);
        this.setBounds(30, 33, 20, 28);
        this.setOpaque(true);
    }

}
