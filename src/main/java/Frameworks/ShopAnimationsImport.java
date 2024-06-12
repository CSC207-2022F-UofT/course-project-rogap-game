package Frameworks;

import Interface_Adapters.ShopAnimationController;
import Interface_Adapters.ShopAnimationsImportController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ShopAnimationsImport {

    private BufferedImage[] animation;
    private BufferedImage sprite;
    ShopAnimationController shopAnimationController;

    /**
     * This class is designated to import all the Shop Item animations
     */

    public ShopAnimationsImport(ShopAnimationController shopAnimationController){
        this.shopAnimationController =  shopAnimationController;
        new ShopAnimationsImportController(getItemAnimation(), shopAnimationController);
    }

    private void importImage() {
        //InputStream sp = getClass().getResourceAsStream("/speedPotion.png");
        InputStream st = getClass().getResourceAsStream("/strengthPotion.png");
        //InputStream vp = getClass().getResourceAsStream("/vitalityPotion.png");
//        InputStream hp = getClass().getResourceAsStream("/healthPotion.png");
        //InputStream ky = getClass().getResourceAsStream("/key.png");
        try {
            //assert sp != null & st != null & vp != null & hp != null & ky != null;
            assert st != null;
//            sprites[0] = ImageIO.read(sp);
//            sprites[1] = ImageIO.read(st);
//            sprites[2] = ImageIO.read(vp);
            sprite = ImageIO.read(st);
//            sprites[4] = ImageIO.read(ky);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//                sp.close();
//                st.close();
//                vp.close();
                st.close();
//                ky.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This function loads the animation for an item, an animation being an array composed of BufferedImage type.
     * Each element within an animation represents a sprite(frame of the sprite sheet)
     *
     * @return
     */
    private void loadAnimation() {
        animation = new BufferedImage[5];
        for (int i = 0; i < animation.length; i++){
            animation[i] = sprite.getSubimage(i*32, 0, 32, 45);
        }
    }
    /**
     * This class returns a loaded item animation
     * @return BufferedImage[][
     */
    public BufferedImage[] getItemAnimation() {
        importImage();
        loadAnimation();
        return this.animation;
    }
}
