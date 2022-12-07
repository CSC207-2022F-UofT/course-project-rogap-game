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
        if (getMoveableWall(getVisualX(), getVisualY(), -playerMovementInputBoundary.getVelX(), 0)) {
            playerMovementInputBoundary.updateX();
        }
        if (getMoveableWall(getVisualX(), getVisualY(), 0, -playerMovementInputBoundary.getVelY())) {
            playerMovementInputBoundary.updateY();
        }

    }
    public boolean getMoveableWall(int visualX, int visualY, int changeX, int changeY) {
        return collisionController.movable(visualX, visualY, 628, 338, changeX, changeY, 24, 24);
    }
/*    public boolean getMoveableEnemies() {

    }*/
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
