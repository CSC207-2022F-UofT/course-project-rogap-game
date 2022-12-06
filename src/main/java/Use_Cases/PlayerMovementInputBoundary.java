package Use_Cases;

import java.awt.image.BufferedImage;

public interface PlayerMovementInputBoundary {
    void setMovingX(int velX);
    void setMovingY(int velY);
    int getLocationX();
    int getLocationY();
    BufferedImage getCurrAnimation();
    void updateLocation();
}
