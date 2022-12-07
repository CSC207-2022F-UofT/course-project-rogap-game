package Use_Cases;

import java.awt.*;
import java.util.ArrayList;

public class EnemyMovement extends Movement{
    private int spawnX, spawnY;
    private int xEnemy, yEnemy;
    private double distance;
    private Rectangle hitBox;
    private int velX = 0, velY = 0;
    public EnemyMovement(int xEnemy, int yEnemy, int spawnX, int spawnY) {
        this.spawnY = spawnY;
        this.spawnX = spawnX;
        this.xEnemy = xEnemy;
        this.yEnemy = yEnemy;
    }
    public void updateX(int playerX) {

    }
    public void updateY(int playerY) {

    }

    public int getVelX(int playerX) {
        velX = enemyMoveHelper(xEnemy - 616 - 1280,playerX - spawnX);
        return velX;
    }
    public int getVelY(int playerY) {
        velY = enemyMoveHelper(yEnemy - 326 - 720,playerY - spawnY);
        return velY;
    }
    public void changeX(int velX) {
        xEnemy -= velX;
        spawnX -= velX;
    }
    public void changeY(int velY) {
        yEnemy -= velY;
        spawnY -= velY;
    }
    private int enemyMoveHelper(int c, int targetC) {
        if (c < targetC) {
            return -1;
        } else if (c == targetC) {
            return 0;
        } else {
            return 1;
        }
    }
    public double getDistance(int targetX, int targetY) {
        return distance = Math.sqrt((Math.pow((targetX - xEnemy - spawnX + 1896),2)
                + Math.pow((targetY - yEnemy - spawnY + 1046), 2)));
    }
    public Rectangle getHitBox() {
        hitBox = new Rectangle(spawnX - 1280 + 4, spawnY - 720 + 4, 24, 24);
        return hitBox;
    }

    @Override
    public int getVisualX() {
        return xEnemy;
    }

    @Override
    public int getVisualY() {
        return yEnemy;
    }

    @Override
    public int getHelperX() {
        return spawnX;
    }

    @Override
    public int getHelperY() {
        return spawnY;
    }
}
