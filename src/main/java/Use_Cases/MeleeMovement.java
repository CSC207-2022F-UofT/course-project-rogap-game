package Use_Cases;

import java.awt.*;

public class MeleeMovement extends Movement {

    private Rectangle hitBox;
    private int velX, velY, xLocation, yLocation;
    public MeleeMovement(int velX, int velY, int xLocation, int yLocation) {
        this.velX = velX;
        this.velY = velY;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    @Override
    public Rectangle getHitBox() {
        hitBox = new Rectangle(xLocation - 1280 + 4, yLocation - 720 + 4, 24, 24);
        return hitBox;
    }

    @Override
    public int newXLocation() {
        return 0;
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
