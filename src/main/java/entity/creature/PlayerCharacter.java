package entity.creature;

import java.util.ArrayList;
import java.util.EnumSet;

public class PlayerCharacter extends Creature {
    private Breed breed;

    public PlayerCharacter(int strength, int dexterity, int constitution) {
        super(strength, dexterity, constitution);

        breed = calculateBreed(strength, dexterity, constitution);
        if (breed == null) {
            throw new IllegalArgumentException("There is no breed that corresponds with these stat values");
        }
    }

    public Breed getBreed() {
        return breed;
    }

    public Breed calculateBreed(int strength, int dexterity, int constitution) {
        ArrayList<Breed> temp = new ArrayList<>(EnumSet.allOf(Breed.class));
        for (int i = 0; i < temp.size(); i++) {
            Breed breed = temp.get(i);
            if (breed.getStrength() == strength && breed.getDexterity() == dexterity && breed.getConstitution() == constitution) {
                return breed;
            }
        }
        return null;
    }
}
