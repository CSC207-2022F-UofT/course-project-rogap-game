package Entities;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static Entities.MonsterConstants.*;

public class Monster{
    protected GamePanel gamePanel;
    protected int xEnemy;
    protected int yEnemy;
    protected double distance;
    protected int velX = 0, velY = 0;
    protected int spawnX, spawnY;
//    protected Rectangle hitBox;

    protected int aniTick, aniSpeed = 10;
    protected int aniIndex;

    // for attack methods
    protected Ellipse2D.Float hitBox, attackHitBox;
    protected int currentHealth;
    protected int maxHealth;
    protected int enemyState;
    public int PLAYER_ATTACK_DAMAGE;  // TODO: make this an attribute of player class

    public Monster(GamePanel gamePanel, int x, int y, int spawnX, int spawnY) {
        this.gamePanel = gamePanel;
        this.spawnY = spawnY;
        this.spawnX = spawnX;
        this.xEnemy = x + this.spawnX;
        this.yEnemy = y + this.spawnY;

        // added for attack methods
        maxHealth = 50;  // set enemy's max health
        initHitBoxAttack();
    }

    // change hitbox shape to an ellipse
    public Ellipse2D.Float getHitBox() {
//        hitBox = new Ellipse2D.Float(spawnX - 1280 + 4, spawnY - 720 + 4, 24, 24);// adjusted the monster hitbox sizes
        hitBox = new Ellipse2D.Float(spawnX - 1280 + 4, spawnY - 720 + 4, 48, 48);
        return hitBox;
    }

//    protected void enemyMovement() { //In order to update current enemy location must update absXenemy.
//        distance = Math.sqrt((Math.pow((gamePanel.player.getAbsXPlayer() - xEnemy - spawnX + 1896),2) + Math.pow((gamePanel.player.getAbsYPlayer() - yEnemy -spawnY + 1046), 2)));
//        if (distance < 600 & distance > 110) {
//            System.out.println(yEnemy);
//            velX = enemyMoveHelper(xEnemy - 616 - 1280,gamePanel.player.getAbsXPlayer() - spawnX);
//            velY = enemyMoveHelper(yEnemy - 326 - 720,gamePanel.player.getAbsYPlayer() - spawnY);
//            ArrayList wall = currMoveCollision(velX, velY);
//            if ((Boolean) wall.get(0)) {
//                xEnemy -= velX;
//                spawnX -= velX;
//                yEnemy -= velY;
//                spawnY -= velY;
//            } else {
//                //TODO: make enemies move randomly while it is touching the border.
//            }
//        } else if (distance < 110) {
//            //TODO: add a function that calls the attack for enemy.
//        } else {
//            //TODO: make enemies move randomly while player is not close
//        }
//
//    }

    protected ArrayList currMoveCollision(int x, int y) {
        return gamePanel.player.getWallCollision().moveAbleWall(xEnemy + 4, yEnemy + 4,
                x, y, 24, 24);
    }
    protected int enemyMoveHelper(int c, int targetC) {
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

    // for attack methods
    public void newState(int enemyState) {  // this function is called to update a monster's state
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0; // when new state, reset the tick and index to show state animation from the start
    }

    public void takeDamage() {      // TODO: update methods
        currentHealth -= PLAYER_ATTACK_DAMAGE;
        if (currentHealth <= 0) {
            newState(DEAD);
        } else {
            newState(HIT);
        }
    }

    private void initHitBoxAttack() {
        attackHitBox = new Ellipse2D.Float(xEnemy, yEnemy, 29, 29);
    }

    protected void updateHitBoxAttack() {
        attackHitBox.x = xEnemy;
        attackHitBox.y = yEnemy;
    }

    public void drawMonsterHitBox(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int) attackHitBox.x, (int) attackHitBox.y, (int) attackHitBox.width, (int) attackHitBox.height);
    }

}