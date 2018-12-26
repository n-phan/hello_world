package board;

public class Pegasus extends Angel{
	private int[] initialAngelMove;
	
	/**
	 * Constructs a Pegasus object with a given height, width, x, and y
	 * @param height the number of vertical boxes
	 * @param width the number of horizontal boxes
	 * @param x the x coordinate of the initial Angel
	 * @param y the y coordinate of the initial Angel
	 */
	public Pegasus(int height, int width, int x, int y) {
		super(height, width, x, y);
	}
	/**
	 * Constructs a Pegasus object with a given height and width
	 * Assumes the Pegasus will be placed in the center of the Board
	 * @param height the number of vertical boxes
	 * @param width the number of horizontal boxes
	 */
	public Pegasus(int height, int width) {
		super(height, width);
	}
	
	/**
	 * Updates this Board's board instance variable given (x, y) coordinates of the next move
	 * @param x the x-coordinate of the box with regards to the mouse's location
	 * @param y the y-coordinate of the box with regards to the mouse's location	 
	 */
	public void updateBoard(int x, int y) {
		board[x][y] = turn;
		
		if(turn == TURN_DEVIL) turn = TURN_ANGEL;
		else {
			burn(x, y);
			lastAngelMove[0] = y;
			lastAngelMove[1] = x;
			
			if(initialAngelMove == null) initialAngelMove = new int[] {lastAngelMove[0], lastAngelMove[1]};
			
			updatePossibleMoves();
			turn = TURN_DEVIL;
		}
	}
	/**
	 * Updates the Angel's possible moves on this Board given its (x, y) coordinates
	 */
	public void updatePossibleMoves() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(validMove(x, y)) board[x][y] = SPACE_POSSIBLE_ANGEL_MOVE;
				else if(board[x][y] == SPACE_POSSIBLE_ANGEL_MOVE) board[x][y] = SPACE_EMPTY;
			}
		}
	}
	/**
	 * Updates this Board's values instance variable given after a move has been made
	 */
	public void updateValues() {
		int updatedValues[][] = new int[height][width];
		int x = lastAngelMove[0];
		int y = lastAngelMove[1];
		int distance = 1;
		
		updatedValues[y][x] = 1;
		
		
		
		values = updatedValues;
	}
	/**
	 * Currently removes previous locations, needs extra parameters to overwrite parent Angel method
	 */
	public void burn(int col, int row) {
		int x = lastAngelMove[1];
		int y = lastAngelMove[0];
		board[x][y] = SPACE_EMPTY;
	}
	
	/**
	 * Sums the values of possible locations the given (x, y) coordinate could have originated from
	 * @param col the x-coordinate of the box with regards to the mouse's location
	 * @param row the y-coordinate of the box with regards to the mouse's location
	 * @param vals the array from which we will analyze
	 * @return value the sum of values from the possible locations the given (x, y) coordinate could have originated from
	 */
	public int findValue(int col, int row, int[][] vals) {
		if(getSpace(col, row) != SPACE_EMPTY && getSpace(col, row) != SPACE_POSSIBLE_ANGEL_MOVE)
			return 0;
		
		int value = 0;
		
		for(int x = lastAngelMove[1] - 2; x <= lastAngelMove[1] + 2; x++) {
			for(int y = lastAngelMove[0] - 2; y <= lastAngelMove[0] + 2; y++) {
				if(validMove(x, y))
					value += vals[y][x];
			}
		}
		System.out.println(value);
		return value;
	}
	/**
	 * Identifies whether the given (x, y) coordinates are a valid choice for a move for this Board
	 * Dependent upon the current turn of the game, whether it is the Pegasus's or Devil's move 
	 * @param x the x-coordinate of the box with regards to the mouse's location
	 * @param y the y-coordinate of the box with regards to the mouse's location
	 * @return false if the the mouse is outside of the Board's boundaries, or if the turn is currently Pegasus ensures that
	 * 	the move is valid with regards to the angel's power
	 * @return true otherwise as it is a valid move 
	 */
	public boolean validMove(int x, int y) {
		if(!inBounds(x, y) || board[x][y] == SPACE_DEVIL || board[x][y] == SPACE_ANGEL)
			return false;
		
		if(turn == TURN_ANGEL) {
			int dx = Math.abs(x - lastAngelMove[1]);
			int dy = Math.abs(y - lastAngelMove[0]);
		
			if(Math.sqrt(dx*dx + dy*dy) != Math.sqrt(5))
				return false;
		}
		
		return true;
	}
}
