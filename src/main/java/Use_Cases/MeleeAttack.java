package Use_Cases;

import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class MeleeAttack extends MonsterAttack{

    private Ellipse2D.Float attackRadius;

    public MeleeAttack(BufferedImage[][] monsterAttackHitAnimations) {
        super(monsterAttackHitAnimations);
    }
}
