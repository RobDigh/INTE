package combat;

import entity.creature.Creature;

public class CombatFactory {

    public Combat create(Creature c1, Creature c2) {
        return new Combat(c1, c2);
    }
}
