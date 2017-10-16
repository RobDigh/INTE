package entity.creature;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InventoryFactoryTest {

    private static final InventoryFactory INVENTORY_FACTORY = new InventoryFactory();

    @Test
    public void create() {

        Inventory inventory = INVENTORY_FACTORY.create();
        assertTrue(inventory != null);

    }
}
