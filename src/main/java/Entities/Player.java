package Entities;

import gamestates.Playing;
import Interface_Adapters.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

public class Player extends Creature {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false, hit = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;

	// Health Bar UI
	// The Heart Icon on the left of the health bar
	private BufferedImage heartIcon;
	private int heartIconWidth = (int) (32 * Game.SCALE);
	private int heartIconHeight = (int) (32 * Game.SCALE);
	// The Heart Icon's x and y position in the UI
	private int heartIconX = (int) (10 * Game.SCALE);
	private int heartIconY = (int) (10 * Game.SCALE);

	// The player's Max Health displayed on the health bar at top left of the screen
	private int maxHealthWidth = (int) (108 * Game.SCALE);
	private int maxHealthHeight = (int) (32 * Game.SCALE);
	// The current health's position
	private int maxHealthXStart = (int) (32 * Game.SCALE);
	private int maxHealthYStart = 0;

	// The player's current health displayed on the health bar at top left of the screen
	private int currentHealthWidth = (int) (100 * Game.SCALE);
	private int currentHealthHeight = (int) (16 * Game.SCALE);
	// The current health's position
	private int currentHealthXStart = (int) (32 * Game.SCALE);
	private int currentHealthYStart = (int) (8 * Game.SCALE);

	private int maxHealth = 100;
	private int currentHealth = maxHealth;

	// AttackBox
	private Ellipse2D.Float attackRadius;
	private int attackRadiusOffset;

	private int flipX = 0;
	private int flipW = 1;

	private boolean attackChecked;

	private Playing playing;

	public Player(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		loadAnimations();
		initHitRadius(x, y, (int) (30 * Game.SCALE), (int) (30 * Game.SCALE));
		initAttackBox();

	}

	private void initAttackBox() {
		attackRadius = new Ellipse2D.Float(x, y, (int) (55 * Game.SCALE), (int) (55 * Game.SCALE)); //35 is width of player; 30 is the height
		attackRadiusOffset = (int) (Game.SCALE * 13);
	}

	public void update() {
		updateCurrentHealth();

		if (currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}

		updateAttackBox();
		
		updatePos();
		if (attacking)
			checkAttack();
		updateAnimationTick();
		setAnimation();
	}

	private void checkAttack() {
		if (attackChecked || aniIndex != 1)
			return;
		attackChecked = true;
		playing.checkEnemyHit(attackRadius);
	}

	private void updateAttackBox() {
		attackRadius.x = hitRadius.x - attackRadiusOffset;
		attackRadius.y = hitRadius.y - attackRadiusOffset;
	}

	/**
	 * Updates the player's current health displayed on the health bar
	 */
	private void updateCurrentHealth() {
		currentHealthWidth = (int) (currentHealth * Game.SCALE);
	}

	/**
	 * Updates the player's max health displayed on the health bar
	 */
	private void updateMaxHealth() {
		currentHealthWidth = (int) ((currentHealth / (float) maxHealth) * currentHealthWidth);
	}

	public void render(Graphics g, int lvlOffset) {
		g.drawImage(animations[playerAction][aniIndex],
				(int) (hitRadius.x - xDrawOffset) - lvlOffset + flipX,
				(int) (hitRadius.y - yDrawOffset), width * flipW, height, null);
		drawHitRadius(g, lvlOffset);
//		drawHitbox(g, lvlOffset);

		drawAttackRadius(g, lvlOffset);
		drawHealthBar(g);
	}

	private void drawAttackRadius(Graphics g, int lvlOffsetX) {
		g.setColor(Color.red);
		g.drawOval((int) attackRadius.x - lvlOffsetX, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
		// can remove lvlOffsetX after
	}

	/**
	 * Draws the health bar at the top left of the screen
	 * @param g
	 */
	private void drawHealthBar(Graphics g) {
		g.drawImage(heartIcon, heartIconX, heartIconY, heartIconWidth, heartIconHeight, null);
		g.setColor(Color.darkGray);
		g.fillRect(maxHealthXStart + heartIconX, maxHealthYStart + heartIconY,
				maxHealthWidth, maxHealthHeight);
		g.setColor(Color.red);
		g.fillRect(currentHealthXStart + heartIconX, currentHealthYStart + heartIconY,
				currentHealthWidth, currentHealthHeight);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
				hit = false;   // after the attacking/hit animation, return to idle or running animation
				attackChecked = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUNNING;
		else if (hit) {
			playerAction = HIT;
		}
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}

		if (attacking) {
			playerAction = ATTACK;
		}
		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;

		if (jump)
			jump();

		if (!inAir)
			if ((!left && !right) || (right && left))
				return;

		float xSpeed = 0;

		if (left) {
			xSpeed -= playerSpeed;
			flipX = width;
			flipW = -1;
		}
		if (right) {
			xSpeed += playerSpeed;
			flipX = 0;
			flipW = 1;
		}
		if (!inAir)
			if (!IsEntityOnFloor(hitRadius, lvlData))
				inAir = true;

		if (inAir) {
			if (CanMoveHere(hitRadius.x, hitRadius.y + airSpeed, hitRadius.width, hitRadius.height, lvlData)) {
				hitRadius.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitRadius.y = GetEntityYPosUnderRoofOrAboveFloor(hitRadius, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;

	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitRadius.x + xSpeed, hitRadius.y, hitRadius.width, hitRadius.height, lvlData)) {
			hitRadius.x += xSpeed;
		} else {
			hitRadius.x = GetEntityXPosNextToWall(hitRadius, xSpeed);
		}

	}

	public void changeCurrentHealth(int value) {
		currentHealth += value;

		if (currentHealth <= 0) {
			currentHealth = 0;
			// game over
		} else if (currentHealth >= maxHealth) {
			currentHealth = 100; // max health
		}
	}

	public void setHit(boolean value) {
		this.hit = value;
	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[5][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 64, 64, 64);

		heartIcon = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitRadius, lvlData))
			inAir = true;

	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public void resetAll() {
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		playerAction = IDLE;
		currentHealth = maxHealth;

		hitRadius.x = x;
		hitRadius.y = y;

		if (!IsEntityOnFloor(hitRadius, lvlData))
			inAir = true;
	}
}