package Entities;

import Interface_Adapters.Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;

public class MeleeMonster extends Monster {

    // AttackBox
//    private Rectangle2D.Float attackBox;
    private Ellipse2D.Float attackRadius;

    private int attackBoxOffset;

    public MeleeMonster(int x, int y) {
        super(x, y, MONSTER_WIDTH, MONSTER_HEIGHT, RANGED_MONSTER);
        initHitbox(x, y, (int) (13 * Game.SCALE), (int) (17 * Game.SCALE)); //13 is width of monster; 17 is the height
        initAttackBox();
    }

    private void initAttackBox() {
//        attackBox = new Rectangle2D.Float(x , y,(int) (14 * Game.SCALE), (int) (18 * Game.SCALE));
        attackRadius = new Ellipse2D.Float(x , y,(int) (14 * Game.SCALE), (int) (18 * Game.SCALE));
        attackBoxOffset = (int) (Game.SCALE * 1);
    }

//    public Rectangle2D.Float getAttackBox() {
//        return attackBox;
//    }

    public Ellipse2D.Float getAttackRadius() {
        return attackRadius;
    }

    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
//        updateAttackBox();
        updateAttackRadiius();
    }

//    private void updateAttackBox() {
//        attackBox.x = hitbox.x - attackBoxOffset; // used to center the attackbox around the hitbox
//        attackBox.y = hitbox.y - attackBoxOffset;
//    }

    private void updateAttackRadiius() {
        attackRadius.x = hitbox.x - attackBoxOffset; // used to center the attackbox around the hitbox
        attackRadius.y = hitbox.y - attackBoxOffset;
    }

    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate)
            firstUpdateCheck(lvlData);

        switch (enemyState) {
            case IDLE:
                newState(RUNNING);
                break;
            case RUNNING:
                if (canSeePlayer(lvlData, player))
                    turnTowardsPlayer(player);
                if (isPlayerCloseForAttack(player))
                    newState(ATTACK);

                move(lvlData);
                break;
            case ATTACK:
                if (aniIndex == 0)
                    attackChecked = false; // ensures that whenever animation restarts, the attack checked resets so that enemy attacks again

                if (aniIndex == 3 && !attackChecked) // player is attacked at sprite #3
                    // attackChecked makes sure we only do one check per animation
//                    checkEnemyHit(attackBox, player);
                    checkEnemyHit(attackRadius, player);
                break;
            case HIT:
                break;
        }

    }

//    public void drawAttackBox(Graphics g, int xLvlOffset) {
//        g.setColor(Color.red);
//        g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
//    }

    public void drawAttackRadius(Graphics g, int xLvlOffset) {
        g.setColor(Color.red);
        g.drawOval((int) (attackRadius.x - xLvlOffset), (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
    }

    // if walking left change sprite direction
    public int flipX() {
        if (walkDir == LEFT)
            return width;
        else
            return 0;
    }

    public int flipW() {
        if (walkDir == LEFT)
            return -1;
        else
            return 1;
    }




}