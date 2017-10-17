package entity.creature;

import java.util.ArrayList;
import java.util.EnumSet;

public class PlayerCharacter extends Creature {
    private Type type;

    public PlayerCharacter(int strength, int dexterity, int constitution) {
        super(strength, dexterity, constitution, true);

        type = calculateBreed(strength, dexterity, constitution);
        if (type == null) {
            throw new IllegalArgumentException("There is no type that corresponds with these stat values");
        }
    }

    public Type getType() {
        return type;
    }

    public Type calculateBreed(int strength, int dexterity, int constitution) {
        ArrayList<Type> temp = new ArrayList<>(EnumSet.allOf(Type.class));
        for (int i = 0; i < temp.size(); i++) {
            Type type = temp.get(i);
            if (type.getStrength() == strength && type.getDexterity() == dexterity && type.getConstitution() == constitution) {
                return type;
            }
        }
        return null;
    }
}
