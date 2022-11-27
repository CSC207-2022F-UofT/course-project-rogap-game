package Entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Key extends Item {
    private GamePanel gamePanel;
    private BufferedImage sprite;
    public BufferedImage[] animation;
    private int aniTick, aniIndex, aniSpeed= 10;

    // == Constructor ==
    public Key(GamePanel gamePanel, int xDelta, int yDelta) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();
    }

    // code relating to animating the key item
    private void importImage() {
        InputStream key = getClass().getResourceAsStream("/Key.png");
        try {
            assert key != null;
            sprite = ImageIO.read(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                key.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAnimation() {
        animation = new BufferedImage[6];
        for (int i = 0; i < animation.length; i++){
            animation[i] = sprite.getSubimage(i*40, 0, 40, 40 );
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
