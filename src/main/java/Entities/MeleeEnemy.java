package Entities;

import main.GamePanel;

import java.awt.*;
import java.util.Random;
public class MeleeEnemy {
    private GamePanel gamePanel;
    private int xEnemy;
    private int yEnemy;
    private double distance;
    private int velX = 0, velY = 0;
    private int spawnX, spawnY;
    private int origSpawnX, origSpawnY;
    private Rectangle hitBox;
    private Random random = new Random();
    private int randMoveTick, randMoveSpeed = 50;
    private int randVelX, randVelY;
    public MeleeEnemy(GamePanel gamePanel, int x, int y, int spawnX, int spawnY) {
        this.gamePanel = gamePanel;
        this.spawnY = spawnY;
        this.spawnX = spawnX;
        this.xEnemy = x + this.spawnX;
        this.yEnemy = y + this.spawnY;
        this.origSpawnY = spawnY;
        this.origSpawnX = spawnX;

    }
    public void update() {
        if (!getHitBox().intersects(gamePanel.player.getHitBox())){
            enemyMovement();
        }
    }
    public Rectangle getHitBox() {
        hitBox = new Rectangle(spawnX - 1280 + 4, spawnY -720 + 4, 24, 24);
        return hitBox;
    }

    private void enemyMovement() { //In order to update current enemy location must update absXenemy.
        distance = Math.sqrt((Math.pow((gamePanel.player.getAbsXPlayer() - xEnemy - spawnX + 1896),2) + Math.pow((gamePanel.player.getAbsYPlayer() - yEnemy -spawnY + 1046), 2)));
        if (distance < 600 & distance > 110) {
            System.out.println(yEnemy);
            velX = enemyMoveHelper(xEnemy - 616 - 1280,gamePanel.player.getAbsXPlayer() - spawnX);
            velY = enemyMoveHelper(yEnemy - 326 - 720,gamePanel.player.getAbsYPlayer() - spawnY);
            String[] wall = currMoveCollision(velX, velY);
            if (wall[0].equals("true")) {
                xEnemy -= velX;
                spawnX -= velX;
                yEnemy -= velY;
                spawnY -= velY;
            } else {//This is to make it follow the player even into other rooms.
/*                if (wall[1].equals("x")) {
                    xEnemy -= velX;
                    spawnX -= velX;
                } else if (wall[1].equals("y")) {
                    yEnemy -= velY;
                    spawnY -= velY;
                }*/
                //This will make it so the enemies go back to their spawn location.
/*                velX = enemyMoveHelper(xEnemy,origSpawnX);
                velY = enemyMoveHelper(yEnemy,origSpawnY);
                xEnemy -= velX;
                spawnX -= velX;
                yEnemy -= velY;
                spawnY -= velY;*/
            }
        } else {
/*            enemyRandMove();*/
        }
    }

    private String[] currMoveCollision(int x, int y) {
        return gamePanel.player.getWallCollision().enemyMovableWall(xEnemy + 4, yEnemy + 4,
                x, y, 24, 24);
    }
    private void enemyRandMove() {
        randMoveTick++;
        if (randMoveTick >= randMoveSpeed) {
            randMoveTick = 0;
            int min = 0;
            int max = 5;
            int i = random.nextInt(max - min + 1) + min;
            switch (i) {
                case 0:
                    randVelX = 0;
                    randVelY = 1;
                    break;
                case 1:
                    randVelX = 1;
                    randVelY = 0;
                    break;
                case 2:
                    randVelX = 0;
                    randVelY = -1;
                    break;
                case 3:
                    randVelX = -1;
                    randVelY = 0;
                    break;
                default:
                    randVelX = 0;
                    randVelY = 0;
                    break;
            }
        }
        String[] checkCollision = currMoveCollision(randVelX, randVelY);
        System.out.println(randVelX);
        if (checkCollision[0] == "true") {
            xEnemy -= randVelX;
            spawnX -= randVelX;
            yEnemy -= randVelY;
            spawnY -= randVelY;
        } else {
            randMoveTick = randMoveSpeed;
        }
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
    public int getXEnemy() {return this.xEnemy;}
    public int getYEnemy() {return this.yEnemy;}
    public void changeXEnemy(int x) {this.xEnemy += x;}
    public void changeYEnemy(int y) {this.yEnemy += y;}
}
