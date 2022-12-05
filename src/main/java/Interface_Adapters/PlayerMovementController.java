package Interface_Adapters;

import Use_Cases.PlayerMovementInputBoundary;


import java.awt.image.BufferedImage;

public class PlayerMovementController {
    PlayerMovementInputBoundary playerMovementInputBoundary;

    public PlayerMovementController(PlayerMovementInputBoundary playerMovementInputBoundary) {
        this.playerMovementInputBoundary = playerMovementInputBoundary;
    }
    
    public int getX() {
        return playerMovementInputBoundary.getLocationX();
    }
    public int getY() {
        return playerMovementInputBoundary.getLocationY();
    }
    public BufferedImage getCurrAnimation() {

    }

    public void playerMoveX(int velX) {
        playerMovementInputBoundary.setMovingX(velX);
    }
    public void playerMoveY(int velY) {
        playerMovementInputBoundary.setMovingY(velY);
    }

}
