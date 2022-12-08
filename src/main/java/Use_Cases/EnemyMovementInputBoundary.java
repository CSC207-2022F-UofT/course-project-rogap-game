package Use_Cases;

import java.awt.*;

public interface EnemyMovementInputBoundary {
    int getHelperX();
    int getHelperY();
    int getVisualX();
    int getVisualY();
    void updateX(int playerX);
    void updateY(int playerY);
    int getVelX(int playerX);
    int getVelY(int playerY);
    void changeX(int velX);
    void changeY(int velY);
    Rectangle getHitbox();
    double getDistance(int targetX, int targetY);

}
