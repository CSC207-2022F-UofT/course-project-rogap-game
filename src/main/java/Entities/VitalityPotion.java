package Entities;

public class VitalityPotion extends Potion{
    public VitalityPotion(){
        super("/vitalityPotion.png");
    }
    public int getBuff(){
        return 25;
    }
    public int getPrice() {
        return 100;
    }
    public String getName(){
        return "Vitality Potion";
    }
}
