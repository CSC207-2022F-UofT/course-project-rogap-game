package Use_Cases;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class RangedAttack extends MonsterAttack{

    private Ellipse2D.Float attackRadius;

    public RangedAttack(BufferedImage[][] monsterAttackHitAnimations) {
        super(monsterAttackHitAnimations);
    }

    @Override
    public Ellipse2D.Float getAttackRadius() {
        attackRadius = new Ellipse2D.Float(getHitRadius().x, getHitRadius().y, 100, 100);
        return attackRadius;
    }

    @Override
    public void drawAttackRadius(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int) attackRadius.x, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
    }

    @Override
    public void updatePlayer() {

    }

    @Override
    public void updateMonsters() {

    }
}
