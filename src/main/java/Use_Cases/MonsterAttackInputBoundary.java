package Use_Cases;

import java.awt.image.BufferedImage;

public interface MonsterAttackInputBoundary {
    void attack();
    BufferedImage getMeleeAttackHitAnimation();
    BufferedImage getRangedAttackHitAnimation();
}
