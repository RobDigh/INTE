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

    private boolean isDead(Creature creature) {
        return creature.getHP() <= 0;
    }

    private boolean combatantsAlive(Creature actor, Creature defender) {
        return !isDead(actor) && !isDead(defender);
    }

    private boolean isFleeing(Creature creature) {
        return creature.getHP() <= 10 && creature.getSpeed() > 0;
    }

    private boolean isImmobilized(Creature creature) {
        return creature.getSpeed() <= 0;
    }

    public void start() {

        Creature actor = c1;
        Creature defender = c2;

        while(combatantsAlive(actor, defender)) {

            if (isFleeing(actor)) {
                actor.flee();
            } else if (!isImmobilized(actor)) {
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
