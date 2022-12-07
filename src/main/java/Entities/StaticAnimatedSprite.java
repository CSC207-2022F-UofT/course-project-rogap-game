package Entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class StaticAnimatedSprite {
    private BufferedImage sprite;
    public BufferedImage[] animation;
    protected int aniTick, aniIndex, aniSpeed= 10;

    // code relating to animating static animated sprites
    protected void importImage(String filename) {
        InputStream image = getClass().getResourceAsStream(filename);
        try {
            assert image != null;
            sprite = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                image.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void loadAnimation(int image_w, int image_h, int num_frames) {
        animation = new BufferedImage[num_frames];
        for (int i = 0; i < animation.length; i++){
            animation[i] = sprite.getSubimage(i*image_w, 0, image_w, image_h);
        }
    }
    public void update() {
        updateAnimationTick();
    }

    private void updateAnimationTick() {
        aniTick ++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= animation.length) {
                aniIndex = 0;
            }
        }

    }
    public BufferedImage getCurrentImage () {
        return this.animation[aniIndex];
    }

}
