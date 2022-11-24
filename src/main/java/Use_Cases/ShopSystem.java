package Use_Cases;

import Entities.Player;
import main.GamePanel;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class ShopSystem{
    private HashMap<String, Integer> itemList = new HashMap<String, Integer>();
    private ArrayList<int[]> itemLocation = new ArrayList<>();
    private Player player;

    public ShopSystem(Player player){
        this.player = player;
        itemList.put("Health Potion",100);
        itemLocation.add(new int[] {567, 1003});
    }

    public void checkLocation(){
        int x = player.getAbsXPlayer();
        int y = player.getAbsYPlayer();

        if (itemList.size() != 0){
            for (int[] coordinates: itemLocation){
                if (( x >= coordinates[0] && x <= coordinates[0] + 38) &&
                        (y >= coordinates[1] && y <= coordinates[1] + 38)){
                    purchase("Health Potion");
                }
            }
        }
    }

    public Set<String> getItemList() {
        return itemList.keySet();
    }

    public void purchase(String itemName) {
        // TODO: More on items goes here
        if (player.getGold() >= itemList.get(itemName)){
            player.removeGold(itemList.get(itemName));
            player.addHealth(20);
            itemList.remove("Health Potion");

            System.out.println("Thank you for your purchase!");
            System.out.println("Balance: " + player.getGold());
        }
    }

}
