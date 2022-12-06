package Entities;

public class Player {
    // VARIABLES FOR SHOP SYSTEM
    private int gold = 100;
    private int health = 10;
    public int getHealth() {
        return this.health;
    }

    public void addHealth(int amount){
        System.out.println("Health Before: " + getHealth());
        this.health += amount;
        if (getHealth() > 100){
            this.health = 100;
        }
        System.out.println("Health After: " + getHealth());
    }

    public int getGold(){
        return this.gold;
    }
    public void addGold(int gold){
        this.gold += gold;
    }
    public void removeGold(int amount){
        this.gold -= amount;
    }

}
