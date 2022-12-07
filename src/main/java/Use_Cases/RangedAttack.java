package Use_Cases;

import java.awt.geom.Ellipse2D;

public class RangedAttack extends Attack{

    private Ellipse2D.Float hitRadius;
    private Ellipse2D.Float attackRadius;

    @Override
    public void initHitRadius() {
        // TODO: need to make getHelperX and getHelperY static
        hitRadius = new Ellipse2D.Float(EnemyMovement.getHelperX(), EnemyMovement.getHelperY(), 48, 48);
    }

    @Override
    public Ellipse2D.Float getHitRadius() {
        return hitRadius;
    }

    @Override
    public void updateHitRadius() {
        hitRadius.x = EnemyMovement.getHelperX();
        hitRadius.y = EnemyMovement.getHelperY();
    }

    /**
     * Initializes the Ranged Monster's attack radius. This is an ellipse which is centered at the monster. The position
     * is the same as the hit radius but the size is different since this type of monster attacks through range.
     */
    @Override
    public void initAttackRadius() {
        attackRadius = new Ellipse2D.Float(hitRadius.x, hitRadius.y, 48, 48);
    }

    /**
     * Returns attack radius
     * @return Ellipse2D.Float attackRadius
     */
    @Override
    public Ellipse2D.Float getAttackRadius() {
        return attackRadius;
    }

    /**
     * Updates the position of the attack radius using the position of the hit radius.
     */
    @Override
    public void updateAttackRadius() {
        attackRadius.x = hitRadius.x;
        attackRadius.y = hitRadius.y;
    }

    public void takeDamage() {
        // decrease health
    }

}
