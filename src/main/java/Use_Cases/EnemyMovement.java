package Use_Cases;

public class EnemyMovement extends Movement{
    private static int spawnX, spawnY;
    private int xEnemy, yEnemy;
    public EnemyMovement(int xEnemy, int yEnemy, int spawnX, int spawnY) {
        this.spawnY = spawnY;
        this.spawnX = spawnX;
        this.xEnemy = xEnemy;
        this.yEnemy = yEnemy;
    }
    @Override
    public void updateX() {

    }

    @Override
    public void updateY() {

    }

    @Override
    public int getVisualX() {
        return xEnemy;
    }

    @Override
    public int getVisualY() {
        return yEnemy;
    }

    public static int getHelperX() {
        return spawnX;
    }

    public static int getHelperY() {
        return spawnY;
    }
}
