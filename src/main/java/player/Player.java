package player;

public class Player {

    String name;
    int life;

    //default life if not specified
    public Player(String name){
        this.name = name;
        this.life = 100;
    }

    public Player(String name, int life){
    this.name = name;
    this.life = life;

    }




}
