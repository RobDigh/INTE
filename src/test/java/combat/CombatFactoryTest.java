package combat;

import entity.creature.Creature;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class CombatFactoryTest {

    private CombatFactory combatFactory = new CombatFactory();

    private Creature mockCreature1 = mock(Creature.class);
    private Creature mockCreature2 = mock(Creature.class);

    @Test
    public void testCreate() {
        assertFalse(combatFactory.create(mockCreature1, mockCreature2) == null);
    }
}
