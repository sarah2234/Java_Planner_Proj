package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ex_panel7 extends JFrame {
    public ex_panel7(){
        road_create C = new road_create();
        add(C);

        setSize(450,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        new ex_panel7();
    }
}
