package entity.creature;

import combat.Combat;
import combat.CombatFactory;
import entity.item.consumable.hp.HealthPotion;
import entity.item.consumable.speed.SpeedPotion;
import entity.item.wearable.armor.Armor;
import entity.item.wearable.weapon.Weapon;
import net.bytebuddy.pool.TypePool;
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
    private Creature damagedTestCreature;
    private Inventory testInventory;
    private InventoryFactory testInventoryFactory;

    private Inventory mockInventory = mock(Inventory.class);
    private InventoryFactory mockInventoryFactory = mock(InventoryFactory.class);
    private Behaviour mockBehaviour = (mock(Behaviour.class));

    private Armor mockArmor = mock(Armor.class);
    private Weapon mockWeapon = mock(Weapon.class);
    private HealthPotion mockHealthPotion = mock(HealthPotion.class);
    private SpeedPotion mockSpeedPotion = mock(SpeedPotion.class);

    private CombatFactory mockCombatFactory = mock(CombatFactory.class);
    private Combat mockCombat = mock(Combat.class);

    private Creature createPlayerWithCustomStrengthDexterityAndConstitution(int strength, int dexterity, int constitution) {
        return new Creature(strength, dexterity, constitution, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Before
    public void setUp() {
        when(mockInventoryFactory.create()).thenReturn(mockInventory);
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(6, 6, 6);
        damagedTestCreature = createPlayerWithCustomStrengthDexterityAndConstitution(6, 6, 6);
        damagedTestCreature.loseHP(50);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreatureFleeCallsFleeMethodInBehaviour() {
        testCreature.flee(null);
        verify(mockBehaviour).flee(testCreature, null, true);
    }

    @Test
    public void testCreatureActCallsActMethodInBehaviour() {
        testCreature.act(testCreature);
        verify(mockBehaviour).act(testCreature);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testCallActWithNullCreature(){
        Creature creature = new Creature(5, 5, 5, true, mockInventoryFactory, new Behaviour(), mockCombatFactory);
        creature.act(null);
    }

    /**
     * Add constructor tests
     */

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNullInventoryFactory() {
        new Creature(6, 6, 6, true, null, new Behaviour(), mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructPlayerWithNullBehaviour() {
        new Creature(6, 6, 6, true, new InventoryFactory(), null, mockCombatFactory);
    }

    /**
     * Add tests for stat increase and decrease
     */

    @Test
    public void testGainStrength() {
        testCreature = new Creature(6, 5, 7, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.gainStrength(2);
        assertEquals(8, testCreature.getStrength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGainNegativeStrength() {
        testCreature.gainStrength(-8);
    }

    @Test
    public void testGainDexterity() {
        testCreature = new Creature(5, 5, 8, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.gainDexterity(2);
        assertEquals(7, testCreature.getDexterity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGainNegativeDexterity() {
        testCreature.gainDexterity(-5);
    }

    @Test
    public void testGainConstitution() {
        testCreature = new Creature(5, 5, 8, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.gainConstitution(2);
        assertEquals(10, testCreature.getConstitution());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGainNegativeConstitution() {
        testCreature.gainConstitution(-3);
    }

    /**
     * Add tests for MaxHP
     */

    @Test
    public void testGetMaxHP() {
        testCreature = new Creature(6, 6, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(6 * Creature.magicConstitutionHPNumber + 6 * Creature.magicStrengthHPNumber, testCreature.getMaxHP());
    }

    /**
     * Add tests for CurrentHP
     */

    @Test
    public void testGetCurrentHP() {
        testCreature = new Creature(6, 6, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.loseHP(30);
        assertEquals(6 * Creature.magicConstitutionHPNumber + 6 * Creature.magicStrengthHPNumber - 30, testCreature.getCurrentHP());
    }

    @Test
    public void testCalculateHp() {
        testCreature = new Creature(5, 5, 8, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(8 * 15 + 5 * 5, testCreature.getMaxHP());
        testCreature = new Creature(7, 5, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(6 * 15 + 7 * 5, testCreature.getMaxHP());
        testCreature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(5 * 15 + 5 * 5, testCreature.getMaxHP());
    }

    /**
     * Add tests for Speed
     */

    @Test
    public void testGetSpeed() {
        testCreature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(2, testCreature.getSpeed());
    }

    @Test
    public void testCalculateSpeedLowDex() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(5, 6, 7);
        testCreature.gainConstitution(5);
        assertEquals(1, testCreature.getSpeed());
    }

    @Test
    public void testCalculateSpeedDexSevenHighCon() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(5, 7, 6);
        assertEquals(2, testCreature.getSpeed());
    }

    @Test
    public void testCalculateSpeedDexSevenLowCon() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(6, 7, 5);
        assertEquals(1, testCreature.getSpeed());
    }

    @Test
    public void testCalculateSpeedHighDexHighCon() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(5, 8, 5);
        testCreature.gainConstitution(2);
        assertEquals(3, testCreature.getSpeed());
    }

    @Test
    public void testCalculateSpeedHighDexLowCon() {
        testCreature = createPlayerWithCustomStrengthDexterityAndConstitution(5, 8, 5);
        testCreature.gainConstitution(1);
        assertEquals(2, testCreature.getSpeed());
    }

    /**
     * Add tests for HP gain and loss
     */

    @Test
    public void testGainHP() {
        int originalHP = damagedTestCreature.getCurrentHP();
        damagedTestCreature.gainHP(10);
        assertEquals(originalHP + 10, damagedTestCreature.getCurrentHP());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGainNegativeHP() {
        testCreature.gainHP(-5);
    }

    @Test
    public void testGainZeroHP() {
        int originalHP = testCreature.getMaxHP();
        testCreature.gainHP(0);
        assertEquals(originalHP, testCreature.getCurrentHP());
    }

    @Test
    public void testLoseHP() {
        testCreature = new Creature(6, 6, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.loseHP(50);
        assertEquals((6 * Creature.magicConstitutionHPNumber + 6 * Creature.magicStrengthHPNumber) - 50, testCreature.getCurrentHP());
    }

    @Test
    public void testLoseNegativeHP() {
        thrown.expect(IllegalArgumentException.class);
        testCreature.loseHP(-5);
    }

    @Test
    public void testLoseZeroHP() {
        int originalHP = testCreature.getCurrentHP();
        testCreature.loseHP(0);
        assertEquals(originalHP, testCreature.getCurrentHP());
    }

    @Test
    public void testGoBelowZeroHP() {
        testCreature = new Creature(6, 6, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.loseHP(testCreature.getCurrentHP() + 50);
        assertEquals(0, testCreature.getCurrentHP());
    }

    @Test
    public void testGainHPSeveralTimes() {
        int startingHP = damagedTestCreature.getCurrentHP();
        damagedTestCreature.gainHP(5);
        damagedTestCreature.gainHP(10);
        damagedTestCreature.gainHP(8);
        assertEquals(startingHP + 5 + 10 + 8, damagedTestCreature.getCurrentHP());
    }

    @Test
    public void testGainHPAboveHPMax(){
        damagedTestCreature.gainHP(11000);
        assertEquals(damagedTestCreature.getMaxHP(), damagedTestCreature.getCurrentHP());
    }

    @Test
    public void testGainHPSeveralTimesWithResultOverMaxHp(){
        damagedTestCreature.gainHP(10);
        damagedTestCreature.gainHP(30);
        damagedTestCreature.gainHP(10000);
        damagedTestCreature.gainHP(3);
        assertEquals(damagedTestCreature.getMaxHP(), damagedTestCreature.getCurrentHP());
    }

    @Test
    public void testLoseHPSeveralTimes() {
        int hpTotal = testCreature.getCurrentHP();
        testCreature.loseHP(10);
        testCreature.loseHP(5);
        testCreature.loseHP(70);
        hpTotal -= (10 + 5 + 70);
        assertEquals(hpTotal, testCreature.getCurrentHP());
    }

    @Test
    public void testLoseHPSeveralTimesToBelowZero() {
        testCreature.loseHP(50);
        testCreature.loseHP(7);
        testCreature.loseHP(8);
        testCreature.loseHP(90);
        testCreature.loseHP(80);
        assertEquals(0, testCreature.getCurrentHP());
    }

    @Test
    public void testIncreaseThenDecreaseThenIncreaseAgain() {
        int hpTotal = damagedTestCreature.getCurrentHP();
        damagedTestCreature.gainHP(10);
        damagedTestCreature.gainHP(5);
        hpTotal += (10+5);
        assertEquals(hpTotal, damagedTestCreature.getCurrentHP());
        assertEquals(hpTotal, damagedTestCreature.getCurrentHP());
        damagedTestCreature.loseHP(30);
        damagedTestCreature.loseHP(20);
        hpTotal -= (30 + 20);
        assertEquals(hpTotal, damagedTestCreature.getCurrentHP());
        damagedTestCreature.gainHP(50);
        hpTotal += 50;
        assertEquals(hpTotal, damagedTestCreature.getCurrentHP());
    }

    @Test
    public void testDecreaseBelowZeroHPThenIncreaseAgain() {
        while (0 < testCreature.getCurrentHP()) {
            testCreature.loseHP(9);
        }
        assertEquals(0, testCreature.getCurrentHP());
        testCreature.gainHP(5);
        assertEquals(0, testCreature.getCurrentHP());
    }

    /**
     * Add tests for Speed gain and loss
     */

    @Test
    public void testGainSpeed() {
        testCreature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.gainSpeed(5);
        assertEquals(7, testCreature.getSpeed());
    }

    @Test
    public void testGainNegativeSpeed() {
        thrown.expect(IllegalArgumentException.class);
        testCreature.gainSpeed(-5);
    }

    @Test
    public void testGainZeroSpeed() {
        int originalSpeed = testCreature.getSpeed();
        testCreature.gainSpeed(0);
        assertEquals(originalSpeed, testCreature.getSpeed());
    }

    @Test
    public void testLoseSpeed() {
        testCreature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.loseSpeed(2);
        assertEquals(0, testCreature.getSpeed());
    }

    @Test
    public void testLoseNegativeSpeed() {
        thrown.expect(IllegalArgumentException.class);
        testCreature.loseSpeed(-5);
    }

    @Test
    public void testLoseZeroSpeed() {
        int originalSpeed = testCreature.getSpeed();
        testCreature.loseSpeed(0);
        assertEquals(originalSpeed, testCreature.getSpeed());
    }

    @Test
    public void testGoBelowZeroSpeed() {
        testCreature.loseSpeed(9);
        assertEquals(0, testCreature.getSpeed());
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
        testCreature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        testCreature.gainSpeed(10);
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
        testCreature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        int speedTotal = testCreature.getSpeed();
        testCreature.gainSpeed(3);
        testCreature.loseSpeed(2);
        testCreature.loseSpeed(2);
        testCreature.gainSpeed(5);
        speedTotal = speedTotal + 3 - 2 - 2 + 5;
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
        assertEquals(0, testCreature.getDamageReduction(), 0.0);
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
        assertEquals(0, testCreature.getDamageBonus(), 0.0);
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

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullItemToInventory() {
        testCreature = new Creature(6,6, 6,
                true, new InventoryFactory(), new Behaviour(), mockCombatFactory);
        testCreature.addItemToInventory(null, "weapon");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullKeyToInventory() {
        testCreature = new Creature(6,6, 6,
                true, new InventoryFactory(), new Behaviour(), mockCombatFactory);
        testCreature.addItemToInventory(mockWeapon, null);
    }

    @Test
    public void testAddTwoItemsToInventory() throws Exception {

        testCreature.addItemToInventory(mockWeapon, "weapon");
        testCreature.addItemToInventory(mockArmor, "armor");
        verify(mockInventory).addItem(mockArmor, "armor");
        verify(mockInventory).addItem(mockWeapon, "weapon");
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

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNullItemFromInventory() {
        testCreature = new Creature(6,6, 6, true, new InventoryFactory(), new Behaviour(), mockCombatFactory);
        testCreature.removeItemFromInventory(null, "weapon");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWithNullKeyFromInventory() {
        testCreature = new Creature(6,6, 6, true, new InventoryFactory(), new Behaviour(), mockCombatFactory);
        testCreature.removeItemFromInventory(mockWeapon, null);
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

        assertEquals(1, testCreature.getSpeed());
        assertEquals(7 * Creature.magicConstitutionHPNumber + 5 * Creature.magicStrengthHPNumber, testCreature.getCurrentHP());
        assertEquals(Type.GREYHOUND, testCreature.getType());
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
        assertEquals(6, testCreature.getConstitution());
    }

    /**
     * Add tests for calculateType
     */
    @Test
    public void testCalculateTypeLabrador() {
        Creature creature = new Creature(6, 6, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.LABRADOR, creature.getType());
    }

    @Test
    public void testCalculateTypeOvtjarka() {
        Creature creature = new Creature(8, 5, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.OVTJARKA, creature.getType());
    }

    @Test
    public void testCalculateTypeDobermann() {
        Creature creature = new Creature(7, 6, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.DOBERMANN, creature.getType());
    }

    @Test
    public void testCalculateTypeStBernard() {
        Creature creature = new Creature(7, 5, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.STBERNARD, creature.getType());
    }

    @Test
    public void testCalculateTypeChihuahua() {
        Creature creature = new Creature(5, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.CHIHUAHUA, creature.getType());
    }

    @Test
    public void testCalculateTypeDachshund() {
        Creature creature = new Creature(5, 7, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.DACHSHUND, creature.getType());
    }

    @Test
    public void testCalculateTypeSiberianHusky() {
        Creature creature = new Creature(6, 7, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.SIBERIANHUSKY, creature.getType());
    }

    @Test
    public void testCalculateTypeVizsla() {
        Creature creature = new Creature(5, 5, 8, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.VIZSLA, creature.getType());
    }

    @Test
    public void testCalculateTypeGreyhound() {
        Creature creature = new Creature(5, 6, 7, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.GREYHOUND, creature.getType());
    }

    @Test
    public void testCalculateTypeBulldog() {
        Creature creature = new Creature(6, 5, 7, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.BULLDOG, creature.getType());
    }

    @Test
    public void testCalculateTypeBear() {
        Creature creature = new Creature(8, 5, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.BEAR, creature.getType());
    }

    @Test
    public void testCalculateTypeCar() {
        Creature creature = new Creature(7, 6, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.CAR, creature.getType());
    }

    @Test
    public void testCalculateTypeCat() {
        Creature creature = new Creature(5, 7, 6, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.CAT, creature.getType());
    }

    @Test
    public void testCalculateTypeFox() {
        Creature creature = new Creature(6, 7, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.FOX, creature.getType());
    }

    @Test
    public void testCalculateTypeHuman() {
        Creature creature = new Creature(6, 6, 6, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.HUMAN, creature.getType());
    }

    @Test
    public void testCalculateTypeJogger() {
        Creature creature = new Creature(6, 5, 7, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.JOGGER, creature.getType());
    }

    @Test
    public void testCalculateTypeMailman() {
        Creature creature = new Creature(5, 6, 7, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.MAILMAN, creature.getType());
    }

    @Test
    public void testCalculateTypeMoose() {
        Creature creature = new Creature(5, 5, 8, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.MOOSE, creature.getType());
    }

    @Test
    public void testCalculateTypeSquirrel() {
        Creature creature = new Creature(5, 8, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.SQUIRREL, creature.getType());
    }

    @Test
    public void testCalculateTypeWolf() {
        Creature creature = new Creature(7, 5, 6, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
        assertEquals(Type.WOLF, creature.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidValueStrength() {
        new Creature(4, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeNegativeValueStrength() {
        new Creature(-7, 5, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToHighStrengthValue() {
        new Creature(9, 6, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToLowValueDexterity() {
        new Creature(5, 3, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToNegativeValueDexterity() {
        new Creature(5, -3, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToHighDexterityValue() {
        new Creature(6, 23, 7, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToLowValueConstitution() {
        new Creature(6, 7, 2, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToNegativeValueConstitution() {
        new Creature(7, 6, -22, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeWithToHighConstitutionValue() {
        new Creature(5, 5, 13, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionAllEight() {
        new Creature(8, 8, 8, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoStatIsSeven() {
        new Creature(7, 7, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoStatsOfSix() {
        new Creature(5, 6, 6, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionAllFive() {
        new Creature(5, 5, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionEightAndSixTogether() {
        new Creature(5, 6, 8, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionEightAndSevenTogether() {
        new Creature(7, 8, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSix() {
        new Creature(5, 6, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePCTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSeven() {
        new Creature(7, 5, 5, true, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidValueStrength() {
        new Creature(4, 8, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeNegativeValueStrength() {
        new Creature(-7, 5, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToHighStrengthValue() {
        new Creature(9, 6, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToLowValueDexterity() {
        new Creature(5, 3, 6, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToNegativeValueDexterity() {
        new Creature(5, -3, 6, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToHighDexterityValue() {
        new Creature(6, 23, 7, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToLowValueConstitution() {
        new Creature(6, 7, 2, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToNegativeValueConstitution() {
        new Creature(7, 6, -22, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeWithToHighConstitutionValue() {
        new Creature(5, 5, 13, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionAllEight() {
        new Creature(8, 8, 8, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoStatIsSeven() {
        new Creature(7, 7, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoStatsOfSix() {
        new Creature(5, 6, 6, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionAllFive() {
        new Creature(5, 5, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionEightAndSixTogether() {
        new Creature(5, 6, 8, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionEightAndSevenTogether() {
        new Creature(7, 8, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSix() {
        new Creature(5, 6, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateMonsterTypeInvalidCombinationStrengthDexterityConstitutionTwoFiveOneSeven() {
        new Creature(7, 5, 5, false, mockInventoryFactory, mockBehaviour, mockCombatFactory);
    }
}