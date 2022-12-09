package Frameworks;

import Interface_Adapters.UpdateScreenBoundary;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame myframe;

    /**
     * This is our GameWindow where everything is going to be drawn
     * @param screenModel: This Jpanel organizes the elements that need to be drawn
     */
    public GameWindow(UpdateScreenBoundary screenModel){
        myframe = new JFrame("RoGap");
        myframe.setSize(new Dimension(1280, 720));
        myframe.setResizable(false);
        myframe.setDefaultCloseOperation(myframe.EXIT_ON_CLOSE);
        myframe.setLocationRelativeTo(null);
        myframe.add((Component) screenModel);

        myframe.setVisible(true);
    }
}
