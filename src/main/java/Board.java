public class Board {

	private static final int DEFAULT_BOARD_LENGTH = 10;
	private static final int DEFAULT_BOARD_HEIGHT = 10;

	private int boardXvalue;
	private int boardYvalue;

	public int[][] map;

	public void setDefaultBoardValues() {
		this.boardXvalue = DEFAULT_BOARD_LENGTH;
		this.boardYvalue = DEFAULT_BOARD_HEIGHT;
	}

	public void createCustomBoardSize(int newX, int newY) {

		if (newX < 0 || newY < 0) {
			throw new IllegalArgumentException(
					"Board X/Y values can't be negative!");
		} else if (newX < 10 || newY < 10) {
			throw new IllegalArgumentException(
					"Board X/Y values can't be less than 10");
		} else {
			boardXvalue = newX;
			boardYvalue = newY;
		}

	}

	public void createMap() {
		map = new int[this.boardXvalue][this.boardYvalue];
		
		int tilenumber = 0;

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {

				map[x][y] = tilenumber;
				tilenumber++;
			}
		}
	}

	public int getBoardValueX() {
		return boardXvalue;
	}

	public int getBoardValueY() {
		return boardYvalue;
	}
}
