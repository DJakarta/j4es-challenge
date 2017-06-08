package io.github.djakarta.diff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;


/**
 * Read the integer M then all the integers from a file ("numbers.in") and display the pairs of positions in
 * the array for which the corresponding integers' difference is M. Optionally, write random numbers in
 * the file for testing.
 * @author DJakarta
 */
public class Main {
	/**
	 * Generate file for testing with the difference parameter M and n random integers.
	 * @param diff difference parameter, the difference for the pair to be considered valid
	 * @param n number of integers to be generated
	 */
	public static void generateInts(int diff, int n) {
		int i;
		try {
			FileWriter out = new FileWriter("numbers.in");
			Random generator = new Random();
			
			out.write(String.valueOf(diff) + '\n');
			for (i = 0; i < n; i++) {
				out.write(String.valueOf(generator.nextInt((n * n) / 5)) + '\n');
			}
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the files and display the pairs in the output PrintStream.
	 * @param out the print stream to be used to display the pairs in
	 */
	public static void displayPairs(PrintStream out) {
		//use a hash table for fast insertion and search 
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		
		//read integers
		try {
			Scanner input = new Scanner(new File("numbers.in"));
			int diff, no, i = 0;
			
			//read the difference M
			diff = input.nextInt();
			
			//read the file
			while(input.hasNext()) {
				no = input.nextInt();
				
				//if the read integer was not read before, store its position in the array
				if (!table.containsKey(no)) {
					table.put(no, i);
					
					//if the number no - M was read, display the pair
					if (table.get(no - diff) != null) {
						out.println("(" + i + ", " + table.get(no - diff) + ")");
					}
					//if the number no + M was read, display the pair
					if (table.get(no + diff) != null) {
						out.println("(" + table.get(no + diff) + ", " + i + ")");
					}
				}
				i++;
			}
			input.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Main program logic.
	 * @param args command line arguments
	 */
	public static void main(String args[]) {
		//set the generate flag to true to generate n random integers to test the program with
		//set the M parameter for the pairs to be written in the file
		int n = 10, M = 1;
		boolean generate = false;
		
		//generate random integers
		if (generate) {
			Main.generateInts(M, n);
		}
		
		//display the pairs found in the system output stream or, optionally, a file or any OutputStream
		Main.displayPairs(System.out);
	}
}
