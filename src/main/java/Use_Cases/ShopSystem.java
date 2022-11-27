package Use_Cases;
import Entities.Player;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class ShopSystem{
    // == Class Variables ==
    private final HashMap<String, Integer> itemList = new HashMap<>();
    private final ArrayList<int[]> itemLocation = new ArrayList<>();
    private final Player player;

    // == Constructor ==
    public ShopSystem(Player player){
        this.player = player;
        itemList.put("Health Potion",50);
        itemLocation.add(new int[] {559, 1007});

        itemList.put("Key", 50);
        itemLocation.add(new int[] {674, 1011});
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
            itemList.remove(itemName);

            System.out.println("Thank you for your purchase!");
            System.out.println("Balance: " + player.getGold());
        }
    }

}
