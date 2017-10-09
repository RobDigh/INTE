
public class Player {
	
	private int positionX, positionY = 0;
	
	private String name;
	
	private int speedPoint = 1;
	private int healthPoint = STARTHEALTH;
	private int levelPoint = 1;
	private int gold = 0;
	private boolean isAlive = true;
	
	private static final int STARTHEALTH = 100;
	
	public Player(){
		
	}
	
	public Player(int pX, int pY, String name, int speed, int gold, boolean alive){
		
		this.setPositionX(pX);
		this.setPositionY(pY);
		this.setName(name);
		this.setSpeedPoint(speed);
		this.setGold(gold);
		this.setAlive(alive);
		
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpeedPoint() {
		return speedPoint;
	}

	public void setSpeedPoint(int speedPoint) {
		this.speedPoint = speedPoint;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getLevelPoint() {
		return levelPoint;
	}

	public void setLevelPoint(int levelPoint) {
		this.levelPoint = levelPoint;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
}
