package Entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class AnimationsManager {
    // PLAYER
    public static final String PLAYER_LEFT_IDLE = "leftIdle.png";
    public static final String PLAYER_LEFT_MOVE = "leftMovement.png";
    public static final String PLAYER_RIGHT_IDLE = "rightIdle.png";
    public static final String PLAYER_RIGHT_MOVE = "rightMovement.png";
    public static final int PLAYER_WIDTH = 64;

    // MONSTER
    public static final String MONSTER_LEFT = "Left_monster.png";
    public static final String MONSTER_RIGHT = "Right_monster.png";
    public static final String MONSTER_BOUNCING = "Bouncing_monster.png";

    // SHOP
    public static final String SHOPKEEPER = "ShopKeeper.png";
    public static final String HEALTH_POTION = "HealthPotion.png";

    // MAP
    public static final String FLOOR = "Floor1.png";
    public static final String MAP_CURSOR = "MapCursor.png";
    public static final String MINIMAP = "Minimap.png";
    public static final String BUSHES = "Bushes.png";
    public static final String LEAF = "leaf.png";

    // SCREEN
    public static final String BUFFBAR = "Buffbar.png";
    public static final String HEALTHBAR = "HealthBar.png";
    public static final String PAUSED = "Paused.png";
    public static final String STATS_BAR = "StatsBar.png";
    public static final String TIMER_PILL = "TimerPill.png";


    public static BufferedImage GetSpriteAtlas(String fileName) {       // Loads sprites
        BufferedImage img = null;
        InputStream is = AnimationsManager.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }


}
