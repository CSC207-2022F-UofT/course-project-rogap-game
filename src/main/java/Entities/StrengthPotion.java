package Entities;

public class StrengthPotion extends Potion{
    public StrengthPotion(){
        super("/strengthPotion.png");

    }
    public int getBuff(){
        return 10;
    }
    public int getPrice() {
        return 150;
    }
    public String getName(){
        return "Strength Potion";
    }
}
