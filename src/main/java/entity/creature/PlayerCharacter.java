package entity.creature;

public class PlayerCharacter extends Creature{

    public PlayerCharacter(int strength, int dexterity, int constitution){
        super(strength, dexterity, constitution);
    }

    public Breed getBreed(){
        return Breed.CHIHUAHUA;
    }
}
