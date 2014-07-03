package knightTravails;

import java.util.ArrayList;
import java.util.List;

/***
 * This class represent the knight piece. Holding information
 * about it moving pattern and calculating it possible move
 * given a position on the board.
 * @author Minh - July 2014
 *
 */

public class Knight extends ChessPiece {
	/**
	 * MOVESET - 2D int array that indicate the knight move pattern. Use for calculating
	 * 				new positions.
	 */
	private final int[][] MOVESET = {{2,1}, {2,-1}, {-2,1}, {-2,-1},
			 						 {1,2}, {1,-2}, {-1,2}, {-1,-2}};
	
	// Constructor
	public Knight(String start, String goal) {
		super(start, goal);
	}
	
	/**
	 * Function to generate a list of position that the knight can move
	 * using it moving pattern.
	 * 
	 * @position - String indicate current position on the board
	 * @return - List of all the move generated
	 */
	@Override
	public List<String> getMoves(String position) {
		List<String> moveList = new ArrayList<String>();
		char[] oldVal = position.toCharArray();
		char[] newVal = new char[2];
		
		// Iterate through and generate new position
		for (int ii = 0; ii < MOVESET.length; ii++) {
			newVal[0] = (char)(oldVal[0] + MOVESET[ii][0]);
			newVal[1] = (char)(oldVal[1] + MOVESET[ii][1]);
			
			// Check if it is a valid position before add to the move list
			String temp = String.valueOf(newVal);
			if (isValidPos(temp))
				moveList.add(new String(String.valueOf(newVal)));
		}
		
		return moveList;
	}
}
