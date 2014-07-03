package knightTravails;

import java.util.Scanner;

/**
 * This is Main class to demonstrate the execution of the program.
 * The program allow user to input start and end position once execute.
 * User must input two valid values <Start> <End>. User must input two values
 * otherwise program will print out and error message then exit.
 * If input values are invalid, program will throw an IllegalArguementException
 * and exit.
 * 
 * @author Minh Dang - July 2014
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PathFinder pathFinder = null;
		Knight knight = null;
		
		System.out.print("Enter start and goal position (i.e - A1 A8): ");
		String[] input = scan.nextLine().split(" ");
		
		if (input.length != 2) {
			System.out.println("Invalid input - require 2 values <start> <goal>");
			System.exit(1);
		} else {
			try {
				String start = input[0].toUpperCase();
				String goal = input[1].toUpperCase();
			
				pathFinder = new PathFinder(start, goal);
				pathFinder.buildPaths();
				knight = pathFinder.getKnight();
				knight.outputPath();
				System.out.println("Program Exiting");
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		scan.close();
		System.exit(0);
	}
}
