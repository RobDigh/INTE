package entity.creature;

import entity.item.consumable.hp.HealthPotion;
import entity.item.consumable.speed.SpeedPotion;
import entity.item.wearable.armor.Armor;
import entity.item.wearable.weapon.Weapon;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatureTest {
    private Creature testCreature;

    private Inventory mockInventory = mock(Inventory.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Behaviour mockBehaviour = (mock(Behaviour.class));

    private Armor mockArmor = mock(Armor.class);
    private Weapon mockWeapon = mock(Weapon.class);
    private HealthPotion mockHealthPotion = mock(HealthPotion.class);
    private SpeedPotion mockSpeedPotion = mock(SpeedPotion.class);

    private Creature createPlayerWithCustomHPAndSpeed(int hp, int speed) {
        return new Creature(hp, speed, 5, 8, 5, true, mockInventoryFactory, mockBehaviour);
    }

    private Creature createPlayerWithCustomStrengthDexterityAndConstitution(int strength, int dexterity, int constitution) {
        return new Creature(10, 100, strength, dexterity, constitution, true, mockInventoryFactory, mockBehaviour);
    }

    @Before
    public void setUp() {
        when(mockInventoryFactory.create()).thenReturn(mockInventory);
        testCreature = createPlayerWithCustomHPAndSpeed(100, 10);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreatureFleeCallsFleeMethodInAi() {
        testCreature.flee(null);
        verify(mockBehaviour).flee(testCreature, null, true);
    }

    @Test
    public void testCreatureActCallsActMethodInAi() {
        testCreature.act(testCreature);
        verify(mockBehaviour).act();
    }

    /**
     * Add constructor tests
     */

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

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNullInventoryFactory() {
        new Creature(10, 10, 6, 6, 6, true, null, new Behaviour());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNullBehaviour() {
        new Creature(10, 10, 6, 6, 6, true, new InventoryFactory(), null);
    }

    /**
     * Add tests for HP
     */

    @Test
    public void testGetHP() {
        Creature creature = createPlayerWithCustomHPAndSpeed(500, 10);
        assertEquals(500, creature.getHP());
    }

    @Test
    public void testCalculateHp() {
        assertEquals(8 * 15 + 5 * 5, testCreature.calculateHP(5, 8));
        assertEquals(6 * 15 + 7 * 5, testCreature.calculateHP(7, 6));
        assertEquals(5 * 15 + 5 * 5, testCreature.calculateHP(5, 5));
        assertEquals((-10) * 15 + 99 * 5, testCreature.calculateHP(99, (-10)));
    }

    /**
     * Add tests for Speed
     */

    @Test
    public void testGetSpeed() {
        Creature creature = createPlayerWithCustomHPAndSpeed(500, 10);
        assertEquals(10, creature.getSpeed());
    }

    @Test
    public void testCalculateSpeedLowDex() {
        assertEquals(1, testCreature.calculateSpeed(6, 12));
    }

    @Test
    public void testCalculateSpeedDexSevenHighCon() {
        assertEquals(2, testCreature.calculateSpeed(7, 6));
    }

    @Test
    public void testCalculateSpeedDexSevenLowCon() {
        assertEquals(1, testCreature.calculateSpeed(7, 3));
    }

    @Test
    public void testCalculateSpeedHighDexHighCon() {
        assertEquals(3, testCreature.calculateSpeed(8, 7));
    }

    @Test
    public void testCalculateSpeedHighDexLowCon() {
        assertEquals(2, testCreature.calculateSpeed(8, 6));
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
     * Add item tests
     */

    @Test(expected = NullPointerException.class)
    public void testAddNullToInventory() {
        testCreature.addItemToInventory(null, "null");
    }

    @Test
    public void testAddArmorToInventory() throws Exception {

        testCreature.addItemToInventory(mockArmor, "armor");
        verify(mockInventory).addItem(mockArmor, "armor");
    }

    @Test
    public void testAddWeaponToInventory() throws Exception {

        testCreature.addItemToInventory(mockWeapon, "weapon");
        verify(mockInventory).addItem(mockWeapon, "weapon");
    }

    @Test
    public void testAddHealthPotionToInventory() throws Exception {

        testCreature.addItemToInventory(mockHealthPotion, "health potion");
        verify(mockInventory).addItem(mockHealthPotion, "health potion");
    }

    @Test
    public void testAddSpeedPotion() throws Exception {

        testCreature.addItemToInventory(mockSpeedPotion, "speed potion");
        verify(mockInventory).addItem(mockSpeedPotion, "speed potion");
    }

    /**
     * Remove item from inventory
     */

    @Test(expected = NullPointerException.class)
    public void testRemoveNullFromInventory() {
        testCreature.removeItemFromInventory(null, "null");
    }

    @Test
    public void testRemoveArmor() throws Exception {
        testCreature.addItemToInventory(mockArmor, "armor");
        testCreature.removeItemFromInventory(mockArmor, "armor");
        verify(mockInventory).removeItem(mockArmor, "armor");
    }

    @Test
    public void testRemoveWeapon() throws Exception {
        testCreature.addItemToInventory(mockWeapon, "weapon");
        testCreature.removeItemFromInventory(mockWeapon, "weapon");
        verify(mockInventory).removeItem(mockWeapon, "weapon");
    }

    @Test
    public void testRemoveHealthPotion() throws Exception {
        testCreature.addItemToInventory(mockHealthPotion, "health potion");
        testCreature.removeItemFromInventory(mockHealthPotion, "health potion");
        verify(mockInventory).removeItem(mockHealthPotion, "health potion");
    }

    @Test
    public void testRemoveSpeedPotion() throws Exception {
        testCreature.addItemToInventory(mockSpeedPotion, "speed potion");
        testCreature.removeItemFromInventory(mockSpeedPotion, "speed potion");
        verify(mockInventory).removeItem(mockSpeedPotion, "speed potion");
    }

    /**
     * Add tests for constructor
     */

    @Test
    public void testCreateCreature() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(5, 6, 7);
        assertEquals(5, testCreature.getStrength());
        assertEquals(6, testCreature.getDexterity());
        assertEquals(7, testCreature.getConstitution());

        assertEquals(100, testCreature.getSpeed());
        assertEquals(10, testCreature.getHP());
        assertEquals(Type.GREYHOUND, testCreature.getType());
        assertTrue(testCreature.isPC());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidValueStrength() {
        createPlayerWithCustomStrengthDexterityAndConstitution(4, 8, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeValueStrength() {
        createPlayerWithCustomStrengthDexterityAndConstitution(-7, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToHighStrengthValue() {
        createPlayerWithCustomStrengthDexterityAndConstitution(9, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToLowValueDexterity() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, 3, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToNegativeValueDexterity() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, -3, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToHighDexterityValue() {
        createPlayerWithCustomStrengthDexterityAndConstitution(6, 23, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToLowValueConstitution() {
        createPlayerWithCustomStrengthDexterityAndConstitution(6, 7, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToNegativeValueConstitution() {
        createPlayerWithCustomStrengthDexterityAndConstitution(7, 6, -22);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithToHighConstitutionValue() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, 5, 13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionAllEight() {
        createPlayerWithCustomStrengthDexterityAndConstitution(8, 8, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionTwoStatIsSeven() {
        createPlayerWithCustomStrengthDexterityAndConstitution(7, 7, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionTwoStatsOfSix() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, 6, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionAllFive() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, 5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionEightAndSixTogether() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, 6, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionEightAndSevenTogether() {
        createPlayerWithCustomStrengthDexterityAndConstitution(7, 8, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSix() {
        createPlayerWithCustomStrengthDexterityAndConstitution(5, 6, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSeven() {
        createPlayerWithCustomStrengthDexterityAndConstitution(7, 5, 5);
    }

    /**
     * Add stat tests
     */

    @Test
    public void testGetStrength() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(6, 6, 6);
        assertEquals(6, testCreature.getStrength());
    }

    @Test
    public void testGetDexterity() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(6, 6, 6);
        assertEquals(6, testCreature.getDexterity());
    }

    @Test
    public void testGetConstitution() {
        createPlayerWithCustomStrengthDexterityAndConstitution(6, 6, 6);
        assertEquals(5, testCreature.getConstitution());
    }

    /**
     * Add tests for calculateType
     */
    @Test
    public void testCalculateTypeLabrador() {
        assertEquals(Type.LABRADOR, testCreature.calculateType(6, 6, 6, true));
    }

    @Test
    public void testCalculateTypeOvtjarka() {
        assertEquals(Type.OVTJARKA, testCreature.calculateType(8, 5, 5, true));
    }

    @Test
    public void testCalculateTypeDobermann() {
        assertEquals(Type.DOBERMANN, testCreature.calculateType(7, 6, 5, true));
    }

    @Test
    public void testCalculateTypeStBernard() {
        assertEquals(Type.STBERNARD, testCreature.calculateType(7, 5, 6, true));
    }

    @Test
    public void testCalculateTypeChihuahua() {
        assertEquals(Type.CHIHUAHUA, testCreature.calculateType(5, 8, 5, true));
    }

    @Test
    public void testCalculateTypeDachshund() {
        assertEquals(Type.DACHSHUND, testCreature.calculateType(5, 7, 6, true));
    }

    @Test
    public void testCalculateTypeSiberianHusky() {
        assertEquals(Type.SIBERIANHUSKY, testCreature.calculateType(6, 7, 5, true));
    }

    @Test
    public void testCalculateTypeVizsla() {
        assertEquals(Type.VIZSLA, testCreature.calculateType(5, 5, 8, true));
    }

    @Test
    public void testCalculateTypeGreyhound() {
        assertEquals(Type.GREYHOUND, testCreature.calculateType(5, 6, 7, true));
    }

    @Test
    public void testCalculateTypeBulldog() {
        assertEquals(Type.BULLDOG, testCreature.calculateType(6, 5, 7, true));
    }

    @Test
    public void testCalculateTypeBear() {
        assertEquals(Type.BEAR, testCreature.calculateType(8, 5, 5, false));
    }

    @Test
    public void testCalculateTypeCar() {
        assertEquals(Type.CAR, testCreature.calculateType(7, 6, 5, false));
    }

    @Test
    public void testCalculateTypeCat() {
        assertEquals(Type.CAT, testCreature.calculateType(5, 7, 6, false));
    }

    @Test
    public void testCalculateTypeFox() {
        assertEquals(Type.FOX, testCreature.calculateType(6, 7, 5, false));
    }

    @Test
    public void testCalculateTypeHuman() {
        assertEquals(Type.HUMAN, testCreature.calculateType(6, 6, 6, false));
    }

    @Test
    public void testCalculateTypeJogger() {
        assertEquals(Type.JOGGER, testCreature.calculateType(6, 5, 7, false));
    }

    @Test
    public void testCalculateTypeMailman() {
        assertEquals(Type.MAILMAN, testCreature.calculateType(5, 6, 7, false));
    }

    @Test
    public void testCalculateTypeMoose() {
        assertEquals(Type.MOOSE, testCreature.calculateType(5, 5, 8, false));
    }

    @Test
    public void testCalculateTypeSquirrel() {
        assertEquals(Type.SQUIRREL, testCreature.calculateType(5, 8, 5, false));
    }

    @Test
    public void testCalculateTypeWolf() {
        assertEquals(Type.WOLF, testCreature.calculateType(7, 5, 6, false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidValueStrength() {
        testCreature.calculateType(4, 8, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeNegativeValueStrength() {
        testCreature.calculateType(-7, 5, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToHighStrengthValue() {
        testCreature.calculateType(9, 6, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToLowValueDexterity() {
        testCreature.calculateType(5, 3, 6, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToNegativeValueDexterity() {
        testCreature.calculateType(5, -3, 6, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToHighDexterityValue() {
        testCreature.calculateType(6, 23, 7, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToLowValueConstitution() {
        testCreature.calculateType(6, 7, 2, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToNegativeValueConstitution() {
        testCreature.calculateType(7, 6, -22, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToHighConstitutionValue() {
        testCreature.calculateType(5, 5, 13, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionAllEight() {
        testCreature.calculateType(8, 8, 8, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoStatIsSeven() {
        testCreature.calculateType(7, 7, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoStatsOfSix() {
        testCreature.calculateType(5, 6, 6, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionAllFive() {
        testCreature.calculateType(5, 5, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionEightAndSixTogether() {
        testCreature.calculateType(5, 6, 8, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionEightAndSevenTogether() {
        testCreature.calculateType(7, 8, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSix() {
        testCreature.calculateType(5, 6, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSeven() {
        testCreature.calculateType(7, 5, 5, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidValueStrength() {
        testCreature.calculateType(4, 8, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeNegativeValueStrength() {
        testCreature.calculateType(-7, 5, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToHighStrengthValue() {
        testCreature.calculateType(9, 6, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToLowValueDexterity() {
        testCreature.calculateType(5, 3, 6, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToNegativeValueDexterity() {
        testCreature.calculateType(5, -3, 6, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToHighDexterityValue() {
        testCreature.calculateType(6, 23, 7, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToLowValueConstitution() {
        testCreature.calculateType(6, 7, 2, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToNegativeValueConstitution() {
        testCreature.calculateType(7, 6, -22, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToHighConstitutionValue() {
        testCreature.calculateType(5, 5, 13, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionAllEight() {
        testCreature.calculateType(8, 8, 8, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoStatIsSeven() {
        testCreature.calculateType(7, 7, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoStatsOfSix() {
        testCreature.calculateType(5, 6, 6, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionAllFive() {
        testCreature.calculateType(5, 5, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionEightAndSixTogether() {
        testCreature.calculateType(5, 6, 8, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionEightAndSevenTogether() {
        testCreature.calculateType(7, 8, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSix() {
        testCreature.calculateType(5, 6, 5, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSeven() {
        testCreature.calculateType(7, 5, 5, false);
    }
}

