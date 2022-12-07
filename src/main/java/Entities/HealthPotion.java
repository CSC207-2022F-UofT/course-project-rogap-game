package Entities;

public class HealthPotion extends Potion{
    public HealthPotion(){
        super("/healthPotion.png");
    }
    public int getBuff(){
        return 100;
    }
    public int getPrice() {
        return 50;
    }
    public String getName(){
        return "Health Potion";
    }
}
