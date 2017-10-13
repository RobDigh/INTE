package entity.item.consumable.speed;

import entity.Creature;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SpeedPotionTest {

    private Creature mockCreature = mock(Creature.class);

    private final int LOW_SPEED_BONUS = 2;
    private final int HIGH_SPEED_BONUS = 5;
    private final int NEGATIVE_SPEED_BONUS = -5;

    private SpeedPotion createSpeedPotion(int bonus) {
        return new SpeedPotion(bonus);
    }

    private SpeedPotion affectCreature(Creature creature) {
        return affectCreature(LOW_SPEED_BONUS, creature);
    }

    private SpeedPotion affectCreature(int bonus, Creature creature) {

        SpeedPotion speedPot = createSpeedPotion(bonus);
        speedPot.affect(creature);

        return speedPot;

    }

    @Test
    public void affectCreature() {

        SpeedPotion speedPot = affectCreature(mockCreature);
        verify(mockCreature).gainSpeed(speedPot.getBonus());

    }

    @Test
    public void affectCreatureCallsCreatureMethodWithBonusPassedToConstructor() {

        affectCreature(mockCreature);
        verify(mockCreature).gainSpeed(LOW_SPEED_BONUS);

        affectCreature(HIGH_SPEED_BONUS, mockCreature);
        verify(mockCreature).gainSpeed(HIGH_SPEED_BONUS);

    }
}
