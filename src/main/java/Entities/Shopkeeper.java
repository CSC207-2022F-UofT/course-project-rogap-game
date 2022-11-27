package Entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Shopkeeper{

    private GamePanel gamePanel;
    private BufferedImage sprite;
    public BufferedImage[] animation;
    private int aniTick, aniIndex, aniSpeed= 10;

    // == Constructor ==
    public Shopkeeper(GamePanel gamePanel, int xDelta, int yDelta) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();
    }

    // code relating to animating the Shopkeeper
    private void importImage() {
        InputStream shopkeeper = getClass().getResourceAsStream("/Shopkeeper.png");
        try {
            assert shopkeeper != null;
            sprite = ImageIO.read(shopkeeper);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                shopkeeper.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAnimation() {
        animation = new BufferedImage[16];
        for (int i = 0; i < animation.length; i++){
            animation[i] = sprite.getSubimage(i*100, 0, 100, 100 );
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
