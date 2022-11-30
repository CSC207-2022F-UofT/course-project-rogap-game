package Entities;

import Interface_Adapters.Game;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

public abstract class Monster extends Creature {
    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected float walkSpeed = 0.35f * Game.SCALE;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.TILES_SIZE; // attacking distance is one tile
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true; // alive
    protected boolean attackChecked;

    public Monster(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;

    }

    protected void firstUpdateCheck(int[][] lvlData) {
        firstUpdate = false;
    }

    protected void move(int[][] lvlData) {
        float xSpeed = 0;

        if (walkDir == LEFT)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;

        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                hitbox.x += xSpeed;
                return;
            }

        changeWalkDir();
    }

    protected void turnTowardsPlayer(Player player) {
        if (player.hitRadius.x > hitbox.x)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        int playerTileY = (int) (player.getAttackRadius().y / Game.TILES_SIZE);
        if (playerTileY == tileY)
            if (isPlayerInRange(player)) {
                if (IsSightClear(lvlData, hitbox, player.hitRadius, tileY))
                    return true;
            }
        return false;
    }

    protected boolean isPlayerInRange(Player player) {
        int absXValue = (int) Math.abs(player.hitRadius.x - hitbox.x);
        int absYValue = (int) Math.abs(player.hitRadius.y - hitbox.y);
        return (absXValue <= attackDistance || absYValue <= attackDistance);
    }

    protected boolean isPlayerCloseForAttack(Player player) {
        int absXValue = (int) Math.abs(player.hitRadius.x - hitbox.x);
        int absYValue = (int) Math.abs(player.hitRadius.y - hitbox.y);
        return (absXValue <= attackDistance - 22 || absYValue <= attackDistance - 22);
    }

    public void newState(int enemyState) {
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0; // when new state, reset the tick and index to show state animation from the start
    }

    public void hurt(int amount) {
        currentHealth -= amount;
        if (currentHealth <= 0) {
            newState(DEAD);
        } else {
            newState(HIT);
        }
    }


    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        Area enemyAttackArea = new Area(attackBox); // find area of enemy attackbox
        enemyAttackArea.intersect(new Area(player.hitRadius)); // find intersection between enemy attackbox and player hitradius
        if (!enemyAttackArea.isEmpty()) { // if they intersect, execute following lines
            player.setHit(true);
            player.changeHealth(-GetEnemyDmg(enemyType));
        }
        attackChecked = true;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, enemyState)) {
                aniIndex = 0;


                switch (enemyState) { // change enemy state to idle as soon as specific action is done
                    case ATTACK:
                    case HIT:
                        enemyState = IDLE;
                        break;
                    case DEAD:
                        active = false;
                        break;
                }
            }
        }
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();

    }

    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            firstUpdate = false;
        }

        switch (enemyState) {
            case IDLE:
                enemyState = RUNNING;
                break;
            case RUNNING:
                float xSpeed = 0;

                if (walkDir == LEFT)
                    xSpeed = -walkSpeed;
                else
                    xSpeed = walkSpeed;

                if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
                    if (IsFloor(hitbox, xSpeed, lvlData)) {
                        hitbox.x += xSpeed;
                        return;
                    }

                changeWalkDir();

                break;
        }
    }


    private void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;

    }

    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(IDLE);
        active = true;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    public boolean isActive() {
        return active;
    }

}