package Frameworks;

import Frameworks.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame myframe;

    public GameWindow(GamePanel gamePanel){
        myframe = new JFrame("RoGap");
        myframe.setSize(new Dimension(1280, 720));
        myframe.setResizable(false);
        myframe.setDefaultCloseOperation(myframe.EXIT_ON_CLOSE);
        myframe.setLocationRelativeTo(null);
        myframe.add(gamePanel);

        myframe.setVisible(true);
    }
}
