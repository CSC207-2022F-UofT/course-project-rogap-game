package Use_Cases;

/**
 * Data Structure that contains the player health, attack, and speed
 */
public class StatBarsResponseModel {
    int maxHealth;
    int currentHealth;
    int attack;

    public StatBarsResponseModel(int maxHealth, int currentHealth, int attack) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.attack = attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }
}
