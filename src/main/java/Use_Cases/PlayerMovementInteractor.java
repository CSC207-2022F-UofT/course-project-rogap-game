package Use_Cases;

import java.awt.image.BufferedImage;

public class PlayerMovementInteractor implements PlayerMovementInputBoundary {
    private PlayerMovement playerMovement;

    public PlayerMovementInteractor(PlayerMovement playerMovement) {
        this.playerMovement = playerMovement;
    }

    @Override
    public void setMovingX(int velX) {
        playerMovement.setVelX(velX);
    }
    @Override
    public void setMovingY(int velY) {
        playerMovement.setVelY(velY);
    }
    public int getLocationX() {
        return this.playerMovement.getCurrLocationX();
    }
    public int getLocationY() {
        return this.playerMovement.getCurrLocationY();
    }

    @Override
    public BufferedImage getCurrAnimation() {

        return playerMovement.getCurrentImage();
    }
    @Override
    public void updateLocation() {
        playerMovement.newXLocation();
        playerMovement.newYLocation();
    }

}
