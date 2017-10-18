package combat;

import entity.creature.Creature;
import entity.creature.InventoryFactory;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CombatTest {

    /*
     * Creating Creature spies is mainly done to control combat behaviour while avoiding
     * having to exactly program which values should be returned on getHP() and getSpeed()
     * at different times.
     */
    private Creature c1 = spy(new Creature(100, 1, mock(InventoryFactory.class)));
    private Creature c2 = spy(new Creature(100, 1, mock(InventoryFactory.class)));

    private Combat combat = new Combat(c1, c2);

    private Void damageSpeed(InvocationOnMock invocationOnMock) {

        Creature opponent = invocationOnMock.getArgument(0);
        opponent.loseSpeed(1);

        return null;
    }

    private Void increaseOpponentsSpeed(InvocationOnMock invocationOnMock) {

        Creature opponent = invocationOnMock.getArgument(0);
        opponent.gainSpeed(1);

        return null;
    }

    private Void damageHP(InvocationOnMock invocationOnMock, int amount) {

        Creature opponent = invocationOnMock.getArgument(0);
        opponent.loseHP(amount);

        return null;
    }

    private Void increaseOpponentsHP(InvocationOnMock invocationOnMock) {

        Creature opponent = invocationOnMock.getArgument(0);
        opponent.gainHP(1);

        return null;
    }

    @Test
    public void activeImmobilizedWin() {

        doAnswer(this::damageSpeed)
                .doAnswer(invocationOnMock -> damageHP(invocationOnMock, 100))
                .when(c2).act(c1);

        combat.start();

        /*
         * Turn taking should be as follows:
         *
         * t1: c1 acts, does nothing.
         * t2: c2 acts, decreases c1 speed.
         * t3: c1 is immobilized, act() should not be called.
         * t4: c2 acts, hp gets decreased by poison or somesuch.
         *
         * If t1 was not immobilized, it would act 2 times. Verifying
         * that act() was only called once proves that it was indeed
         * immobilized.
         */
        verify(c1, times(1)).act(any());
        assertEquals(combat.getResult(), Combat.INITIATOR_WIN);

    }

    @Test
    public void activeFleeingWin() {

        doAnswer(invocationOnMock -> damageHP(invocationOnMock, 90))
                .doAnswer(invocationOnMock -> damageHP(invocationOnMock, 100))
                .when(c2).act(c1);

        combat.start();

        /*
         * Turn taking should be as follows:
         *
         * t1: c1 acts, does nothing.
         * t2: c2 acts, decreases c1 hp.
         * t3: c1 is fleeing, flee() should be called.
         * t4: c2 acts, hp gets decreased by poison or somesuch,
         * t1 wins.
         *
         */
        verify(c1, times(1)).flee();
        assertEquals(combat.getResult(), Combat.INITIATOR_WIN);

    }

    @Test
    public void activeFleeingImmobilizedActiveWin() {

        doAnswer(invocationOnMock -> null)
                .doAnswer(invocationOnMock -> damageHP(invocationOnMock, 100))
                .when(c1).act(c2);

        doAnswer(invocationOnMock -> damageHP(invocationOnMock, 90))
                .doAnswer(this::damageSpeed)
                .doAnswer(this::increaseOpponentsHP)
                .doAnswer(this::increaseOpponentsSpeed)
                .when(c2).act(c1);

        combat.start();

        /*
         * Turn taking should be as follows:
         *
         * t1: c1 acts, does nothing.
         * t2: c2 acts, decreases c1 hp.
         * t3: c1 is fleeing, flee() should be called.
         * t4: c2 acts, decreases c1 speed.
         * t5: c1 is immobilized, act() should not be called.
         * t6: c2 acts, does nothing. c1 regains hp.
         * t7: c1 is immobilized, act() should not be called.
         * t8: c2 acts, does nothing. c1 regains speed.
         * t9: c1 acts, decreases c2 hp and wins.
         *
         */

        verify(c1, times(2)).act(c2);
        verify(c1, times(1)).flee();

        verify(c2, times(4)).act(c1);
        assertEquals(Combat.INITIATOR_WIN, combat.getResult());

    }
}
