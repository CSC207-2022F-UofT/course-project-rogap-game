package Use_Cases;

import Entities.MeleeEnemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyMovement extends Movement{
    public EnemyMovement() {
    }
    public void updateX(int playerX) {
    }
    public void updateY(int playerY) {
    }

    public int getVelX(int enemyVisualX, int playerX) {
        int velX;
        velX = enemyMoveHelper(enemyVisualX - 616 - 1280,playerX - meleeEnemy.getHelperX());
        return velX;
    }
    public int getVelY(int enemyVisualX, int playerY) {
        int velY;
        velY = enemyMoveHelper(enemyVisualX - 326 - 720,playerY - meleeEnemy.getHelperY());
        return velY;
    }

    public Rectangle getHitBox() {
        return meleeEnemy.getHitBox();
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
        double distance;
        return distance = Math.sqrt((Math.pow((targetX - meleeEnemy.getVisualX() - meleeEnemy.getHelperX() + 1896),2)
                + Math.pow((targetY - meleeEnemy.getVisualY() - meleeEnemy.getHelperY() + 1046), 2)));
    }
    @Override
    public int getVisualX() {
        return meleeEnemy.getVisualX();
    }
    @Override
    public int getVisualY() {
        return meleeEnemy.getVisualY();
    }
    @Override
    public int getHelperX() {
        return meleeEnemy.getHelperX();
    }
    @Override
    public int getHelperY() {
        return meleeEnemy.getHelperY();
    }
    @Override
    public void setAnimations(BufferedImage[][] animations) {
        meleeEnemy.setAnimations(animations);
    }
    @Override
    public BufferedImage[][] getAnimations() {
        return meleeEnemy.getAnimations();
    }
}
