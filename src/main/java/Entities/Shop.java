package Entities;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class Shop {
    // == Class Variables ==
    private final ArrayList<int[]> itemLocation = new ArrayList<>();
    private final Player player;

    private final ArrayList<Purchasable> itemsList = new ArrayList<>(Arrays.asList(new Key(), new HealthPotion(),
            new StrengthPotion(), new VitalityPotion(), new SpeedPotion()));
    // == Constructor ==
    public Shop(Player player){
        this.player = player;
        itemLocation.add(new int[] {559, 1007});
        itemLocation.add(new int[] {674, 1011});
    }

    public void checkLocation(){
        int x = player.getAbsXPlayer();
        int y = player.getAbsYPlayer();

        if (itemsList.size() != 0){
            for (int[] coordinates: itemLocation){
                if (( x >= coordinates[0] && x <= coordinates[0] + 38) &&
                        (y >= coordinates[1] && y <= coordinates[1] + 38)){
                    purchase("Health Potion");
                }
            }
        }
    }

    public ArrayList<Purchasable> getItemList() {
        return itemsList;
    }

//    public void purchase(String itemName) {
//        // TODO: More on items goes here
//        if (player.getGold() >= itemList.get(itemName)){
//            player.removeGold(itemList.get(itemName));
//            player.addHealth(20);
//            itemList.remove(itemName);
//
//            System.out.println("Thank you for your purchase!");
//            System.out.println("Balance: " + player.getGold());
//        }
//    }

}
