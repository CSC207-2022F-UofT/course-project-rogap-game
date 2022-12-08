package Use_Cases;

import Entities.MeleeEnemy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyMovement extends Movement{
    private MeleeEnemy meleeEnemy;
    public EnemyMovement(MeleeEnemy meleeEnemy) {
        this.meleeEnemy = meleeEnemy;
    }
    public void updateX(int playerX) {
    }
    public void updateY(int playerY) {
    }

    public int getVelX(int playerX) {
        int velX;
        velX = enemyMoveHelper(meleeEnemy.getVisualX() - 616 - 1280,playerX - meleeEnemy.getHelperX());
        return velX;
    }
    public int getVelY(int playerY) {
        int velY;
        velY = enemyMoveHelper(meleeEnemy.getVisualY()- 326 - 720,playerY - meleeEnemy.getHelperY());
        return velY;
    }
    public void changeX(int velX) {
        meleeEnemy.setHelperX(velX);
        meleeEnemy.setVisualX(velX);
    }
    public void changeY(int velY) {
        meleeEnemy.setHelperY(velY);
        meleeEnemy.setVisualY(velY);
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
