package Gabi_attack_method_code;

import gamestates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[][] rangedArr;
    private ArrayList<RangedMonster> rangedMonsters = new ArrayList<>();
    private BufferedImage[][] meleeArr;
    private ArrayList<MeleeMonster> meleeMonsters = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {
        rangedMonsters = LoadSave.GetRangedMonsters();
        meleeMonsters = LoadSave.GetMeleeMonsters();

    }

    public void update(int[][] lvlData, Player player) {
        for (RangedMonster r : rangedMonsters)
            if (r.isActive())
                r.update(lvlData, player);
        for (MeleeMonster m : meleeMonsters)
            if (m.isActive())
                m.update(lvlData, player);
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawRangedMonster(g, xLvlOffset);
        drawMeleeMonster(g, xLvlOffset);
    }

    private void drawRangedMonster(Graphics g, int xLvlOffset) {
        for (RangedMonster r : rangedMonsters)
            if (r.isActive()){
                g.drawImage(rangedArr[r.getEnemyState()][r.getAniIndex()],
                        (int) r.getHitbox().x - xLvlOffset - MONSTER_DRAWOFFSET_X + r.flipX(),
                        (int) r.getHitbox().y - MONSTER_DRAWOFFSET_Y,
                        MONSTER_WIDTH * r.flipW(), MONSTER_HEIGHT, null);
			    r.drawHitbox(g, xLvlOffset);
                r.drawAttackBox(g, xLvlOffset);
            }
    }

    private void drawMeleeMonster(Graphics g, int xLvlOffset) {
        for (MeleeMonster m : meleeMonsters)
            if (m.isActive()){
                g.drawImage(meleeArr[m.getEnemyState()][m.getAniIndex()],
                        (int) m.getHitbox().x - xLvlOffset - MONSTER_DRAWOFFSET_X + m.flipX(),
                        (int) m.getHitbox().y - MONSTER_DRAWOFFSET_Y,
                        MONSTER_WIDTH * m.flipW(), MONSTER_HEIGHT, null);
                m.drawHitbox(g, xLvlOffset);
                m.drawAttackBox(g, xLvlOffset);
            }
    }

    public void checkEnemeyHit(Ellipse2D.Float attackRadius) {
        for (RangedMonster r : rangedMonsters)
            if (r.isActive()){
                if (attackRadius.intersects(r.getHitbox())) {
                    r.hurt();
                    return;
                }
            }
        for (MeleeMonster m : meleeMonsters)
            if (m.isActive()){
                if (attackRadius.intersects(m.getHitbox())) {
                    m.hurt();
                    return;
                }
            }
    }

    private void loadEnemyImgs() {
        rangedArr = new BufferedImage[5][4];
        BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.RANGED_MONSTER_SPRITE);
        for (int j = 0; j < rangedArr.length; j++)
            for (int i = 0; i < rangedArr[j].length; i++)
                rangedArr[j][i] = temp2.getSubimage(i * MONSTER_WIDTH_DEFAULT, j * MONSTER_HEIGHT_DEFAULT,
                        MONSTER_WIDTH_DEFAULT, MONSTER_HEIGHT_DEFAULT);

        meleeArr = new BufferedImage[5][4];
        BufferedImage temp3 = LoadSave.GetSpriteAtlas(LoadSave.MELEE_MONSTER_SPRITE);
        for (int j = 0; j < meleeArr.length; j++)
            for (int i = 0; i < meleeArr[j].length; i++)
                meleeArr[j][i] = temp3.getSubimage(i * MONSTER_WIDTH_DEFAULT, j * MONSTER_HEIGHT_DEFAULT,
                        MONSTER_WIDTH_DEFAULT, MONSTER_HEIGHT_DEFAULT);
    }

    public void resetAllEnemies() {
        for (RangedMonster r : rangedMonsters)
            r.resetEnemy();
        for (MeleeMonster m : meleeMonsters)
            m.resetEnemy();
    }



}