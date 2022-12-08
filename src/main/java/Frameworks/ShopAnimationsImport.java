package Frameworks;

import Interface_Adapters.ShopAnimationController;
import Interface_Adapters.ShopAnimationsImportController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ShopAnimationsImport {

    private BufferedImage[][] animations;
    private BufferedImage[] sprites = new BufferedImage[5];
    ShopAnimationController shopAnimationController;

    /**
     * This class is designated to import all the player animation
     */

    public ShopAnimationsImport(ShopAnimationController shopAnimationController){
        this.shopAnimationController =  shopAnimationController;
        new ShopAnimationsImportController(getItemAnimations(), shopAnimationController);
    }

    private void importImage() {
        //InputStream sp = getClass().getResourceAsStream("/speedPotion.png");
        //InputStream st = getClass().getResourceAsStream("/strengthPotion.png");
        //InputStream vp = getClass().getResourceAsStream("/vitalityPotion.png");
        InputStream hp = getClass().getResourceAsStream("/healthPotion.png");
        //InputStream ky = getClass().getResourceAsStream("/key.png");
        try {
            //assert sp != null & st != null & vp != null & hp != null & ky != null;
            assert hp != null;
//            sprites[0] = ImageIO.read(sp);
//            sprites[1] = ImageIO.read(st);
//            sprites[2] = ImageIO.read(vp);
            sprites[0] = ImageIO.read(hp);
//            sprites[4] = ImageIO.read(ky);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                sp.close();
//                st.close();
//                vp.close();
                hp.close();
//                ky.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This class loads the player animation to a BufferedImage[][]
     *
     * @return
     */
    private void loadAnimation() {
        animations = new BufferedImage[1][5];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = sprites[j].getSubimage(i * 32, 0, 32, 45);
//                if (j < 4) {
//                    animations[j][i] = sprites[j].getSubimage(i * 32, 0, 32, 45);
//                } else if (j == 4) {
//                    animations[j][i] = sprites[j].getSubimage(i * 40, 0, 40, 48);
//                }
            }
        }
    }
    /**
     * This class returns the loaded item animations
     * @return BufferedImage[][]
     */
    public BufferedImage[][] getItemAnimations() {
        importImage();
        loadAnimation();
        return this.animations;
    }
}
