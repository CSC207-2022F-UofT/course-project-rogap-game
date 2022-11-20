package Inputs;

import main.GamePanel;

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

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                gamePanel.player.setVelY(2);
                gamePanel.player.setMoving();
            }
            case KeyEvent.VK_S -> {
                gamePanel.player.setVelY(-2);
                gamePanel.player.setMoving();
            }
            case KeyEvent.VK_D -> {
                gamePanel.player.setVelX(-2);
                gamePanel.player.setMoving();
            }
            case KeyEvent.VK_A -> {
                gamePanel.player.setVelX(2);
                gamePanel.player.setMoving();
            }
            case KeyEvent.VK_M ->{
                // TODO: Change minimap setting
                gamePanel.setMinimapVisible(!gamePanel.getMinimapVisible());
                gamePanel.repaint();
            }
            case KeyEvent.VK_ESCAPE -> {
                gamePanel.setIsPaused(!gamePanel.getIsPaused());
                gamePanel.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S -> {
                gamePanel.player.setVelY(0);
                gamePanel.player.setMoving();

            }
            case KeyEvent.VK_D -> {
                gamePanel.player.setVelX(0);
                gamePanel.player.setMoving();
                gamePanel.player.setIdleDirection(1);}
            case KeyEvent.VK_A -> {
                gamePanel.player.setVelX(0);
                gamePanel.player.setMoving();
                gamePanel.player.setIdleDirection(0);
            }
        }
    }
}
