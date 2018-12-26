package board;
import java.util.ArrayList;

/**
 * Board object that represents the Board of an Angel Problem game
 * @author Nathan Phan
 * @version 10/24/2018
 */
public class Angel {
	protected static int height, width;
	protected static int turn;
	protected static ArrayList<ArrayList<Space>> spaces;
	protected static int[] lastAngelMove; // row, col
	
	public static final int STATUS_EMPTY = 0;
	public static final int STATUS_DEVIL = 1;
	public static final int STATUS_ANGEL = 2;
	public static final int STATUS_POSSIBLE_ANGEL_MOVE = 3;
	
	public static final int TURN_DEVIL = 1;
	public static final int TURN_ANGEL = 2;
	
	public static final int POWER_ANGEL = 1;
	
	public Angel(int height, int width, int row, int col) {
		setHeight(height);
		setWidth(width);
		setTurn(TURN_ANGEL);
		createSpaces();
		update(row, col);
	}
	public Angel(int height, int width) {
		this(height, width, height/2, width/2);
	}
	
	public void update(int row, int col) {
		if(validMove(row, col)) {
			updateStatus(row, col);
			updateValues();
		}
	}
	public void updateStatus(int row, int col) {
		spaces.get(row).get(col).setStatus(turn);
		if(turn == TURN_DEVIL) turn = TURN_ANGEL;
		else {
			lastAngelMove[0] = row;
			lastAngelMove[1] = col;
			turn = TURN_DEVIL;
		}
	}
	public void updateValues() {
		int updatedValues[][] = new int[height][width];
		int y = lastAngelMove[0];
		int x = lastAngelMove[1];
		int distance = 1;
		
		updatedValues[y][x] = 1;
		
		while(y - distance >= 0 || y + distance < height || x - distance >= 0 || x + distance < width) {
			int temp[][] = copy2DArray(updatedValues);
			
			for(int col = x - distance; col <= x + distance; col++) {				// Horizontal Strips
				if(y - distance)
			}
			for(int row = y - distance + 1; row <= y + distance - 1; row++) {		// Vertical Strips
			
			}
			
			updatedValues = copy2DArray(temp);
			distance++;
		}
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public void createSpaces() {
		spaces = new ArrayList<ArrayList<Space>>();
		
		for(int h = 0; h < height; h++) {
			ArrayList<Space> row = new ArrayList<Space>();
			spaces.add(row);
			for(int w = 0; w < width; w++) {
				Space s = new Space();
				spaces.get(h).add(s);
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public int getTurn() {
		return turn;
	}
	
	public boolean validMove(int row, int col) {
		if(isOccupied(row, col)) return false;
		return true;
	}
	public boolean isOccupied(int row, int col) {
		int space = spaces.get(0).get(col).getStatus();

		if(space == STATUS_DEVIL || space == STATUS_ANGEL) return true;
		return false;
	}
	
	public int[][] copy2DArray(int[][] original) {
		int height = original.length;
		int width = original[0].length;
		int[][] duplicate = new int[height][width];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				duplicate[y][x] = original[y][x];
			}
		}
		
		return duplicate;
	}
	
	public Space getSpace(int row, int col) {
		Space s = spaces.get(row).get(col);
		return s;
	}
}