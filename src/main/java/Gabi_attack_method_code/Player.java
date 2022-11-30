package Gabi_attack_method_code;

import Interface_Adapters.Game;
import gamestates.Playing;
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
	private boolean left, up, right, down;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

	// StatusBarUI
	private BufferedImage statusBarImg;

	private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (34 * Game.SCALE);
	private int healthBarYStart = (int) (14 * Game.SCALE);

	private int maxHealth = 100;
	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;

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

	public Ellipse2D.Float getAttackRadius() {
		return attackRadius;
	}

	public void update() {
		updateHealthBar();

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

	private void updateHealthBar() {
		healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);

	}

	public void render(Graphics g, int lvlOffset) {
		g.drawImage(animations[playerAction][aniIndex],
				(int) (hitRadius.x - xDrawOffset) - lvlOffset + flipX,
				(int) (hitRadius.y - yDrawOffset), width * flipW, height, null);
		drawHitRadius(g, lvlOffset);

		drawAttackRadius(g, lvlOffset);
		drawUI(g);
	}

	private void drawAttackRadius(Graphics g, int lvlOffsetX) {
		g.setColor(Color.red);
		g.drawOval((int) attackRadius.x - lvlOffsetX, (int) attackRadius.y, (int) attackRadius.width, (int) attackRadius.height);
		// can remove lvlOffsetX after
	}

	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		g.setColor(Color.red);
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);

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

		if (moving) {
			playerAction = RUNNING;
		} else {
			playerAction = IDLE;
		}

		if (attacking) {
			playerAction = ATTACK;
		}

		if (hit) {
			playerAction = HIT;
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

		float xSpeed = 0;
		float ySpeed = 0;

		if (left) {
			xSpeed -= playerSpeed;
			flipX = width;
			flipW = -1;
			moving = true;
		}else if (right) {
			xSpeed += playerSpeed;
			flipX = 0;
			flipW = 1;
			moving = true;
		}
		if (up) {
			ySpeed -= playerSpeed;
			moving = true;
		} else if (down) {
			ySpeed += playerSpeed;
			moving = true;
		}

		updateXPos(xSpeed);
		updateYPos(ySpeed);


	}

	private void updateXPos(float xSpeed) {
		if (CanMoveHere(hitRadius.x + xSpeed, hitRadius.y, hitRadius.width, hitRadius.height, lvlData)) {
			hitRadius.x += xSpeed;
		} else {
			hitRadius.x = GetEntityXPosNextToWall(hitRadius, xSpeed);
		}

	}

	private void updateYPos(float ySpeed) {
		if (CanMoveHere(hitRadius.x, hitRadius.y + ySpeed, hitRadius.width, hitRadius.height, lvlData)) {
			hitRadius.y += ySpeed;
		} else {
			hitRadius.y = GetEntityYPosUnderRoofOrAboveFloor(hitRadius, ySpeed);
		}

	}

	public void changeHealth(int value) {
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

		statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;

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

	public void resetAll() {
		resetDirBooleans();
		resetAniTick();
		attacking = false;
		moving = false;
		playerAction = IDLE;
		currentHealth = maxHealth;

		hitRadius.x = x;
		hitRadius.y = y;
	}
}