package Entities;

import Frameworks.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MeleeEnemy {
    public  BufferedImage[][] animations;
    private int spawnX, spawnY;
    private int xEnemy, yEnemy;
    private Rectangle hitBox;

    public MeleeEnemy(int x, int y) {
        this.xEnemy = x;
        this.yEnemy = y;
        this.spawnX = x;
        this.spawnY = y;
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
        hitBox = new Rectangle(spawnX - 1280 + 4, spawnY - 720 + 4, 24, 24);
        return hitBox;
    }
    public void setAnimations(BufferedImage[][] animations) {
        this.animations = animations;
    }
    public BufferedImage[][] getAnimations() {
        return this.animations;
    }

}
