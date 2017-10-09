public class Player {

    private String name;
    private int lives;
    private int speed;

    public Player(String name){
        this.name = name;
        lives = 5;
        speed = 100;
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
        return speed;
    }

    public void increaseSpeed(int speed){
        this.speed += speed;
    }

}
