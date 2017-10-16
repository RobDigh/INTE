package entity.creature;

import entity.item.Item;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Item> inventoryMap;

    public Inventory(){
        inventoryMap = new HashMap<>();
    }

    public void addItem(Item item, String key) {
        inventoryMap.put(key, item);
    }

    public Map<String, Item> getInventory() {
        return inventoryMap;
=======
public class Inventory {

    public void addItem(Item item, String key) {

>>>>>>> origin/dev
    }
}
