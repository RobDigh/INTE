package entity.creature;

import entity.creature.Creature;
import entity.item.Item;
import entity.item.wearable.armor.Armor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatureTest {
    private Creature testCreature;

    private Armor testArmor01;
    private Armor testArmor02;

    private Inventory mockInventory = mock(Inventory.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);

    private Armor mockArmor = mock(Armor.class);

    private Creature createPlayerWithCustomHPAndSpeed(int hp, int speed) {
        return new Creature(hp, speed, mockInventoryFactory);
    }

    //set ups a Reflection of armorList in Creature
    private List<Item> setUpReflectionItemListForAddItem() throws Exception {

        Field itemListField = Creature.class.getDeclaredField("inventory");
        itemListField.setAccessible(true);
        List<Item> inventory = (List<Item>) itemListField.get(testCreature);
        return inventory;
    }

//    //set ups a Reflection of inventory Map in Creature
//    private Map<Item, List<Item>> setUpReflectionInventoryMapForAddItem() throws Exception {
//
//        Field inventoryMapField = Creature.class.getDeclaredField("inventory");
//        inventoryMapField.setAccessible(true);
//        Map<Item, List<Item>> inventory = (Map<Item, List<Item>>) inventoryMapField.get(testCreature);
//        return inventory;
//    }

    @Before
    public void setUp() {
        testCreature = createPlayerWithCustomHPAndSpeed(100, 10);
        testArmor01 = new Armor(1);
        testArmor02 = new Armor(2);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructPlayerWithValidHPAndSpeed() {

        Creature creature = createPlayerWithCustomHPAndSpeed(100, 1);

        assertEquals(100, creature.getHP());
        assertEquals(1, creature.getSpeed());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNegativeHP() {
        createPlayerWithCustomHPAndSpeed(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithZeroHP() {
        createPlayerWithCustomHPAndSpeed(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNegativeSpeed() {
        createPlayerWithCustomHPAndSpeed(100, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithZeroSpeed() {
        createPlayerWithCustomHPAndSpeed(100, 0);
    }

    @Test
    public void testGetHP() {

        Creature creature = createPlayerWithCustomHPAndSpeed(500, 10);
        assertEquals(500, creature.getHP());

    }

    @Test
    public void testGetSpeed() {

        Creature creature = createPlayerWithCustomHPAndSpeed(500, 10);
        assertEquals(10, creature.getSpeed());

    }

    /**
     * Add tests for HP gain and loss
     */

    @Test
    public void testGainHP() {
        Creature creature = createPlayerWithCustomHPAndSpeed(450, 8);
        creature.gainHP(50);
        assertEquals(500, creature.getHP());
    }

    @Test
    public void testGainNegativeHP() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(320, 45);
        creature.gainHP(-5);
    }

    @Test
    public void testGainZeroHP() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(258, 12);
        creature.gainHP(0);
    }

    @Test
    public void testLoseHP() {
        Creature creature = createPlayerWithCustomHPAndSpeed(450, 8);
        creature.loseHP(50);
        assertEquals(400, creature.getHP());
    }

    @Test
    public void testLoseNegativeHP() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(450, 10);
        creature.loseHP(-5);
    }

    @Test
    public void testLoseZeroHP() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(500, 10);
        creature.loseHP(0);
    }

    @Test
    public void testGoBelowZeroHP() {
        Creature creature = createPlayerWithCustomHPAndSpeed(50, 8);
        creature.loseHP(50);
        assertEquals(0, creature.getHP());
    }

    @Test
    public void testGainHPSeveralTimes() {
        int hpTotal = testCreature.getHP();
        testCreature.gainHP(50);
        testCreature.gainHP(10);
        testCreature.gainHP(80);
        hpTotal += (50 + 10 + 80);
        assertEquals(hpTotal, testCreature.getHP());
    }

    @Test
    public void testLoseHPSeveralTimes() {
        int hpTotal = testCreature.getHP();
        testCreature.loseHP(10);
        testCreature.loseHP(5);
        testCreature.loseHP(70);
        hpTotal -= (10 + 5 + 70);
        assertEquals(hpTotal, testCreature.getHP());
    }

    @Test
    public void testLoseHPSeveralTimesToBelowZero() {
        testCreature.loseHP(50);
        testCreature.loseHP(7);
        testCreature.loseHP(8);
        testCreature.loseHP(90);
        testCreature.loseHP(80);
        assertEquals(0, testCreature.getHP());
    }

    @Test
    public void testIncreaseThenDecreaseThenIncreaseAgain() {
        int hpTotal = testCreature.getHP();
        testCreature.gainHP(50);
        testCreature.gainHP(70);
        hpTotal += (50 + 70);
        assertEquals(hpTotal, testCreature.getHP());
        testCreature.loseHP(40);
        testCreature.loseHP(30);
        hpTotal -= (40 + 30);
        assertEquals(hpTotal, testCreature.getHP());
        testCreature.gainHP(50);
        hpTotal += 50;
        assertEquals(hpTotal, testCreature.getHP());
    }

    @Test
    public void testDecreaseBelowZeroHPThenIncreaseAgain() {
        while (0 < testCreature.getHP()) {
            testCreature.loseHP(9);
        }
        assertEquals(0, testCreature.getHP());
        testCreature.gainHP(5);
        assertEquals(0, testCreature.getHP());
    }

    /**
     * Add tests for Speed gain and loss
     */

    @Test
    public void testGainSpeed() {
        Creature creature = createPlayerWithCustomHPAndSpeed(50, 10);
        creature.gainSpeed(5);
        assertEquals(15, creature.getSpeed());
    }

    @Test
    public void testGainNegativeSpeed() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(500, 8);
        creature.gainSpeed(-5);
    }

    @Test
    public void testGainZeroSpeed() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(500, 8);
        creature.gainSpeed(0);
    }

    @Test
    public void testLoseSpeed() {
        Creature creature = createPlayerWithCustomHPAndSpeed(50, 10);
        creature.loseSpeed(5);
        assertEquals(5, creature.getSpeed());
    }

    @Test
    public void testLoseNegativeSpeed() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(800, 15);
        creature.loseSpeed(-5);
    }

    @Test
    public void testLoseZeroSpeed() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(450, 7);
        creature.loseSpeed(0);
    }

    @Test
    public void testGoBelowZeroSpeed() {
        Creature creature = createPlayerWithCustomHPAndSpeed(450, 7);
        creature.loseSpeed(9);
        assertEquals(0, creature.getSpeed());
    }

    @Test
    public void testGainSpeedSeveralTimes() {
        int speedTotal = testCreature.getSpeed();
        testCreature.gainSpeed(5);
        testCreature.gainSpeed(8);
        testCreature.gainSpeed(7);
        speedTotal += (5 + 8 + 7);
        assertEquals(speedTotal, testCreature.getSpeed());
    }

    @Test
    public void testLoseSpeedSeveralTimes() {
        int speedTotal = testCreature.getSpeed();
        testCreature.loseSpeed(1);
        testCreature.loseSpeed(2);
        testCreature.loseSpeed(5);
        speedTotal -= (1 + 2 + 5);
        assertEquals(speedTotal, testCreature.getSpeed());
    }

    @Test
    public void testLoseSpeedForNegativeTotalThenGainSpeed() {
        while (testCreature.getSpeed() > 0) {
            testCreature.loseSpeed(4);
        }
        testCreature.loseSpeed(4);
        assertEquals(0, testCreature.getSpeed());
        testCreature.gainSpeed(5);
        assertEquals(5, testCreature.getSpeed());
    }

    @Test
    public void testGainSpeedThenLoseSpeed() {
        int speedTotal = testCreature.getSpeed();
        testCreature.gainSpeed(5);
        testCreature.loseSpeed(7);
        testCreature.loseSpeed(7);
        testCreature.gainSpeed(5);
        speedTotal = speedTotal + 5 - 7 - 7 + 5;
        assertEquals(speedTotal, testCreature.getSpeed());
    }

    /**
     * Damage reduction tests
     */

    //IncrementDamageReduction
    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageReductionWithZero() {
        testCreature.incrementDamageReduction(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageReductionWithNegativeValue() {
        testCreature.incrementDamageReduction(-12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageReductionWithMoreThanHundred() {
        testCreature.incrementDamageReduction(101);
    }

    @Test
    public void testGetDamageReduction() {
        Creature creature = createPlayerWithCustomHPAndSpeed(100, 10);
        assertEquals(0, creature.getDamageReduction(), 0.0);
    }

    @Test
    public void testIncrementDamageReductionWithInt() {
        testCreature.incrementDamageReduction(10);
        assertEquals(10, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testIncrementDamageReductionWithDoubleOneDecimal() {
        testCreature.incrementDamageReduction(5.3);
        assertEquals(5.3, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testIncrementDamageReductionWithDoubleTwoDecimals() {
        testCreature.incrementDamageReduction(43.33);
        assertEquals(43.33, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testIncrementDamageReductionSeveralTimes() {
        testCreature.incrementDamageReduction(5.4);
        testCreature.incrementDamageReduction(57.13);
        testCreature.incrementDamageReduction(14);

        assertEquals(76.53, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testIncrementDamageReductionThatIncreaseDamageReductionToGreaterThanHundred() {
        testCreature.incrementDamageReduction(80);
        testCreature.incrementDamageReduction(40);
        assertEquals(100, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testGetIncrementDamageReductionRoundToTwoDecimals() {
        testCreature.incrementDamageReduction(1.123);
        testCreature.incrementDamageReduction(1.123);
        assertEquals(2.24, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testIncrementDamageReductionWithSeveralRandomValues() {
        double damageReduction = 0;

        for (int i = 1; i < 15; i++) {
            Random rnd = new Random();
            double damageReductionToAdd = i + rnd.nextDouble();
            testCreature.incrementDamageReduction(damageReductionToAdd);
            double damageReductionUpdateValue = Math.round(damageReductionToAdd * 100);
            damageReduction += (damageReductionUpdateValue / 100);

            if (damageReduction > 100) {
                assertEquals(100, testCreature.getDamageReduction(), 0.0);
            } else {
                assertEquals(damageReduction, testCreature.getDamageReduction(), 0.0);
            }
        }
    }

    //DecrementDamageReduction
    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageReductionWithZero() {
        testCreature.decrementDamageReduction(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageReductionWithNegativeValue() {
        testCreature.decrementDamageReduction(-77);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageReductionWithMoreThanHundred() {
        testCreature.decrementDamageReduction(104);
    }

    @Test
    public void testDecrementDamageReductionWithInt() {
        testCreature.incrementDamageReduction(10);
        testCreature.decrementDamageReduction(5);
        assertEquals(5, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionWithDoubleOneDecimal() {
        testCreature.incrementDamageReduction(15);
        testCreature.decrementDamageReduction(5.1);
        assertEquals(9.9, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionWithDoubleTwoDecimals() {
        testCreature.incrementDamageReduction(17);
        testCreature.decrementDamageReduction(14.55);
        assertEquals(2.45, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionWithDoubleFourDecimals() {
        testCreature.incrementDamageReduction(17);
        testCreature.decrementDamageReduction(14.5541);
        assertEquals(2.45, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionSeveralTimes() {
        testCreature.incrementDamageReduction(77);
        testCreature.decrementDamageReduction(13.55);
        assertEquals(63.45, testCreature.getDamageReduction(), 0.0);
        testCreature.decrementDamageReduction(37.589);
        assertEquals(25.86, testCreature.getDamageReduction(), 0.0);
        testCreature.decrementDamageReduction(24.3);
        assertEquals(1.56, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionSeveralRandomValues() {
        testCreature.incrementDamageReduction(85);
        double damageReduction = 85;

        for (int i = 11; i < 35; i++) {
            Random rnd = new Random();
            double damageReductionToSubtract = i + rnd.nextDouble();
            testCreature.decrementDamageReduction(damageReductionToSubtract);

            double newDamageReduction = Math.round((damageReduction - damageReductionToSubtract) * 100);
            damageReduction = (newDamageReduction / 100);

            if (damageReduction > 100) {
                assertEquals(100, testCreature.getDamageReduction(), 0.0);
            } else if (damageReduction < 0) {
                assertEquals(0, testCreature.getDamageReduction(), 0.0);
            } else {
                assertEquals(damageReduction, testCreature.getDamageReduction(), 0.0);
            }
        }
    }

    @Test
    public void testDecrementDamageReductionRoundToTwoDecimals() {
        testCreature.incrementDamageReduction(17.2);
        testCreature.decrementDamageReduction(15.1);
        assertEquals(2.1, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionToLessThanZero() {
        testCreature.incrementDamageReduction(12.3);
        testCreature.decrementDamageReduction(13.11);
        assertEquals(0, testCreature.getDamageReduction(), 0.0);
    }

    /**
     * Damage bonus tests
     */

    //IncrementDamageBonus
    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageBonusWithZero() {
        testCreature.incrementDamageBonus(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageBonusWithNegativeValue() {
        testCreature.incrementDamageBonus(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageBonusWithMoreThanHundred() {
        testCreature.incrementDamageBonus(102);
    }

    @Test
    public void testGetDamageBonus() {
        Creature creature = createPlayerWithCustomHPAndSpeed(100, 10);
        assertEquals(0, creature.getDamageBonus(), 0.0);
    }

    @Test
    public void testIncrementDamageBonusWithAnInt() {
        testCreature.incrementDamageBonus(5);
        assertEquals(5, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testIncrementDamageBonusWithADoubleOneDecimal() {
        testCreature.incrementDamageBonus(13.4);
        assertEquals(13.4, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testIncrementDamageBonusDoubleWithTwoDecimals() {
        testCreature.incrementDamageBonus(63.49);
        assertEquals(63.49, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testIncrementDamageBonusRoundToTwoDecimals() {
        testCreature.incrementDamageBonus(15.333);
        assertEquals(15.33, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testIncrementDamageBonusThatIncreaseDamageBonusToGreaterThanHundred() {
        testCreature.incrementDamageBonus(55.34);
        testCreature.incrementDamageBonus(64.55);
        assertEquals(100, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testIncrementDamageBonusWithSeveralRandomValues() {
        double totalDamageBonus = 0;

        for (int i = 0; i < 20; i++) {
            Random rnd = new Random();
            double damageBonusToAdd = i + rnd.nextDouble();
            testCreature.incrementDamageBonus(damageBonusToAdd);
            double damageBonusUpdateValue = Math.round(damageBonusToAdd * 100);
            totalDamageBonus += (damageBonusUpdateValue / 100);

            if (totalDamageBonus > 100) {
                assertEquals(100, testCreature.getDamageBonus(), 0.0);
            } else {
                assertEquals(totalDamageBonus, testCreature.getDamageBonus(), 0.0);
            }
        }
    }

    //DecrementDamageBonus
    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageBonusWithZero() {
        testCreature.decrementDamageBonus(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageBonusWithNegativeValue() {
        testCreature.decrementDamageBonus(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageBonusWithMoreThanHundred() {
        testCreature.decrementDamageBonus(112);
    }

    @Test
    public void testDecrementDamageBonusWithInt() {
        testCreature.incrementDamageBonus(20);
        testCreature.decrementDamageBonus(5);
        assertEquals(15, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusWithDoubleOneDecimal() {
        testCreature.incrementDamageBonus(25);
        testCreature.decrementDamageBonus(5.3);
        assertEquals(19.7, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusWithDoubleTwoDecimals() {
        testCreature.incrementDamageBonus(33);
        testCreature.decrementDamageBonus(5.35);
        assertEquals(27.65, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusRoundToTwoDecimals() {
        testCreature.incrementDamageBonus(37);
        testCreature.decrementDamageBonus(3.258);
        assertEquals(33.74, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusWithDoubleFourDecimals() {
        testCreature.incrementDamageBonus(35);
        testCreature.decrementDamageBonus(12.3581);
        assertEquals(22.64, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusToLessThanZero() {
        testCreature.incrementDamageBonus(12.3);
        testCreature.decrementDamageBonus(43.7);
        assertEquals(0, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusSeveralTimes() {
        testCreature.incrementDamageBonus(63.12);
        testCreature.decrementDamageBonus(12.37);
        assertEquals(50.75, testCreature.getDamageBonus(), 0.0);
        testCreature.decrementDamageBonus(37.5819);
        assertEquals(13.17, testCreature.getDamageBonus(), 0.0);
        testCreature.decrementDamageBonus(12.5555);
        assertEquals(0.61, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusWithSeveralRandomValues() {
        testCreature.incrementDamageBonus(83.12);
        double damageBonus = 83.12;

        for (int i = 10; i < 30; i++) {
            Random rnd = new Random();
            double damageBonusToSubtract = i + rnd.nextDouble();
            testCreature.decrementDamageBonus(damageBonusToSubtract);

            double newDamageBonus = Math.round((damageBonus - damageBonusToSubtract) * 100);
            damageBonus = (newDamageBonus / 100);

            if (damageBonus > 100) {
                assertEquals(100, testCreature.getDamageBonus(), 0.0);
            } else if (damageBonus < 0) {
                assertEquals(0, testCreature.getDamageBonus(), 0.0);
            } else {
                assertEquals(damageBonus, testCreature.getDamageBonus(), 0.0);
            }
        }
    }

    /**
     * Add armor tests
     */

//    @Test
//    public void testAddArmorToInventoryTestingArmorAsKey() throws Exception {
//
//        Map<Item, List<Item>> inventory = setUpReflectionInventoryMapForAddItem();
//        testCreature.addItemToInventory(testArmor01);
//        assertTrue(inventory.containsKey(testArmor01));
//    }
//
//    @Test
//    public void testAddArmorToInventoryTestingListAsValue() throws Exception {
//
//        Map<Item, List<Item>> inventory = setUpReflectionInventoryMapForAddItem();
////        List<Item> itemList = inventory.get(Item);
//
//        testCreature.addItemToInventory(testArmor01);
//        inventory.get(testArmor01)
//        assertTrue(inventory.containsValue(itemList));
//    }

    @Test
    public void testAddArmorInventoryTestingArmorList() throws Exception{


        /*List<Item> inventory = setUpReflectionItemListForAddItem();
        testCreature.addItemToInventory(testArmor01);
        assertTrue(inventory.contains(testArmor01));*/

        when(mockInventoryFactory.create()).thenReturn(mockInventory);

        testCreature.addItemToInventory(mockArmor, "armor");
        verify(mockInventory).addItem(mockArmor, "armor");

    }

    /*@Test
    public void testAddArmorTiInventoryTestingArmorAsKeyTwoItems() throws Exception {

        List<Item> inventory = setUpReflectionItemListForAddItem();
        testCreature.addItemToInventory(testArmor01);
        testCreature.addItemToInventory(testArmor02);
        assertTrue(inventory.contains(testArmor02));
    }

    @Test(expected = NullPointerException.class)
    public void testAddArmorToInventoryTestingNull() {
        testCreature.addItemToInventory(null);
    }*/
}

