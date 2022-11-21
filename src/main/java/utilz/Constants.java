package utilz;

import main.Game;

public class Constants {

	public static class EnemyConstants {
		public static final int CRABBY = 0;
		public static final int MELEE_MONSTER = 1;
		public static final int RANGED_MONSTER = 2;

		public static final int IDLE = 0; // probably remove this???
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		public static final int CRABBY_WIDTH_DEFAULT = 72;
		public static final int CRABBY_HEIGHT_DEFAULT = 32;

		public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
		public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

		public static final int MELEE_WIDTH_DEFAULT = 32;
		public static final int MELEE_HEIGHT_DEFAULT = 32;

		public static final int MELEEONSTER_WIDTH = (int) (MELEE_WIDTH_DEFAULT * Game.SCALE);
		public static final int MELEEMONSTER_HEIGHT = (int) (MELEE_HEIGHT_DEFAULT * Game.SCALE);

		public static final int RANGED_WIDTH_DEFAULT = 32;
		public static final int RANGED_HEIGHT_DEFAULT = 32;

		public static final int RANGEDMONSTER_WIDTH = (int) (RANGED_WIDTH_DEFAULT * Game.SCALE);
		public static final int RANGEDMONSTER_HEIGHT = (int) (RANGED_HEIGHT_DEFAULT * Game.SCALE);

//		public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Game.SCALE); // 26 is difference between start of sprite and hitbox
//
//		public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Game.SCALE);

		public static int GetSpriteAmount(int enemy_type, int enemy_state) {

			switch (enemy_type) {
				case MELEE_MONSTER:
					switch (enemy_state) {
						case IDLE:
							return 4;
						case RUNNING:
						case ATTACK:
							return 2;
						case HIT:
						case DEAD:
							return 3;
					}
				case RANGED_MONSTER:
					switch (enemy_state) {
						case IDLE:
						case ATTACK:
							return 4;
						case RUNNING:
							return 2;
						case HIT:
						case DEAD:
							return 3;
					}
			}

			return 0;

		}

		public static int GetMaxHealth(int enemyType) {
			switch (enemyType) {
				case CRABBY:
				case RANGED_MONSTER:
					return 50;
				default:
					return 1;
			}
		}

		public static int GetEnemyDmg(int enemyType) {
			switch (enemyType) {
				case CRABBY:
					return 15;
				default:
					return 0;
			}
		}

	}


	public static class Environment {
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

		public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
	}

	public static class UI {
		public static class Buttons {
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}

		public static class PauseButtons {
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
		}

		public static class URMButtons {
			public static final int URM_DEFAULT_SIZE = 56;
			public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

		}

		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int ATTACK = 4;
		public static final int HIT = 5;
		public static final int DEAD = 6;

		public static int GetSpriteAmount(int player_action) {
			switch (player_action) {
				case DEAD:
					return 8;
				case RUNNING:
					return 6;
				case IDLE:
					return 5;
				case HIT:
					return 4;
				case JUMP:
				case ATTACK:
					return 3;
				case FALLING:
				default:
					return 1;
			}
		}
	}

}