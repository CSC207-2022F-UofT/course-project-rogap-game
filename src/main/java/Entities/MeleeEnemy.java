package Entities;

import main.GamePanel;

public class MeleeEnemy {
    private GamePanel gamePanel;
    private int xEnemy;
    private int yEnemy;
    private double distance;
    private double velX = 0, velY = 0;
    private int spawnX, spawnY;

    public MeleeEnemy(GamePanel gamePanel, int x, int y, int spawnX, int spawnY) {
        this.gamePanel = gamePanel;
        this.spawnY = spawnY;
        this.spawnX = spawnX;
        this.xEnemy = x + this.spawnX;
        this.yEnemy = y + this.spawnY;
    }

    public void update() {
        enemyMovement();
    }
    private void enemyMovement() { //In order to update current enemy location must update absXenemy.
        distance = Math.sqrt((Math.pow((gamePanel.player.getAbsXPlayer() + 615 - xEnemy - spawnX),2) + Math.pow((gamePanel.player.getAbsYPlayer()+ 325 - yEnemy -spawnY), 2)));
        if (distance < 600 & distance > 10) {
            velX = enemyMoveHelper(xEnemy - 615 ,gamePanel.player.getAbsXPlayer()- spawnX);
            velY = enemyMoveHelper(yEnemy -325,gamePanel.player.getAbsYPlayer() -spawnY);
            xEnemy -= velX;
            spawnX -= velX;
            yEnemy -= velY;
            spawnY -= velY;

        } else {
            velX = 0;
            velY = 0;
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
    public int getxEnemy() {return this.xEnemy;}
    public int getyEnemy() {return this.yEnemy;}
    public void changeXEnemy(int x) {this.xEnemy += x;}
    public void changeYEnemy(int y) {this.yEnemy += y;}
}
