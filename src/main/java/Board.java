public class Board {

	public static final int DEFAULT_BOARD_LENGTH = 10;
	public static final int DEFAULT_BOARD_HEIGHT = 10;
	
	private int boardXvalue;
	private int boardYvalue;
	
	public void setDefaultBoardValueX(){
		this.boardXvalue = DEFAULT_BOARD_LENGTH;
	}
	
	public void setDefaultBoardValueY(){
		this.boardYvalue = DEFAULT_BOARD_HEIGHT;
	}
	
	public int getBoardValueX(){
		return boardXvalue;
	}
	
	public int getBoardValueY(){
		return boardYvalue;
	}
}
