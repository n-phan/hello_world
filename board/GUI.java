package board;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import processing.core.PApplet;

/**
 * Graphics User Interface object that represents the Angel Problem
 * @author Nathan Phan
 * @version 10/24/2018
 */
public class GUI extends PApplet{
	private int height, width, numRows, numCols, gapSize;
	private Display display;
	private Board board;
	
	/**
	 * Initializes the interface with a given height, width, number of rows and columns, 
	 * and a gap size for the borders of the Java PApplet
	 */
	public void setup() {
		height = 1000;
		width = (int)(1.25*height);
		numRows = 25;
		numCols = numRows;
		gapSize = 20;
	}
	
	
	/**
	 * Draws the first instance of the GUI given the initial setup conditions
	 */
	public void draw() {
	}
	
	/**
	 * Updates the GUI given the location of the mouse with regards to the GUI
	 */
	public void mouseReleased(MouseEvent e) {

	}
	/**
	 * Updates the GUI with regards to a specific key
	 */
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			
	}
}
