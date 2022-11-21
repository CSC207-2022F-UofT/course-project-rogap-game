package Entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.GetMaxHealth;

public abstract class Monster extends Creature {
    // types of monsters
    protected static final int MELEE_MONSTER = 0;
    protected static final int RANGED_MONSTER = 1;

    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected float walkSpeed = 0.35f; // * Game.SCALE;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.TILES_SIZE; // attacking distance is one tile
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true; // alive
    protected boolean attackChecked;

    protected Rectangle2D.Float hitbox;

    public Monster(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;

    }

    protected void drawHitbox(Graphics g, int xLvlOffset) {
        // For debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    protected void initHitbox(float x, float y, int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void checkEnemyHit(Ellipse2D.Float attackRadius) { // check whether monster has been attacked by Player
        if (attackRadius.intersects(getHitbox())) {
            takeDamage(10);
            // update animation for HIT
            return;

        // add checks for when the current health reaches 0
        // - update animation for DEAD
    }


}

    private void takeDamage(int d) {
        this.currentHealth -= d;
    }



}
