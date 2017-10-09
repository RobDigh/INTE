public class Player {

    private String name;
    private int lives;

    public Player(String name){
        this.name = name;
        lives = 5;
    }

    public String getName(){
        return name;
    }

    public int getLives(){
        return lives;
    }

    public void addLife(){
        lives += 1;
    }

    public int getSpeed(){
        return 100;
    }
}
