package Use_Cases;

import java.awt.geom.Ellipse2D;

public class PlayerAttack extends Attack{

    private static Ellipse2D.Float hitRadius;
    private static Ellipse2D.Float attackRadius;

    public PlayerAttack() {
    }

    @Override
    public void initHitRadius() {
        // htiRadius is created at position (615, 325) with size 40x40
        hitRadius = new Ellipse2D.Float(615, 325, 48, 48);
    }

    @Override
    public Ellipse2D.Float getHitRadius() {
        return hitRadius;
    }

    @Override
    public void updateHitRadius() {
        // There is no need to update hitRadius since the Player's position relative to the screen is always the same
    }

    @Override
    public void initAttackRadius() {
        attackRadius = new Ellipse2D.Float(590, 300, 100, 100);
    }

    @Override
    public Ellipse2D.Float getAttackRadius() {
        return attackRadius;
    }

    @Override
    public void updateAttackRadius() {
        // attack radius stays the same
    }
}
