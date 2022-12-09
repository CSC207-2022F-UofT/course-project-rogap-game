package Entities;

import Frameworks.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy {
    public  BufferedImage[][] animations;
    private int spawnX, spawnY;
    private int xEnemy, yEnemy;
    private Rectangle hitBox;
    private String name;

    /**
     * This is the enemy class constructor
     * @param name: This is the name of the enemy
     * @param x: This is the current x-coordinate of the map
     * @param y: This is the current y-coordinate of the map
     * @param spawnX: This is the x-coord for the enemy spawn
     * @param spawnY: This is the y-coord for the enemy spawn
     */
    public Enemy(String name, int x, int y, int spawnX, int spawnY) {
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
    public int getVisualX() {
        return xEnemy;
    }
    public int getVisualY() {
        return yEnemy;
    }
    public int getHelperX() {
        return spawnX;
    }
    public int getHelperY() {
        return spawnY;
    }
    public void setVisualX(int x) {
        this.xEnemy -= x;
    }
    public void setVisualY(int y) {
        this.yEnemy -= y;
    }
    public void setHelperX(int x) {
        this.spawnX -= x;
    }
    public void setHelperY(int y) {
        this.spawnY -= y;
    }

    public Rectangle getHitBox() {
        /**
         * Generates a rectangle around the enemy that can be used for collision and attacks
         */
        hitBox = new Rectangle(spawnX - 1280 + 4, spawnY - 720 + 4, 24, 24);
        return hitBox;
    }
    public void setAnimations(BufferedImage[][] animations) {
        /**
         * Sets the current animations depending on which way the enemy is moving
         */
        this.animations = animations;
    }
    public BufferedImage[][] getAnimations() {
        return this.animations;
    }

}