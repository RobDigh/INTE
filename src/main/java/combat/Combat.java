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

        c1.act(c2);
        c2.act(c1);
        c2.act(c1);

    }

    public boolean getResult() {
        return INITIATOR_WIN;
    }
}
