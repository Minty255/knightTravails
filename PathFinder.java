package knightTravails;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class is in charge of finding the shortest (in term of moves)
 * possible path(s) from start to goal position as indicated by the user.
 * The solution is stored inside the knight object's member variable.
 * @author Minh Dang - July 2014
 *
 */

public class PathFinder {
	/***
	 * m_found		- boolean indicate if a solution has been found or not
	 * m_path		- Path object that act as an expanding graph of path
	 * m_knight		- An object of chess piece, this can be change to find path
	 * 					for different pieces
	 */
	private boolean m_found;
	private Path<String> m_path;
	private Knight m_knight;
	
	/**
	 * Constructor
	 * First validate the imported value before setting the state of object.
	 * @param start
	 * @param goal
	 */
	public PathFinder(String start, String goal) {
		this.m_knight = new Knight(start, goal);
		this.m_found = false;
		this.m_path = new Path<String>(start);
		
	}
	
	/**
	 * This function build the path tree from start to goal position.
	 * It will stop after a path to the goal position is found.
	 * But it does not stop immediately, it only stop after all the list on
	 * the same depth finished processing. This to guarantee to find all possible
	 * paths instead of stopping immediately.
	 */
	public void buildPaths() {
		// Create two queue for processing
		LinkedList<List<Path<String>>> queue = new LinkedList<List<Path<String>>>();
		LinkedList<List<Path<String>>> newQueue = null;
		
		// First expand the start position and add it possible to the queue for processing
		expandBranch(this.m_path);
		queue.addFirst(this.m_path.getPath());
		
		// This will continue running until atleast one solution path is found
		while (!this.m_found) {
			newQueue = new LinkedList<List<Path<String>>>();
			// Iterate through the queue and process each list of path
			while (queue.size() != 0) {
				List<Path<String>> temp = queue.removeLast();
				
				for (int ii = 0; ii < temp.size(); ii++) {
					expandBranch(temp.get(ii));
					newQueue.addFirst(temp.get(ii).getPath());
				}
			}
			
			// Assign the new set of paths to the queue for processing
			queue = newQueue;
			newQueue = null;
		}
	}
	
	/**
	 * This function in charge of taking a section within the whole path and expand
	 * that out with valid and unexplored values on that branch.
	 * @param currPath - Path object: sub section of the whole path that requires
	 * 						to be expanded.
	 */
	private void expandBranch(Path<String> currPath) {
		List<String> moves = m_knight.getMoves(currPath.getValue());

		for (int ii = 0; ii < moves.size(); ii++) {
			String temp = moves.get(ii);

			// Validate to make sure only add valid values and have yet explored
			if (!isExplored(temp, currPath.getExploredPath())) {
				Path<String> newPath = new Path<String>(temp, currPath.getExploredPath());
				currPath.addPath(newPath);
				
				// Add the path to the solution list if a goal is found and set found to true
				if (temp.equals(m_knight.GOAL)) {
					this.m_found = true;
					this.m_knight.addSolPath(new ArrayList<String>(newPath.getExploredPath()));
				}
			}
		}
	}
	
	/**
	 * Function that is use to check if a position has been explored or not.
	 * This is to save memory so that we don't store already visited position.
	 * Done by iterate through the list and check if it exist already
	 * 
	 * @param pos - String: position of the piece
	 * @param exploredList - List: contain a list of explored path so far to that position
	 * @return - boolean: to indicate if the position has been explored before
	 */
	private boolean isExplored(String pos, List<String> exploredList) {
		boolean explored = false;
		ListIterator<String> iterator = exploredList.listIterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(pos))
				explored = true;
		}
		
		return explored;
	}
	
	// Getters
	public Knight getKnight() {
		return this.m_knight;
	}
}