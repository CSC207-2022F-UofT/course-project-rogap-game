package Inputs;
import Interface_Adapters.*;

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
    ShowStatsController showStatsController;
    ReadFromBoardPresenter readFromBoardPresenter;

    public KeyboardInputs(PauseGameController pauseGameController, ShowMapController showMapController,
                          ShowStatsController showStatsController, PlayerMovementController playerMovementController,
                          ReadFromBoardPresenter readFromBoardPresenter){
        this.pauseGameController = pauseGameController;
        this.showMapController = showMapController;
        this.showStatsController = showStatsController;
        this.playerMovementController = playerMovementController;
        this.readFromBoardPresenter = readFromBoardPresenter;
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

            case KeyEvent.VK_M -> {
                showMapController.updateMap();
            }
            case KeyEvent.VK_ESCAPE -> {
                pauseGameController.pause();
            }
            case KeyEvent.VK_O-> {
                showStatsController.showStats();
            }
            case KeyEvent.VK_L-> {
                readFromBoardPresenter.readFromDatabase();
            }

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
