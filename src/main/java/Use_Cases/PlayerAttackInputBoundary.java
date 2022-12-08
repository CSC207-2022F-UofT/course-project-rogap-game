package Use_Cases;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface PlayerAttackInputBoundary {
    void attack();
    BufferedImage getCurrAttackHitAnimation();

    void drawPlayerHitRadius(Graphics g);
}
