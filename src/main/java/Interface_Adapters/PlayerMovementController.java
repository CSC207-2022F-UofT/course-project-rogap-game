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
        playerMovementInputBoundary.updateLocation();
    }
    
    public int getX() {
        return playerMovementInputBoundary.getLocationX();
    }
    public int getY() {
        return playerMovementInputBoundary.getLocationY();
    }
    public BufferedImage getCurrAnimation() {
        return playerMovementInputBoundary.getCurrAnimation();
    }

    public void setIdleDirection(int dir) {

    }
    //Must check if it is possible to move before moving
    public void playerMoveX(int velX) {
        if (collisionController.movable(getX(), getY(), velX,0, 36, 36)) {
            playerMovementInputBoundary.setMovingX(velX);
        }
    }
    public void playerMoveY(int velY) {
        if (collisionController.movable(getX(), getY(), 0,velY, 36, 36)) {
            playerMovementInputBoundary.setMovingY(velY);
        }
    }

}
