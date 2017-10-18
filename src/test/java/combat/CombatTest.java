package combat;

import entity.creature.Creature;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CombatTest {

    private Creature c1 = mock(Creature.class);
    private Creature c2 = mock(Creature.class);

    private Combat combat = new Combat(c1, c2);

    @Test
    public void activeImmobilizedWin() {

        doAnswer(invocation -> {

            Creature c1 = invocation.getArgument(0);
            c1.loseHP(1);

            return null;

        }).doAnswer(invocation -> {

            c2.loseHP(100);
            return null;

        }).when(c2).act(c1);


        Combat combat = new Combat(c1, c2);
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

        when(c1.getHP()).thenReturn(10);

        doAnswer(invocation -> {

                Creature c1 = invocation.getArgument(0);
                c1.loseHP(90);

                return null;

        }).doAnswer(invocation -> {

                c2.loseHP(100);
                return null;

        }).when(c2).act(c1);


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

        when(c1.getHP()).thenReturn(10).thenReturn(11);
        when(c1.getSpeed()).thenReturn(0).thenReturn(0).thenReturn(1);

        doAnswer(invocation -> null).doAnswer(invocation -> {

            Creature c2 = invocation.getArgument(0);
            c2.loseHP(100);

            return null;

        }).when(c1).act(c2);

        doAnswer(invocation -> {

            Creature c1 = invocation.getArgument(0);
            c1.loseHP(90);

            return null;

        }).doAnswer(invocation -> {

            Creature c1 = invocation.getArgument(0);
            c1.loseSpeed(1);

            return null;

        }).doAnswer(invocation -> null).when(c2).act(c1);

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
        verify(c1, times(2)).flee();

        verify(c2, times(4)).act(c1);
        assertEquals(Combat.INITIATOR_WIN, combat.getResult());

    }
}
