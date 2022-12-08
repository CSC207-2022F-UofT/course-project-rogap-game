package Frameworks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

// TODO!!!!!!! update with new spritesss
public class MonsterAnimationImport {
    private BufferedImage[][] animations;
    private BufferedImage[] sprites = new BufferedImage[4];
    // attack animations
    private BufferedImage[][] attackHitAnimations;
    private BufferedImage[] attackHitSprites = new BufferedImage[2];

    private void importImage() {
        InputStream lI = getClass().getResourceAsStream("/leftIdle.png");
        InputStream rI = getClass().getResourceAsStream("/rightIdle.png");
        InputStream lM = getClass().getResourceAsStream("/leftMovement.png");
        InputStream rM = getClass().getResourceAsStream("/rightMovement.png");

        InputStream lA = getClass().getResourceAsStream("/meleeAttackLeft.png"); // TODO change with player sprites
        InputStream rA = getClass().getResourceAsStream("/meleeAttackRight.png"); // TODO change with player sprites
        try {
            assert lI != null & rI != null & lM != null & rM != null & lA != null & rA != null;
            sprites[0] = ImageIO.read(lI);
            sprites[1] = ImageIO.read(rI);
            sprites[2] = ImageIO.read(lM);
            sprites[3] = ImageIO.read(rM);

            // for attack animations
            attackHitSprites[0] = ImageIO.read(lA);
            attackHitSprites[1] = ImageIO.read(rA);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lI.close();
                rM.close();
                rI.close();
                lM.close();

                lA.close();
                rA.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
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
    public BufferedImage[][] getMonsterAnimations() {
        importImage();
        loadAnimation();
        return animations;
    }

    private void loadAttackHitAnimation() {
        attackHitAnimations = new BufferedImage[2][5];
        for (int j = 0; j < attackHitAnimations.length; j ++) {
            for (int i = 0; i < attackHitAnimations[j].length; i++) {
                attackHitAnimations[j][i] = attackHitSprites[j].getSubimage(i*32, 0, 32, 32);
            }
        }
    }
    public BufferedImage[][] getPlayerAttackHitAnimations() {
        importImage();
        loadAttackHitAnimation();
        return attackHitAnimations;
    }
}
