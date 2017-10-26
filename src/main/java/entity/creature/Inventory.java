package entity.creature;

import entity.item.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, Item> inventoryMap;

    public Inventory(){
        inventoryMap = new HashMap<>();
    }

    public void addItem(Item item, String key) {

        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }

        inventoryMap.put(key, item);
    }

    public boolean removeItem(Item item, String key) {
       if (inventoryMap.isEmpty()){
           return false;
       }
       if (!inventoryMap.containsKey(key)){
           return false;
       }
        inventoryMap.remove(key, item);
        return true;
    }

    public Map<String, Item> getInventory() {
        return inventoryMap;
    }
}