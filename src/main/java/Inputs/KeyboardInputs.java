package Inputs;
import Interface_Adapters.PauseGameController;
import Interface_Adapters.PlayerMovementController;
import Interface_Adapters.ShowMapController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

//    private GamePanel gamePanel;
//    public KeyboardInputs(GamePanel gamePanel){
//
//        this.gamePanel = gamePanel;
//    }
    PauseGameController pauseGameController;
    ShowMapController showMapController;
    PlayerMovementController playerMovementController;

    public KeyboardInputs(PauseGameController pauseGameController, ShowMapController showMapController, PlayerMovementController playerMovementController){
        this.pauseGameController = pauseGameController;
        this.showMapController = showMapController;
        this.playerMovementController = playerMovementController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                playerMovementController.playerMoveY(2);
            }
            case KeyEvent.VK_S -> {
                playerMovementController.playerMoveY(-2);
            }
            case KeyEvent.VK_D -> {
                playerMovementController.playerMoveX(-2);
            }
            case KeyEvent.VK_A -> {
                playerMovementController.playerMoveX(2);
            }

            //TODO: Raiyan
            //  Don't directly talk to gamePanel.
            //  Implement CLEAN way of doing this process.
            case KeyEvent.VK_M ->{
                // TODO: Change minimap setting
//                gamePanel.setMinimapVisible(!gamePanel.getMinimapVisible());
//                gamePanel.repaint();
                showMapController.updateMap();
            }
            case KeyEvent.VK_ESCAPE -> {
                // Use Controller to change stuff.
//                gamePanel.setIsPaused(!gamePanel.getIsPaused());
//                gamePanel.repaint();
                pauseGameController.pause();
            }
//            case KeyEvent.VK_O-> {
//                gamePanel.changeStatsBarVisible();
//            }

        }
    }


    //TODO: Abu
    // - Remove Player from gamePanel
    // - Implement CLEAN way of changing velocity following SOLID PRINCIPLES
//    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S -> {
                playerMovementController.playerMoveY(0);
            }
            case KeyEvent.VK_D -> {
                playerMovementController.playerMoveX(0);
                playerMovementController.setIdleDirection(1);}
            case KeyEvent.VK_A -> {
                playerMovementController.playerMoveX(0);
                playerMovementController.setIdleDirection(0); }

        }
    }
}
