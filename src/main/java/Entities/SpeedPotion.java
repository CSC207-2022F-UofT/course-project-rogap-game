package Entities;

public class SpeedPotion extends Potion{
    public SpeedPotion(){
        super("/speedPotion.png");
    }
    public int getBuff(){
        return 5;
    }
    public int getPrice() {
        return 100;
    }
    public String getName(){
        return "Speed Potion";
    }
}
