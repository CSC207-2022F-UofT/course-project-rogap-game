package Entities;

import main.Game;
import main.GamePanel;

public class MeleeMonster extends Monster {
    private GamePanel gamePanel;
    private int xEnemy;
    private int yEnemy;
    private double distance;
    private double velX = 0, velY = 0;
    private int spawnX, spawnY;

    private static final int MELEE_WIDTH_DEFAULT = 32;
    private static final int MELEE_HEIGHT_DEFAULT = 32;

    public static final int MELEEMONSTER_WIDTH = (int) (MELEE_WIDTH_DEFAULT * Game.SCALE);
    public static final int MELEEMONSTER_HEIGHT = (int) (MELEE_HEIGHT_DEFAULT * Game.SCALE);

    public MeleeMonster(GamePanel gamePanel, int x, int y, int spawnX, int spawnY) {
        super(x, y, MELEEMONSTER_WIDTH, MELEEMONSTER_HEIGHT, MELEE_MONSTER);
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
        distance = Math.sqrt((Math.pow((gamePanel.player.getAbsXPlayer() + 615 - xEnemy - spawnX + 1280 + 24),2) + Math.pow((gamePanel.player.getAbsYPlayer()+ 325 - yEnemy -spawnY + 720 + 24), 2)));
        if (distance < 600 & distance > 10) {
            velX = enemyMoveHelper(xEnemy - 615 - 1280,gamePanel.player.getAbsXPlayer()- spawnX + 24);
            velY = enemyMoveHelper(yEnemy - 325 - 720,gamePanel.player.getAbsYPlayer() - spawnY + 24);
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
