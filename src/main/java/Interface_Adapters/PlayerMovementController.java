package Interface_Adapters;

import Use_Cases.PlayerMovementInputBoundary;


import java.awt.image.BufferedImage;

public class PlayerMovementController {
    PlayerMovementInputBoundary playerMovementInputBoundary;
    CollisionController collisionController;

    public PlayerMovementController(PlayerMovementInputBoundary playerMovementInputBoundary, CollisionController collisionController) {
        this.playerMovementInputBoundary = playerMovementInputBoundary;
        this.collisionController = collisionController;
    }
    public void update() {
        if (collisionController.movable(getVisualX(),getVisualY(), 628, 338,
                -playerMovementInputBoundary.getVelX(), 0, 24, 24)) {
            playerMovementInputBoundary.updateX();
        }
        if (collisionController.movable(getVisualX(),getVisualY(), 628, 338, 0,
                -playerMovementInputBoundary.getVelY(), 24, 24)) {
            playerMovementInputBoundary.updateY();
        }
    }
    public int getVisualX() {
        return playerMovementInputBoundary.getVisualX();
    }
    public int getVisualY() {
        return playerMovementInputBoundary.getVisualY();
    }
    public int getHelperX() {
        return playerMovementInputBoundary.getHelperX();
    }
    public int getHelperY() {
        return playerMovementInputBoundary.getHelperY();
    }
    public BufferedImage getCurrAnimation() {
        return playerMovementInputBoundary.getCurrAnimation();
    }
    public void setIdleDirection(int dir) {
        playerMovementInputBoundary.setIdleDirection(dir);
    }
    //Must check if it is possible to move before moving

    public void rightActivator() {
        playerMovementInputBoundary.rightActivator();
    }
    public void leftActivator() {
        playerMovementInputBoundary.leftActivator();
    }
    public void upActivator() {
        playerMovementInputBoundary.upActivator();
    }
    public void downActivator() {
        playerMovementInputBoundary.downActivator();
    }
    public void rightDeactivator() {
        playerMovementInputBoundary.rightDeactivator();
    }
    public void leftDeactivator() {
        playerMovementInputBoundary.leftDeactivator();
    }
    public void upDeactivator() {
        playerMovementInputBoundary.upDeactivator();
    }
    public void downDeactivator() {
        playerMovementInputBoundary.downDeactivator();
    }
}
