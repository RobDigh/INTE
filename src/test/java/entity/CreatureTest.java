package entity;

import entity.item.Item;
import entity.item.wearable.armor.Armor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreatureTest {

    private Creature testCreature;
    private Armor testArmor;

    private Creature createPlayerWithCustomHPAndSpeed(int hp, int speed) {
        return new Creature(hp, speed);
    }

    @Before
    public void setUp() {
        testCreature = createPlayerWithCustomHPAndSpeed(100, 10);
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

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageReductionWithZero() {
        testCreature.incrementDamageReduction(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageReductionWithMoreThanHundred() {
        testCreature.incrementDamageReduction(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageBonusWithZero() {
        testCreature.incrementDamageBonus(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncrementDamageBonusWithMoreThanHundred() {
        testCreature.incrementDamageBonus(102);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageReductionWithZero(){
        testCreature.decrementDamageReduction(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageReductionWithMoreThanHundred() {
        testCreature.decrementDamageReduction(104);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageBonusWithzero() {
        testCreature.decrementDamageBonus(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecrementDamageBonusWithMoreThanHundred() {
        testCreature.decrementDamageBonus(112);
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


    /**
     * Damage reduction tests
     */

    //Tests for increment
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

    //Tests for decrement
    @Test
    public void testDecrementDamageReductionWithInt(){
        testCreature.incrementDamageReduction(10);
        testCreature.decrementDamageReduction(5);
        assertEquals(5, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionWithDoubleOneDecimal(){
        testCreature.incrementDamageReduction(15);
        testCreature.decrementDamageReduction(5.1);
        assertEquals(9.9, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionWithDoubleTwoDecimals(){
        testCreature.incrementDamageReduction(17);
        testCreature.decrementDamageReduction(14.55);
        assertEquals(2.45, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionWithDoubleFourDecimals(){
        testCreature.incrementDamageReduction(17);
        testCreature.decrementDamageReduction(14.5541);
        assertEquals(2.45, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionSeveralTimes(){
        testCreature.incrementDamageReduction(77);
        testCreature.decrementDamageReduction(13.55);
        assertEquals(63.45, testCreature.getDamageReduction(), 0.0);
        testCreature.decrementDamageReduction(37.589);
        assertEquals(25.86, testCreature.getDamageReduction(), 0.0);
        testCreature.decrementDamageReduction(24.3);
        assertEquals(1.56, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionSeveralRandomValues(){
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
            } else if(damageReduction < 0) {
                assertEquals(0, testCreature.getDamageReduction(), 0.0);
            }else {
                assertEquals(damageReduction, testCreature.getDamageReduction(), 0.0);
            }
        }
    }

    @Test
    public void testDecrementDamageReductionRoundToTwoDecimals(){
        testCreature.incrementDamageReduction(17.2);
        testCreature.decrementDamageReduction(15.1);
        assertEquals(2.1, testCreature.getDamageReduction(), 0.0);
    }

    @Test
    public void testDecrementDamageReductionToLessThanZero(){
        testCreature.incrementDamageReduction(12.3);
        testCreature.decrementDamageReduction(13.11);
        assertEquals(0, testCreature.getDamageReduction(), 0.0);
    }

    /**
     * Damage bonus tests
     */
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
    @Test
    public void testDecrementDamageBonusWithInt(){
        testCreature.incrementDamageBonus(20);
        testCreature.decrementDamageBonus(5);
        assertEquals(15, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusWithDoubleOneDecimal(){
        testCreature.incrementDamageBonus(25);
        testCreature.decrementDamageBonus(5.3);
        assertEquals(19.7, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusWithDoubleTwoDecimals(){
        testCreature.incrementDamageBonus(33);
        testCreature.decrementDamageBonus(5.35);
        assertEquals(27.65, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testDecrementDamageBonusRoundToTwoDecimals(){
        testCreature.incrementDamageBonus(37);
        testCreature.decrementDamageBonus(3.258);
        assertEquals(33.74, testCreature.getDamageBonus(), 0.0);
    }

    @Test
    public void testAddArmorToInventory() throws Exception {

        testCreature = new Creature(1, 1);
        testArmor = new Armor(1);
        Field inventoryMapField = Creature.class.getDeclaredField("inventory");
        inventoryMapField.setAccessible(true);
        Map<Item, List<Item>> inventory = (Map<Item, List<Item>>) inventoryMapField.get(testCreature);


        testCreature.addArmorToInventory(testArmor);
        assertTrue(inventory.containsKey("armor"));
    }


}

