package Entities;

import Frameworks.GamePanel;
import main.WallCollision;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Player {
    private GamePanel gamePanel;
    private BufferedImage[] sprites = new BufferedImage[4];
    private BufferedImage[][] animations;
    private int idleDir = 0;

    private int velX = 0, velY = 0;
    private int absXPlayer = 1882, absYPlayer = 1738;

    private int aniTick, aniIndex, aniSpeed= 10;
    private int playerAction = 0;
    private boolean moving = false;
    
    // VARIABLES FOR SHOP SYSTEM
    private int gold = 100;
    private int health = 10;

    private int[][] verticalWalls = {{0,1,1},{0,4,1},{1,1,0},{1,2,1},{1,3,1},{1,4,0},{2,0,1},{2,1,0},{2,2,1},{2,3,0},{2,4,1},{3,0,1},{3,1,1},{3,3,1}};
    private int[][] horizontalWalls = {{2,0,1},{0,1,1},{1,1,1},{2,1,0},{0,2,1},{1,2,0},{2,2,1},{1,3,0},{2,3,1},{1,4,0},{0,4,1},{2,4,1},{0,5,1},{1,5,1}};
    private WallCollision wallCollision = new WallCollision(verticalWalls, horizontalWalls);
    

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();
    }
    public void update() {
        updateWalls();
        ArrayList wallCheck = currMoveCollision(-velX, -velY);
        if (movable(absXPlayer - velX, absYPlayer - velY) & (boolean) wallCheck.get(0)) {
            gamePanel.changeXDelta(velX);
            gamePanel.changeYDelta(velY);
            updateLocation(velX, velY);
        } else if (movable(absXPlayer - velX, absYPlayer - velY) & !((boolean) wallCheck.get(0))) {
            if (wallCheck.get(1) == "y" & (int) wallCheck.get(2) <= 1) {
                gamePanel.changeXDelta(velX);
                updateLocation(velX, 0);
            } else if (wallCheck.get(1) == "x" & (int) wallCheck.get(2) <= 1) {
                gamePanel.changeYDelta(velY);
                updateLocation(0, velY);
            }
        }
        updateAnimationTick();
        setAnimation();
    }
    private ArrayList currMoveCollision(int x, int y) {
        return this.getWallCollision().moveAbleWall(616 + 12, 326 + 12,
                x, y, 24, 24);
    }
    //Player collisions with enemy and walls.
    private void updateLocation(int x, int y){
        this.absXPlayer -= x;
        this.absYPlayer -= y;
    }
    public void updateWalls() {wallCollision.createWallLayout(gamePanel.getXDelta() + velX, gamePanel.getYDelta() + velY);}
    public WallCollision getWallCollision() {return this.wallCollision;}
    public Rectangle getHitBox() {
        Rectangle hitBox = new Rectangle(absXPlayer + 6, absYPlayer + 6, 36, 36);
        return hitBox;
    }
    private boolean movable(int targetX, int targetY) {
        Rectangle hitBox = new Rectangle(targetX + 6, targetY + 6, 36, 36);
        boolean move = true;
        for (MeleeEnemy enemy : gamePanel.getEnemyList()) {
            if (hitBox.intersects(enemy.getHitBox())) {
                move = false;
            }
        }
        return move;
    }
    public int getHealth() {
        return this.health;
    }

    public void addHealth(int amount){
        System.out.println("Health Before: " + getHealth());
        this.health += amount;
        if (getHealth() > 100){
            this.health = 100;
        }
        System.out.println("Health After: " + getHealth());
    }

    public int getGold(){
        return this.gold;
    }
    public void addGold(int gold){
        this.gold += gold;
    }

    public void removeGold(int amount){
        this.gold -= amount;
    }
    
    //Helper methods

    //Handles all of player movement
}
