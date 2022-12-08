package Use_Cases;

import java.awt.*;

public class EnemeyMovementInteractor implements EnemyMovementInputBoundary{
    EnemyMovement enemyMovement;
    public EnemeyMovementInteractor(EnemyMovement enemyMovement) {
        this.enemyMovement = enemyMovement;
    }
    @Override
    public int getHelperX() {
        return enemyMovement.getHelperX();
    }

    @Override
    public int getHelperY() {
        return enemyMovement.getHelperY();
    }

    @Override
    public int getVisualX() {
        return enemyMovement.getVisualX();
    }

    @Override
    public int getVisualY() {
        return enemyMovement.getVisualY();
    }

    @Override
    public void updateX(int playerX) {
        enemyMovement.updateX(playerX);
    }

    @Override
    public void updateY(int playerY) {
        enemyMovement.updateY(playerY);
    }

    @Override
    public int getVelX(int playerX) {
        return enemyMovement.getVelX(playerX);
    }

    @Override
    public int getVelY(int playerY) {
        return enemyMovement.getVelY(playerY);
    }

    @Override
    public void changeX(int velX) {
        enemyMovement.changeX(velX);
    }

    @Override
    public void changeY(int velY) {
        enemyMovement.changeY(velY);
    }

    @Override
    public Rectangle getHitbox() {
        return enemyMovement.getHitBox();
    }

    @Override
    public double getDistance(int targetX, int targetY) {
        return enemyMovement.getDistance(targetX, targetY);
    }
}
