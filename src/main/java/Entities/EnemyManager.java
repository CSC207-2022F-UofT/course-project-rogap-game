//package Entities;
//
//import main.Game;
//import utilz.LoadSave;
//
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//
//import static utilz.Constants.EnemyConstants.*;
//
//public class EnemyManager {
//
//    private Game game;
////    private BufferedImage[][] crabbyArr;
////    private ArrayList<Crabby> crabbies = new ArrayList<>();
////    private BufferedImage[][] rangedArr;
////    private ArrayList<RangedMonster> meleeMonsters = new ArrayList<>();
////    private ArrayList<RangedMonster> rangedMonsters = new ArrayList<>();
//
//    public static final String MELEE_MONSTER_SPRITE = "melee_sprite3.png";
////    public static final String RANGED_MONSTER_SPRITE = "outside_sprites.png";
//
//    public EnemyManager(Game game) {
//        this.game = game;
//        loadEnemyImgs();
//        addEnemies();
//    }
//
//    private void addEnemies() {
//        crabbies = LoadSave.GetCrabs();
//        rangedMonsters = LoadSave.GetRangedMonsters();
//
//    }
//    public static ArrayList<MeleeMonster> GetRangedMonsters() {
//        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
//        ArrayList<MeleeMonster> list = new ArrayList<>();
//        for (int j = 0; j < img.getHeight(); j++)
//            for (int i = 0; i < img.getWidth(); i++) {
//                Color color = new Color(img.getRGB(i, j));
//                int value = color.getBlue();
//                if (value == (RANGED_MONSTER - 1)) // !!!! SIPA TROCAR RANGED_MONSTER P 0 TBM
//                    list.add(new MeleeMonster(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
//            }
//        return list;
//
//    }
//
//    public void update(int[][] lvlData, Player player) {
//        for (Crabby c : crabbies)
//            if (c.isActive())
//                c.update(lvlData, player);
//        for (RangedMonster r : rangedMonsters)
//            if (r.isActive())
//                r.update(lvlData, player);
//    }
//
//    public void draw(Graphics g, int xLvlOffset) {
//        drawCrabs(g, xLvlOffset);
//        drawRangedMonster(g, xLvlOffset);
//    }
//
//    private void drawCrabs(Graphics g, int xLvlOffset) {
//        for (Crabby c : crabbies)
//            if (c.isActive()){
//                g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()],
//                        (int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
//                        (int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y,
//                        CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
////			c.drawHitbox(g, xLvlOffset);
//                c.drawAttackBox(g, xLvlOffset);
//        }
//    }
//
//    private void drawRangedMonster(Graphics g, int xLvlOffset) {
//        for (RangedMonster r : rangedMonsters)
//            if (r.isActive()){
//                g.drawImage(rangedArr[r.getEnemyState()][r.getAniIndex()],
//                        (int) r.getHitbox().x - xLvlOffset + r.flipX(),
//                        (int) r.getHitbox().y,
//                        RANGEDMONSTER_WIDTH * r.flipW(), RANGEDMONSTER_HEIGHT, null);
//			    r.drawHitbox(g, xLvlOffset);
//                r.drawAttackBox(g, xLvlOffset);
//            }
//    }
//
//    public void checkEnemeyHit(Ellipse2D.Float attackRadius) {
//        for (Crabby c : crabbies)
//            if (c.isActive()){
//                if (attackRadius.intersects(c.getHitbox())) {
//                    c.hurt(10);
//                    return;
//            }
//        }
//        for (RangedMonster r : rangedMonsters)
//            if (r.isActive()){
//                if (attackRadius.intersects(r.getHitbox())) {
//                    r.hurt(10);
//                    return;
//                }
//            }
//    }
//
//    private void loadEnemyImgs() {
//        crabbyArr = new BufferedImage[5][9];
//        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
//        for (int j = 0; j < crabbyArr.length; j++)
//            for (int i = 0; i < crabbyArr[j].length; i++)
//                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT,
//                        CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
//
//        rangedArr = new BufferedImage[5][4];
//        BufferedImage temp2 = LoadSave.GetSpriteAtlas(LoadSave.MELEE_MONSTER_SPRITE);
//        for (int j = 0; j < rangedArr.length; j++)
//            for (int i = 0; i < rangedArr[j].length; i++)
//                rangedArr[j][i] = temp2.getSubimage(i * RANGED_WIDTH_DEFAULT, j * RANGED_HEIGHT_DEFAULT,
//                        RANGED_WIDTH_DEFAULT, RANGED_HEIGHT_DEFAULT);
//    }
//
//    public void resetAllEnemies() {
//        for (Crabby c : crabbies)
//            c.resetEnemy();
//
//        for (RangedMonster r : rangedMonsters)
//            r.resetEnemy();
//    }
//}