package Frameworks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class AnimationImport {
    private BufferedImage[][] animations;


//    private BufferedImage[] sprites = new BufferedImage[10];
    private BufferedImage[][] animations2;
    private BufferedImage[] sprites2 = new BufferedImage[6];
    private BufferedImage[][] enemyAnimations;
    private BufferedImage[] sprites = new BufferedImage[8];

    /**
     * This class is designated to import all the player animation
     */
    private void importImage() {
        InputStream lI = getClass().getResourceAsStream("/leftIdle.png");
        InputStream rI = getClass().getResourceAsStream("/rightIdle.png");
        InputStream lM = getClass().getResourceAsStream("/leftMovement.png");
        InputStream rM = getClass().getResourceAsStream("/rightMovement.png");

        InputStream lA = getClass().getResourceAsStream("/playerAttackLeft.png");
        InputStream rA = getClass().getResourceAsStream("/playerAttackRight.png");
        InputStream lH = getClass().getResourceAsStream("/playerHitLeft.png");
        InputStream rH = getClass().getResourceAsStream("/playerHitRight.png");
        InputStream lD = getClass().getResourceAsStream("/playerDeadLeft.png");
        InputStream rD = getClass().getResourceAsStream("/playerDeadRight.png");

        InputStream lIM = getClass().getResourceAsStream("/leftIdleMonster.png");
        InputStream rIM = getClass().getResourceAsStream("/rightIdleMonster.png");
        InputStream lMM = getClass().getResourceAsStream("/leftMovementMonster.png");
        InputStream rMM = getClass().getResourceAsStream("/rightMovementMonster.png");
        try {
            assert lI != null & rI != null & lM != null & rM != null &
                     lIM != null & rIM != null & lMM != null & rMM != null &
                    lA != null & rA != null & lH != null & rH != null &
                    lD != null & rD != null;
            sprites[0] = ImageIO.read(lI);
            sprites[1] = ImageIO.read(rI);
            sprites[2] = ImageIO.read(lM);
            sprites[3] = ImageIO.read(rM);
            sprites[4] = ImageIO.read(lIM);
            sprites[5] = ImageIO.read(rIM);
            sprites[6] = ImageIO.read(lMM);
            sprites[7] = ImageIO.read(rMM);

//            sprites2[0] = ImageIO.read(lA);
//            sprites2[1] = ImageIO.read(rA);
//            sprites2[2] = ImageIO.read(lH);
//            sprites2[3] = ImageIO.read(rH);
//            sprites2[4] = ImageIO.read(lD);
//            sprites2[5] = ImageIO.read(rD);
//            sprites[4] = ImageIO.read(lA);
//            sprites[5] = ImageIO.read(rA);
//            sprites[6] = ImageIO.read(lH);
//            sprites[7] = ImageIO.read(rH);
//            sprites[8] = ImageIO.read(lD);
//            sprites[9] = ImageIO.read(rD);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lI.close();
                rM.close();
                rI.close();
                lM.close();
                lMM.close();
                rIM.close();
                rMM.close();
                lIM.close();

//                lA.close();
//                rA.close();
//                lH.close();
//                rH.close();
//                lD.close();
//                rD.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This class loads the player animation to a BufferedImage[][]
     */
    private void loadAnimation() {
        animations = new BufferedImage[4][6];
        for (int j = 0; j < animations.length; j++){
            for (int i = 0; i < animations[j].length; i++) {
                if (j <= 1) {
                    animations[j][i] = sprites[j].getSubimage(i*32, 0,32,32);
                } else if (j >= 2 & i <= 4) {
                    animations[j][i] = sprites[j].getSubimage(i*32, 0,32,32);
                }
            }
        }
    }
    private void loadAnimationEnemy() {
        enemyAnimations = new BufferedImage[4][6];
        for (int j = 0; j < enemyAnimations.length; j++){
            for (int i = 0; i < enemyAnimations[j].length; i++) {
                if (j <= 1) {
                    enemyAnimations[j][i] = sprites[j + 4].getSubimage(i*32, 0,32,32);
                } else if (j >= 2 & i <= 4) {
                    enemyAnimations[j][i] = sprites[j + 4].getSubimage(i*32, 0,32,32);
                }
            }
        }
    }
    /**
     * This class returns the loaded player animation
     * @return BufferedImage[][]
     */
    public BufferedImage[][] getPlayerAnimations() {
        importImage();
        loadAnimation();
        return animations;
    }
    public BufferedImage[][] getEnemyAnimations() {
        importImage();
        loadAnimationEnemy();
        return enemyAnimations;
    }
    /**
     * This class loads the player attack and damage animation to a BufferedImage[][]
     */
//    private void loadAnimation2() {
//        animations2 = new BufferedImage[6][7];
//        for (int j = 0; j < animations2.length; j++){
//            for (int i = 0; i < animations2[j].length; i++) {
//                if (j <= 1) {
//                    animations2[j][i] = sprites2[j].getSubimage(i*32, 0,32,32);
//                } else if (j >= 2 & i <= 4) {
//                    animations2[j][i] = sprites2[j].getSubimage(i*32, 0,32,32);
//                }
//            }
//        }
//    }

    /**
     * This class returns the loaded attack and damage player animation
     * @return BufferedImage[][]
     */
//    public BufferedImage[][] getPlayerAnimations2() {
//        importImage();
//        loadAnimation2();
//        return animations2;
//    }

}
