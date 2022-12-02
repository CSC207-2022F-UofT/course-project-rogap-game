package Inputs;

import Frameworks.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    //TODO: Abu
    // - Remove Player from gamePanel
    // - Implement CLEAN way of changing velocity following SOLID PRINCIPLES
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                gamePanel.player.setVelY(2);
            }
            case KeyEvent.VK_S -> {
                gamePanel.player.setVelY(-2);
            }
            case KeyEvent.VK_D -> {
                gamePanel.player.setVelX(-2);
            }
            case KeyEvent.VK_A -> {
                gamePanel.player.setVelX(2);
            }

            //TODO: Raiyan
            //  Don't directly talk to gamePanel.
            //  Implement CLEAN way of doing this process.
            case KeyEvent.VK_M ->{
                // TODO: Change minimap setting
                gamePanel.setMinimapVisible(!gamePanel.getMinimapVisible());
                gamePanel.repaint();
            }
            case KeyEvent.VK_ESCAPE -> {
                gamePanel.setIsPaused(!gamePanel.getIsPaused());
                gamePanel.repaint();
            }
            case KeyEvent.VK_O-> {
                gamePanel.changeStatsBarVisible();
            }

        }
    }


    //TODO: Abu
    // - Remove Player from gamePanel
    // - Implement CLEAN way of changing velocity following SOLID PRINCIPLES
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S -> {
                gamePanel.player.setVelY(0);
            }
            case KeyEvent.VK_D -> {
                gamePanel.player.setVelX(0);
                gamePanel.player.setIdleDirection(1);}
            case KeyEvent.VK_A -> {
                gamePanel.player.setVelX(0);
                gamePanel.player.setIdleDirection(0);
            }
        }
    }
}
