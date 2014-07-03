package knightTravails;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic container class that provide data structure that get uses by
 * PathFinder class to store data and build the solution upon.
 * 
 * @author Minh - July 2014
 *
 */
public class Path<E> {
	/**
	 * m_exploredPath 	- List: contain the explored path to the current state
	 * m_path			- List of path: store the next position path to explore
	 * m_value			- Position of the current object
	 */
	private List<E> m_exploredPath;
	private List<Path<E>> m_path;
	private E m_value;
	
	// Constructor
	public Path(E value) {
		this.m_value = value;
		this.m_path = new ArrayList<Path<E>>();
		this.m_exploredPath = new ArrayList<E>();
		this.m_exploredPath.add(value);
	}
	
	// Alternate Constructor
	public Path(E value, List<E> explored) {
		this.m_value = value;
		this.m_path = new ArrayList<Path<E>>();
		this.m_exploredPath = new ArrayList<>(explored);
		this.m_exploredPath.add(value);
	}
	
	// Getters
	public List<Path<E>> getPath() {
		return this.m_path;
	}
	
	public E getValue() {
		return this.m_value;
	}
	
	public List<E> getExploredPath() {
		return this.m_exploredPath;
	}
	
	// Setters
	public void setPath(List<Path<E>> path) {
		this.m_path = path;
	}
	
	public void setValue(E value) {
		this.m_value = value;
	}
	
	public void setExploredPath(List<E> explored) {
		this.m_exploredPath = explored;
	}
	
	// Other Methods
	public void addPath(Path<E> path) {
		this.m_path.add(path);
	}
}