package view;

import Model.Player;
import Model.PropertySquare;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class popupWindow{

    int size;
    int height;
    JButton button;
    JPanel panel;

    public popupWindow(Player player, ArrayList<PropertySquare> properties) {

        JFrame frame = new JFrame("sell properties");

        size = properties.size();
        height = size/8;

        panel = new JPanel();
        GridLayout layout = new GridLayout(height, 8, 10, 10);
        frame.setBounds(100,300,500, height * 50);
        panel.setLayout(layout);

        for(PropertySquare property: properties) {
            button = new JButton(property.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(properties.isEmpty()){
                        frame.dispose();
                    }
                    for(PropertySquare p: properties) {
                        if(e.getSource().equals(p.getName())) {
                            player.sellProperty(p);
                            if(!player.isBankrupt()) {
                                frame.dispose();
                            }
                        }
                    }
                }
            });
            panel.add(button);
        }


        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
