package Entities;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static Entities.MonsterConstants.LEFT_ATTACK;
import static Entities.MonsterConstants.RIGHT_ATTACK;

public class RangedEnemy extends Monster{

    private Ellipse2D.Float attackRadius;

    public RangedEnemy(GamePanel gamePanel, int x, int y, int spawnX, int spawnY) {
        super(gamePanel, x, y, spawnX, spawnY);
        initAttackRadius();
    }

    public void update() {
//        if (!getHitBox().intersects(gamePanel.player.getHitBox())){
//            enemyMovement();
//        }

        // changed the method of finding the intersection to accommodate for the
        // hitbox shape change (from rect to ellipse)
        Area hitBoxArea = new Area(attackHitBox);     // find area of Monster's hitBox
        hitBoxArea.intersect(new Area(gamePanel.player.getHitBox())); // find intersection between monster's hitBox and player's hitbox
        if (hitBoxArea.isEmpty()) {         // if they do not intersect (the intersection is empty)
            enemyMovement();
        }

        updateHitBoxAttack();
        // update ani tick
        updateBehavior();

    }

    protected void enemyMovement() { //In order to update current enemy location must update absXenemy.
        distance = Math.sqrt((Math.pow((gamePanel.player.getAbsXPlayer() - xEnemy - spawnX + 1896),2) +
                Math.pow((gamePanel.player.getAbsYPlayer() - yEnemy -spawnY + 1046), 2)));
        if (distance < 600 & distance > 110) {
            System.out.println(yEnemy);
            velX = enemyMoveHelper(xEnemy - 616 - 1280,gamePanel.player.getAbsXPlayer() - spawnX);
            velY = enemyMoveHelper(yEnemy - 326 - 720,gamePanel.player.getAbsYPlayer() - spawnY);
            ArrayList wall = currMoveCollision(velX, velY);
            if ((Boolean) wall.get(0)) {
                xEnemy -= velX;
                spawnX -= velX;
                yEnemy -= velY;
                spawnY -= velY;
            } else {
                //TODO: make enemies move randomly while it is touching the border.
            }
        } else if (distance < 110) {
            newState(LEFT_ATTACK); // TODO: add conditions for the direction the enemy is facing
        } else {
            //TODO: make enemies move randomly while player is not close
        }

    }

    private void initAttackRadius() {
        attackRadius = new Ellipse2D.Float(attackHitBox.x, attackHitBox.y,
                attackHitBox.width, attackHitBox.height);
    }

    // for debugging
    public void drawAttackBox(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int) attackRadius.x, (int) attackRadius.y,
                (int) attackRadius.width, (int) attackRadius.height);
    }

    private void updateBehavior() {
        Player player = gamePanel.getPlayer();
        switch (enemyState) {
            case LEFT_ATTACK, RIGHT_ATTACK ->
                    checkPlayerHit(attackRadius, player);
        }
    }

    private void checkPlayerHit(Ellipse2D.Float attackRadius, Player player) {
        Area enemyAttackArea = new Area(attackRadius); // find area of enemy attackbox
        enemyAttackArea.intersect(new Area(player.getHitBox())); // find intersection between enemy attack radius and player hitradius
        if (!enemyAttackArea.isEmpty()) { // if they intersect:
            player.setHit(true);
            player.changeHealth(5);  // TODO: determine player damage value
        }
//        attackChecked = true;
    }


}
