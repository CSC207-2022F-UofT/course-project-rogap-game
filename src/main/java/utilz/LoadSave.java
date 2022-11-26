package utilz;

import Entities.MeleeMonster;
import Entities.RangedMonster;
import Interface_Adapters.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.MELEE_MONSTER;
import static utilz.Constants.EnemyConstants.RANGED_MONSTER;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png"; // need to update this to the actual floor sprite
	public static final String LEVEL_ONE_DATA = "level_one_data_long.png"; // might need to update this depending on how we implement the floor
	// this is basically the floor data. Each pixel/tile on this file corresponds to the same pixel/tile on the screen.
	// this is used to specify where the tiles of outside_sprites should go, and also where the monsters spawn.

	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "blue_background.png";
	public static final String PLAYING_BG_IMG = "Floor1.png";
	public static final String MELEE_MONSTER_SPRITE = "melee_sprite.png";
	public static final String RANGED_MONSTER_SPRITE = "ranged_sprite.png";
	public static final String STATUS_BAR = "health_power_bar.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
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

	public static ArrayList<RangedMonster> GetRangedMonsters() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<RangedMonster> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getBlue();
				if (value == RANGED_MONSTER) // spawns a ranged monster in every blue tile in level_one_data_long
					list.add(new RangedMonster(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}

	public static ArrayList<MeleeMonster> GetMeleeMonsters() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<MeleeMonster> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == (MELEE_MONSTER - 1)) // spawns a melee monster in every green tile in level_one_data_long
					list.add(new MeleeMonster(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}

	public static int[][] GetLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}