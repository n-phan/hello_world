package board;

import java.awt.Color;

import processing.core.PApplet;

/**
 * Display object that holds information regarding the Board
 * @author Nathan Phan
 * @version 10/24/2018
 */
public class Display {
	public static final Color COLOR_EMPTY = Color.WHITE;
	public static final Color COLOR_ANGEL = Color.GREEN;
	public static final Color COLOR_DEVIL = Color.RED;
	public static final Color COLOR_POSSIBLE_ANGEL_MOVE = Color.YELLOW;
	public static final Color COLOR_UNKNOWN = Color.BLACK;
	
	private PApplet p;		
	private int x, y, w, h;	// (x, y) of upper left corner of display, width and height of the display
	private float dx, dy; 	// calculate the width and height of each box in the display
	private int rows, cols;
		
	/**
	 * Constructs a Display object given some variables
	 * @param p the PApplet object that the this Display will be working on 
	 * @param x the leftmost point of this Display object
	 * @param y the up-most point of this Display object
	 * @param w the width of this Display object
	 * @param h the height of this Display object
	 */
	public Display(PApplet p, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.p = p;
	}

	/**
	 * Illustrates a given Board object onto this Display
	 * @param a the Board object that will be illustrated onto this Display
	 */
	public void drawBoard(Angel a) {		
		for(int row = 0; row < a.getHeight(); row++) {
			for(int col = 0; col < a.getWidth(); col++) {	
				// Paints the square of the board at a specific row and column
				fill(a.getSpace(row, col));	// Identifies the correct color to use
				p.rect(x+ row*dx, y+ col*dy, dx, dy);

				// Prints the value of the board at a specific row and column
				fill(-1);					// Changes color back to black for text
				p.text(a.getValue(row, col), (float)(x+ row*dx + 2), (float)(1.5*y+ col*dy));
			}
		}
	}
	
	/**
	 * Given an 'identifier' for a space on the Board, returns the appropriate color
	 * @param space a representation of what is on the Board at a specific location
	 */
	public void fill(int space) {
		Color color;
		if(space == Angel.SPACE_EMPTY) color = COLOR_EMPTY; 
		else if(space == Angel.SPACE_ANGEL) color = COLOR_ANGEL; 
		else if(space == Angel.SPACE_DEVIL) color = COLOR_DEVIL; 
		else if(space == Angel.SPACE_POSSIBLE_ANGEL_MOVE) color = COLOR_POSSIBLE_ANGEL_MOVE;
		else color = COLOR_UNKNOWN;
		
		p.fill(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	/**
	 * Sets the number of rows that the Display will have
	 * Also updates the height of each box accordingly to the new number of rows
	 * @param num_rows the number of rows that the Display will have
	 */
	public void setNumRows(int num_rows) {
		rows = num_rows;
		dy = h / rows;
	}
	/**
	 * Sets the number of columns that the Display will have
	 * Also updates the width of each box accordingly to the new number of columns
	 * @param num_cols the number of rows that the Display will have
	 */
	public void setNumCols(int num_cols) {
		cols = num_cols;
		dx = w / cols;
	}
	
	/**
	 * Finds the row and column of the board given the location of a mouse click
	 * @param x the x-coordinate of the mouse click with regards to the GUI
	 * @param y the y-coordinate of the mouse click with regards to the GUI
	 * @param gapSize the space between the GUI and Display
	 * @return coordinates the (x, y) coordinates of the Board
	 */
	public int[] getSpace(int x, int y, int gapSize) {
		int[] coordinates = new int[] {(int)((x-gapSize) / dx), (int)((y-gapSize) / dy)}; 
		return coordinates;
	}
}
