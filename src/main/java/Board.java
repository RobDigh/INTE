public class Board {

	private static final int DEFAULT_BOARD_LENGTH = 10;
	private static final int DEFAULT_BOARD_HEIGHT = 10;
	
	private int boardXvalue;
	private int boardYvalue;
	
	public void setDefaultBoardValueX(){
		this.boardXvalue = DEFAULT_BOARD_LENGTH;
	}
	
	public void setDefaultBoardValueY(){
		this.boardYvalue = DEFAULT_BOARD_HEIGHT;
	}
	
	public void createCustomBoard(int newX, int newY){
		boardXvalue = newX;
		boardYvalue = newY;
	}
	
	public int getBoardValueX(){
		return boardXvalue;
	}
	
	public int getBoardValueY(){
		return boardYvalue;
	}
}
