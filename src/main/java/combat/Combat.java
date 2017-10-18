package combat;

import entity.creature.Creature;

public class Combat {

    public static final boolean INITIATOR_WIN = true;
    public static final boolean INITIATOR_LOSS = false;

    private Creature c1;
    private Creature c2;

    public Combat(Creature c1, Creature c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public void start() {

        Creature actor = c1;
        Creature defender = c2;

        while(c1.getHP() > 0 && c2.getHP() > 0) {

            if (actor.getHP() <= 10 && actor.getSpeed() > 0) {
                actor.flee();
            } else if (actor.getSpeed() > 0) {
                actor.act(defender);
            }

            Creature temp = actor;
            actor = defender;
            defender = temp;

        }
    }

    public boolean getResult() {
        return INITIATOR_WIN;
    }
}
