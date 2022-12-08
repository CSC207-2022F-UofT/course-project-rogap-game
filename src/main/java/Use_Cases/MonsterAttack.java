package Use_Cases;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class MonsterAttack extends Attack{

    private int aniTick, aniIndex, aniSpeed= 10;
    private boolean attacking = false, hit = false;
    private int monsterAction = 0; // TODO might have to change this to some other number so that it doesnt think its attacking
    private Ellipse2D.Float hitRadius;
    private BufferedImage[][] monsterAttackHitAnimations;

    public MonsterAttack(BufferedImage[][] monsterAttackHitAnimations) {
        this.monsterAttackHitAnimations = monsterAttackHitAnimations;
    }

//    @Override
//    public void initHitRadius() {
//        // TODO: need to make getHelperX and getHelperY static
//        hitRadius = new Ellipse2D.Float(EnemyMovement.getHelperX(), EnemyMovement.getHelperY(), 48, 48);
//    }

    @Override
    public Ellipse2D.Float getHitRadius() {
        hitRadius = new Ellipse2D.Float(EnemyMovement.getHelperX(), EnemyMovement.getHelperY(), 48, 48);
        return hitRadius;
    }

//    @Override
//    public void updateHitRadius() {
//        hitRadius.x = EnemyMovement.getHelperX();
//        hitRadius.y = EnemyMovement.getHelperY();
//    }

    /**
     * Initializes the Melee Monster's attack radius. This is an ellipse which is centered at the monster. The position
     * and size is same as the hit radius since this type of monster attacks by contact.
     */
//    @Override
//    public void initAttackRadius() {
//        attackRadius = new Ellipse2D.Float(hitRadius.x, hitRadius.y, 48, 48);
//    }

    /**
     * Returns attack radius
     * @return Ellipse2D.Float attackRadius
     */
    @Override
    public Ellipse2D.Float getAttackRadius() {
        return null;
    }

    /**
     * Updates the position of the attack radius using the position of the hit radius.
     */
//    @Override
//    public void updateAttackRadius() {
//        attackRadius.x = hitRadius.x;
//        attackRadius.y = hitRadius.y;
//    }

    @Override
    public void setAttackHitAnimation() {
        if (attacking) {
            // TODO check for attacking direction
            monsterAction = 0;
        }
        if (hit) {
            // TODO check for hit direction
            monsterAction = 2;
        }
    }

    @Override
    public int getSpriteAmount(int creatureAction) {
        switch (monsterAction) {
            case 0:
            case 1:
                return 5;
            case 2:
            case 3:
                return 6;
        }
        return 0;
    }

    @Override
    public void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(monsterAction)) {
                aniIndex = 0;
            }
        }
    }

    @Override
    public BufferedImage getCurrentImage() {
        updateAnimationTick();
        setAttackHitAnimation();
        return this.monsterAttackHitAnimations[monsterAction][aniIndex];
    }

    @Override
    public void drawAttackRadius(Graphics g) {
    }

    public void setAttacking(boolean value) {
        this.attacking = value;
    }

    public void setHit(boolean value) {
        this.hit = value;
    }

    public void takeDamage() {
        // decrease health
    }
}
