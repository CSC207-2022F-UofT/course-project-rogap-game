package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MeleeEnemy extends Enemy {
    public  BufferedImage[][] animations;
    private int spawnX, spawnY;
    private int xEnemy, yEnemy;
    private Rectangle hitBox;
    private String name;

    public MeleeEnemy(String name, int x, int y, int spawnX, int spawnY) {
        /**
         * Spawn the enemy at a x,y location and saves it.
         * xEnemy and yEnemy: is used for the visual aspect of enemy
         * spawnX and spawnY: is used for finding enemy relative to walls and players
         */
        this.name = name;
        this.xEnemy = x + spawnX;
        this.yEnemy = y + spawnY;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
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
    public void setVisualX(int xDelta) {
        this.xEnemy = xDelta + getHelperX();
    }
    public void setVisualY(int yDelta) {
        this.yEnemy = yDelta + getHelperY();
    }
    @Override
    public void changeHelperX(int x) {
        this.spawnX -= x;
    }
    @Override
    public void changeHelperY(int y) {
        this.spawnY -= y;
    }
    @Override
    public Rectangle getHitBox() {
        /**
         * Generates a rectangle around the enemy that can be used for collision and attacks
         */
        hitBox = new Rectangle(spawnX - 1280 + 4, spawnY - 720 + 4, 24, 24);
        return hitBox;
    }
    @Override
    public void setAnimations(BufferedImage[][] animations) {
        /**
         * Sets the current animations depending on which way the enemy is moving
         */
        this.animations = animations;
    }
    @Override
    public BufferedImage[][] getAnimations() {
        return this.animations;
    }
    public BufferedImage getCurrImage() {
        return animations[0][0];
    }

}
