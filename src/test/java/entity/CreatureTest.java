package entity;

import com.sun.tracing.dtrace.ProviderAttributes;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CreatureTest {

    private Creature createPlayerWithCustomHPAndSpeed(int hp, int speed) {
        return new Creature(hp, speed);
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
    public void testGain0HP() {
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
    public void testLose0HP() {
        thrown.expect(IllegalArgumentException.class);
        Creature creature = createPlayerWithCustomHPAndSpeed(500, 10);
        creature.loseHP(0);
    }
}
