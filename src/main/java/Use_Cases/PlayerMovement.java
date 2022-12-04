package Use_Cases;

import Entities.Player;

import java.awt.*;

public class PlayerMovement extends Movement {
    public PlayerMovement(int velX, int velY, int xLocation, int yLocation) {
        super(velX, velY, xLocation, yLocation);
    }
    @Override
    public Rectangle getHitBox() {
        return null;
    }

    @Override
    public int newXLocation() {
        return this.velX;
    }

    @Override
    public int newYLocation() {
        return 0;
    }

    @Override
    public void setVelX() {

    }

    @Override
    public void setVelY() {

    }

    @Override
    public void setMoving() {

    }
}
