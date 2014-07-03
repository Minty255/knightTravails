package knightTravails;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the base class for chess pieces. New chess piece class can
 * inherit from this class with most the functionality it needed. The only thing
 * base class need to implement is the method to calculate their possible moves
 * base on their own move pattern.
 * @author Minh
 *
 */
public abstract class ChessPiece {
	/**
	 * START 	- String indicate starting position
	 * GOAL  	- String indicate the goal
	 * m_solPath - List of solution path to go from start to goal position
	 */
	public final String START;
	public final String GOAL;
	
	private List<List<String>> m_solPath;
	
	// Constructor
	public ChessPiece(String start, String goal) {
		isValidInput(start, goal);
		
		this.START = start;
		this.GOAL = goal;
		this.m_solPath = new LinkedList<List<String>>();
	}

	// Getter to retrieve the solution list
	public List<List<String>> getSolPath() {
		return this.m_solPath;
	}
	
	// Function to add a path to the solution list
	public void addSolPath(List<String> solPath) {
		this.m_solPath.add(solPath);
	}

	/**
	 * Function that output the possible path for the given start and goal
	 * position. Print out path from start to goal position minus the start position.
	 * There is a feature in which if there are more than one possible solution user 
	 * can decide to output more.
	 * Example output: B6 D7 C5 B7
	 */
	public void outputPath() {
		Scanner scan = new Scanner(System.in);
		boolean choice = false;
		int count = m_solPath.size();
		
		if (count == 0)
			System.out.println("Currently there are no solution");
		else {
			System.out.println("Below are the possible solution path");
			
			do {
				count--;
				List<String> temp = m_solPath.get(count);
				for (int ii = 1; ii < temp.size(); ii++) 
					System.out.print(temp.get(ii) + " ");
			
				System.out.println();
				if (count > 0) {
					System.out.print("There are more solution, would you like to display it? (y/n) ");
					choice = isValidChoice(scan.nextLine());
				}
			} while (count > 0 && choice);

			
		/*	// Iterate through the list and output the solution path
			List<String>temp = m_solPath.get(count-1);
			for (int ii = 1; ii < temp.size(); ii++)
				System.out.print(temp.get(ii) + " ");;
			
			System.out.println();
		*/
			scan.close();
		}
	}
	
	/**
	 * Function use for validating user input to make sure that it's only yes, no, y or n.
	 * @param choice - User input
	 * @return - boolean indicate if yes(true) or no (false) 
	 */
	private boolean isValidChoice(String choice) {
		String temp = choice.toUpperCase();

		// Make sure only y, yes, n or no - otherwise ask the user to re-input
		while (!(temp.equals("Y") || temp.equals("YES") || temp.equals("N") || temp.equals("NO"))) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Invalid choice - please put 'yes' or 'no': ");
			temp = scan.nextLine();
			temp = choice.toUpperCase();
			scan.close();
		}

		if (temp.equals("Y") || temp.equals("YES")) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Function that validate user input for start and goal position.
	 * First check the length of inputs - must be length of 2.
	 * Then call validatePos() to check for actual input value.
	 * Also check that the start and goal value isn't the same otherwise
	 * throw an exception.
	 * 
	 * @param start - String: import value for starting position
	 * @param goal - String: import value for goal position
	 * 
	 * Throw IllegalArgumentException if the import value is invalid
	 */
	private void isValidInput(String start, String goal) {
		boolean checkLen = start.length() == 2 && goal.length() == 2;
		boolean checkVal = isValidPos(start) && isValidPos(goal);
		
		if (!(checkLen && checkVal))
			throw new IllegalArgumentException("Invalid input, positions must be on valid on chessboard");
		else if(start.equals(goal))
			throw new IllegalArgumentException("Already at the goal location");
	}
	
	/**
	 * Function that use to validate the value of input.
	 * Letter must be from A-H and number from 1-8
	 * 
	 * @param pos - String: imported position input
	 * @return - boolean: indicate if value is valid or not
	 */
	protected boolean isValidPos(String pos) {
		return (pos.charAt(0) >= 'A' && pos.charAt(0) <= 'H') &&
				(pos.charAt(1) >= '1' && pos.charAt(1) <= '8');
	}
	
	/**
	 *  Abstract method so that inherit class can implement their own method of calculating
	 *  their moves depending on their move patterns
	 */
	public abstract List<String> getMoves(String position);
}
