package Use_Cases;

import java.awt.geom.Ellipse2D;

public abstract class Attack {
    /**
     * Initializes the creature's hit radius centered at the creature. The hit radius is of type Ellipse2D.Float and
     * outlines the shape of the creature.
     */
    public abstract void initHitRadius();

    /**
     * Returns the hit radius of the creature.
     * @return Ellipse2D.Float hitRadius
     */
    public abstract Ellipse2D.Float getHitRadius();

    /**
     * Updates the hit radius position based on the creature's current position.
     */
    public abstract void updateHitRadius();

    /**
     * Initializes the creature's attack radius centered at the creature.
     */
    public abstract void initAttackRadius();

    /**
     * Returns the creature's attack radius.
     * @return Ellipse2D.Float attackRadius
     */
    public abstract Ellipse2D.Float getAttackRadius();

    /**
     * Updates the attack radius position based on the creature's hit radius position.
     */
    public abstract void updateAttackRadius();

}
