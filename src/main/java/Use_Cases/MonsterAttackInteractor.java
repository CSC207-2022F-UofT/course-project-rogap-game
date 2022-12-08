package Use_Cases;

import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class MonsterAttackInteractor implements MonsterAttackInputBoundary{
    private MeleeAttack[] meleeEnemies;
    private RangedAttack[] rangedEnemies;
    private PlayerAttack playerAttack;

    public MonsterAttackInteractor(MeleeAttack[] meleeEnemies, RangedAttack[] rangedEnemies,
                                   PlayerAttack playerAttack) {
        this.meleeEnemies = meleeEnemies;
        this.rangedEnemies = rangedEnemies;
        this.playerAttack = playerAttack;
    }


    @Override
    public void attack() {
        for (MeleeAttack m : meleeEnemies) { // TODO: change this with enemy manager
            Area meleeAttackRadius = new Area(m.getAttackRadius());     // find area of enemy's attack radius
            meleeAttackRadius.intersect(new Area(playerAttack.getHitRadius())); // find intersection between Player hit radius and enemy attack radius
            if (!meleeAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                m.setAttacking(true);
                playerAttack.setHit(true);
                playerAttack.takeDamage();
                // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }
        for (RangedAttack r : rangedEnemies) {
            Area rangedAttackRadius = new Area(r.getAttackRadius());     // find area of enemy's attack radius
            rangedAttackRadius.intersect(new Area(playerAttack.getHitRadius())); // find intersection between Player hit radius and enemy attack radius
            if (!rangedAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                r.setAttacking(true);
                playerAttack.setHit(true);
                playerAttack.takeDamage();
                // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }
    }

    @Override
    public BufferedImage getMeleeAttackHitAnimation() {
        return meleeEnemies[0].getCurrentImage();  // TODO this should be called on all melee enemies
    }

    @Override
    public BufferedImage getRangedAttackHitAnimation() {
        return rangedEnemies[0].getCurrentImage(); // TODO this should be called on all ranged enemies
    }
}
