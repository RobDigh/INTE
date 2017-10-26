package entity.creature;

import entity.item.Item;
import entity.item.consumable.hp.HealthPotion;
import entity.item.consumable.speed.SpeedPotion;
import entity.item.wearable.armor.Armor;
import entity.item.wearable.weapon.Weapon;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class InventoryTest {

    private Map<String, Item> inventoryTestMap = new HashMap<>();
    private Inventory testInventory = new Inventory();
    private Armor mockArmor = mock(Armor.class);
    private Weapon mockWeapon = mock(Weapon.class);
    private HealthPotion mockHealthPotion = mock(HealthPotion.class);
    private SpeedPotion mockSpeedPotion = mock(SpeedPotion.class);
    private static final InventoryFactory INVENTORY_FACTORY = new InventoryFactory();

    @Test
    public void create() {

        Inventory inventory = INVENTORY_FACTORY.create();
        assertTrue(inventory != null);
    }

    @Test
    public void testInventoryMapAddOneArmor(){

        testInventory.addItem(mockArmor, "armor");
        assertTrue(testInventory.getInventory().containsKey("armor"));
    }

    @Test
    public void testInventoryMapAddOneWeapon(){

        testInventory.addItem(mockWeapon, "weapon");
        assertTrue(testInventory.getInventory().containsKey("weapon"));
    }

    @Test
    public void testInventoryMapAddOneHealthPotion(){

        testInventory.addItem(mockHealthPotion, "health potion");
        assertTrue(testInventory.getInventory().containsKey("health potion"));
    }

    @Test
    public void testInventoryMapAddOneSpeedPotion(){

        testInventory.addItem(mockSpeedPotion, "speed potion");
        assertTrue(testInventory.getInventory().containsKey("speed potion"));
    }

    @Test
    public void testInventoryMapAddTwoItems(){

        inventoryTestMap.put("armor", mockArmor);
        testInventory.addItem(mockArmor, "armor");
        inventoryTestMap.put("weapon", mockWeapon);
        testInventory.addItem(mockWeapon, "weapon");

        assertEquals(inventoryTestMap, testInventory.getInventory());
    }

    @Test
    public void testRemoveOneItemFromInventoryContainingOneItem(){

        testInventory.addItem(mockArmor, "armor");
        assertTrue(testInventory.removeItem(mockArmor, "armor"));
    }

    @Test
    public void testRemoveTwoItemsFromInventoryContainingTwoItems(){

        testInventory.addItem(mockArmor, "armor");
        testInventory.addItem(mockWeapon, "weapon");

        assertTrue(testInventory.removeItem(mockArmor, "armor"));
        assertTrue(testInventory.removeItem(mockWeapon, "weapon"));
    }

    @Test
    public void testRemoveNonExistingItemFromNonEmptyInventory(){

        testInventory.addItem(mockArmor, "armor");
        assertFalse(testInventory.removeItem(mockWeapon, "weapon"));
    }

    @Test
    public void testRemoveItemFromEmptyInventory(){
        assertFalse(testInventory.removeItem(mockArmor, "armor"));
    }

    @Test
    public void testRemoveItemFromInventoryWrongKeyAndCorrectItem(){

        testInventory.addItem(mockArmor, "armor");
        assertFalse(testInventory.removeItem(mockArmor, "amor"));
    }
}