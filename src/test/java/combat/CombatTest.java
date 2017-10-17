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

    @Test
    public void activeImmobilizedWin() {

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {

                Creature c1 = invocation.getArgument(0);
                c1.loseHP(1);

                return null;

            }
        }).doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {

                c2.loseHP(100);
                return null;

            }
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
}
