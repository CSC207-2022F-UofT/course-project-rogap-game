package Use_Cases;

import Entities.MeleeEnemy;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.BufferedImage;

public class MeleeAttack extends MonsterAttack{

    private MeleeEnemy meleeEnemy;
    private Ellipse2D.Float attackRadius;

    public MeleeAttack(MeleeEnemy meleeEnemy) {
        this.meleeEnemy = meleeEnemy;
    }

    @Override
    public void updatePlayer() {

    }

    @Override
    public void updateMonsters() {

    }

//    public void drawAttackRadius(Graphics g) {
//        g.setColor(Color.red);
//        g.drawOval((int) attackRadius.x, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
//    }
}
