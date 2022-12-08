package Entities;

import java.awt.image.BufferedImage;

public class Player {
    private   BufferedImage[][] animations;
    private int speed = 10;
    private int currLocationX = -2546, currLocationY = -2132;
    private int helperX = 1882, helperY = 1738;
    private int gold = 100;
    final private int STARTING_DMG = 10;
    final private int STARTING_HP = 100;
    private int attack = STARTING_DMG;
    private String name;
    
    // VARIABLES FOR SHOP SYSTEM
    private int maxHealth = STARTING_HP;
    private int currentHealth = maxHealth;

    public int getMaxHealth() { return this.maxHealth; }
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    public int getAttack() { return attack; }

    public Player(String name) {
        this.name = name;
    }
    public int getSpeed() {
        return this.speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    /**
     * Heals the player
     * @param amount How much health we regen
     */
    public void regenHealth(int amount){
        System.out.println("Health Before: " + getCurrentHealth());
        this.currentHealth += amount;
        if (getCurrentHealth() > 100){
            this.currentHealth = 100;
        }
        System.out.println("Health After: " + getCurrentHealth());
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
    public void setAnimations(BufferedImage[][] animations) {
        this.animations = animations;
    }
    public BufferedImage[][] getAnimations() {
        return this.animations;
    }
    public int getVisualX() {
        return currLocationX;
    }
    public int getVisualY() {
        return currLocationY;
    }
    public int getHelperX() {
        return helperX;
    }
    public int getHelperY() {
        return helperY;
    }
    public void changeHelperX(int x) {
        this.helperX -= x;
    }
    public void changeHelperY(int y) {
        this.helperY -= y;
    }
    public void changeVisualX(int x) {
        this.currLocationX += x;
    }
    public void changeVisualY(int y) {
        this.currLocationY += y;
    }

}
