package Use_Cases;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class PlayerAttackInteractor implements PlayerAttackInputBoundary{

    private PlayerAttack playerAttack;
    private Ellipse2D.Float playerAttackRadius;
    private MeleeAttack[] meleeEnemies;
    private RangedAttack[] rangedEnemies;

    public PlayerAttackInteractor(PlayerAttack playerAttack, MeleeAttack[] meleeEnemies, RangedAttack[] rangedEnemies) {
        this.playerAttack = playerAttack;
        this.playerAttackRadius = playerAttack.getAttackRadius();
        this.meleeEnemies = meleeEnemies;
        this.rangedEnemies = rangedEnemies;
    }

    @Override
    public void attack() {
        // TODO: set Player state to attacking
        playerAttack.setAttacking(true);

        for (MeleeAttack m : meleeEnemies) {
            Area playerAttackRadius = new Area(this.playerAttackRadius);     // find area of Player's attack box
            playerAttackRadius.intersect(new Area(m.getHitRadius()));      // find intersection between Player attackbox and enemy hitbox
            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                m.setHit(true);
                m.takeDamage();   // TODO!!!!!!!!!
                // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }
        for (RangedAttack r : rangedEnemies) {
            Area playerAttackRadius = new Area(this.playerAttackRadius);     // find area of Player's attack box
            playerAttackRadius.intersect(new Area(r.getHitRadius()));      // find intersection between Player attackbox and enemy hitbox
            if (!playerAttackRadius.isEmpty()) {  // player attack box and monster hitbox do intersect
                r.setHit(true);
                r.takeDamage();   // TODO!!!!!!!!!!
                // takes damage and updates animation (HIT or DEAD)
                return;
            }
        }
    }

    @Override
    public BufferedImage getCurrAttackHitAnimation() {
        return playerAttack.getCurrentImage();
    }

    @Override
    public void drawPlayerHitRadius(Graphics g) {
        playerAttack.drawAttackRadius(g);
    }

}
