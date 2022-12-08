package Interface_Adapters;

import Use_Cases.MonsterAttackInputBoundary;
import Use_Cases.PlayerAttackInputBoundary;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AttackController {
    PlayerAttackInputBoundary playerAttackInputBoundary;
    MonsterAttackInputBoundary monsterAttackInputBoundary;

    public AttackController(PlayerAttackInputBoundary playerAttackInputBoundary,
                            MonsterAttackInputBoundary monsterAttackInputBoundary) {
        this.playerAttackInputBoundary = playerAttackInputBoundary;
        this.monsterAttackInputBoundary = monsterAttackInputBoundary;
    }

    public void playerAttack() {
        playerAttackInputBoundary.attack();
    }

    public void monsterAttack() {  // called on game loop (every loop)
        monsterAttackInputBoundary.attack();
    }

    public BufferedImage getPlayerAttackHitAnimation() {
        return playerAttackInputBoundary.getCurrAttackHitAnimation();
    }

    public BufferedImage getMeleeAttackHitAnimation() {
        return monsterAttackInputBoundary.getMeleeAttackHitAnimation();
    }

    public BufferedImage getRangedAttackHitAnimation() {
        return monsterAttackInputBoundary.getRangedAttackHitAnimation();
    }

    public void drawPlayerHitRadius(Graphics g) {
        playerAttackInputBoundary.drawPlayerHitRadius(g);
    }

}
