package Frameworks;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class AnimationImport {
    private BufferedImage[][] animations;
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
        InputStream lIM = getClass().getResourceAsStream("/leftIdleMonster.png");
        InputStream rIM = getClass().getResourceAsStream("/rightIdleMonster.png");
        InputStream lMM = getClass().getResourceAsStream("/leftMovementMonster.png");
        InputStream rMM = getClass().getResourceAsStream("/rightMovementMonster.png");

        try {
            assert lI != null & rI != null & lM != null & rM != null  & lIM != null & rIM != null & lMM != null & rMM != null;
            sprites[0] = ImageIO.read(lI);
            sprites[1] = ImageIO.read(rI);
            sprites[2] = ImageIO.read(lM);
            sprites[3] = ImageIO.read(rM);
            sprites[4] = ImageIO.read(lIM);
            sprites[5] = ImageIO.read(rIM);
            sprites[6] = ImageIO.read(lMM);
            sprites[7] = ImageIO.read(rMM);
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
}
