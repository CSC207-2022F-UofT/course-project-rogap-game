package Entities;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Potion extends Item{

    private GamePanel gamePanel;
    private BufferedImage sprite;
    public BufferedImage[] animation;
    private int aniTick, aniIndex, aniSpeed= 10;

    // == Constructor ==
    public Potion(GamePanel gamePanel, int xDelta, int yDelta) {
        this.gamePanel = gamePanel;
        importImage();
        loadAnimation();
    }

    // code relating to animating the potion item
    private void importImage() {
        InputStream potion = getClass().getResourceAsStream("/HealthPotion.png");
        try {
            assert potion != null;
            sprite = ImageIO.read(potion);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                potion.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAnimation() {
        animation = new BufferedImage[5];
        for (int i = 0; i < animation.length; i++){
            animation[i] = sprite.getSubimage(i*32, 0, 32, 32 );
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
