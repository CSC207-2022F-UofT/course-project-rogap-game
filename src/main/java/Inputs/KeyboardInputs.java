package Inputs;
import Interface_Adapters.PauseGameController;
import Interface_Adapters.ShowMapController;
import Interface_Adapters.ShowStatsController;

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

    ShowStatsController showStatsController;

    public KeyboardInputs(PauseGameController pauseGameController, ShowMapController showMapController,
                          ShowStatsController showStatsController){
        this.pauseGameController = pauseGameController;
        this.showMapController = showMapController;
        this.showStatsController = showStatsController;
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
//            case KeyEvent.VK_W -> {
//                gamePanel.player.setVelY(2);
//            }
//            case KeyEvent.VK_S -> {
//                gamePanel.player.setVelY(-2);
//            }
//            case KeyEvent.VK_D -> {
//                gamePanel.player.setVelX(-2);
//            }
//            case KeyEvent.VK_A -> {
//                gamePanel.player.setVelX(2);
//            }

            case KeyEvent.VK_M -> {
                showMapController.updateMap();
            }
            case KeyEvent.VK_ESCAPE -> {
                pauseGameController.pause();
            }
            case KeyEvent.VK_O-> {
                showStatsController.showStats();
            }

        }
    }


    //TODO: Abu
    // - Remove Player from gamePanel
    // - Implement CLEAN way of changing velocity following SOLID PRINCIPLES
//    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_W, KeyEvent.VK_S -> {
//                gamePanel.player.setVelY(0);
//            }
//            case KeyEvent.VK_D -> {
//                gamePanel.player.setVelX(0);
//                gamePanel.player.setIdleDirection(1);}
//            case KeyEvent.VK_A -> {
//                gamePanel.player.setVelX(0);
//                gamePanel.player.setIdleDirection(0);
//            }
        }
    }
}
