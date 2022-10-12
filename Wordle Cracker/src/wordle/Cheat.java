package wordle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
	

public class Cheat {
	static Scanner console = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		//Decode.decrypt();
		//System.out.print("Done");
		boolean end = false;
		ArrayList<String> char_arr = new ArrayList<String>();
		String[] pos_arr = new String[5];
		pos_arr[0] = "-";
		pos_arr[1] = "-";
		pos_arr[2] = "-";
		pos_arr[3] = "-";
		pos_arr[4] = "-";
		
		do {
			System.out.printf("Choose an option:%nadd a letter [1]%ngive a postion [2]%n remove a letter [3]%nDisplay [4]%nEnd [5]");
			int choice = console.nextInt();
			
			switch (choice) {
			case 1:
				add(char_arr);
				break;
			case 2:
				pos(char_arr, pos_arr);
				break;
			case 3:
				sub(char_arr, pos_arr);
				break;
			case 4:
				display(char_arr, pos_arr);
				break;
			case 5:
				end = true;
				break;
			}
		
		} while(!end);
	}
	
	public static void add(ArrayList<String> char_arr) throws FileNotFoundException {
		System.out.println("What letter would you like to add");
		String ltr = console.next();
		if (char_arr.size() <= 5) {
			char_arr.add(ltr.toLowerCase());
		} else {
			System.out.println("Max Number of Letters!");
		}
		
	}
	
	public static void pos(ArrayList<String> char_arr, String[] pos_arr)  throws FileNotFoundException {
		System.out.println("What letter would you like to assign a position:");
		for (int i = 0; i < char_arr.size(); i++) {
			System.out.print(char_arr.get(i) + " ");
		}
		System.out.println(" :");
		String ltr = console.next();
		System.out.print("Please enter the position of the letter: ");
		int pos = console.nextInt();
		pos_arr[pos] = ltr.toLowerCase();
		System.out.println("Position complete.");
		
	}
	
	public static void sub(ArrayList<String> char_arr, String[] pos_arr) throws FileNotFoundException {
		System.out.println("Have you assigned a position to the letter? [y/n]");
		char choice = console.next().charAt(0);
		System.out.println("What is the letter you would like to remove?: ");
		String ltr = console.next();
		if (choice == 'y') {
			System.out.println("what was the position of the letter");
			int pos = console.nextInt();
			if (pos_arr[pos].equals(ltr.toLowerCase())) {
				pos_arr[pos] = "-";
			} else {
				System.out.println("Error pos incorrect or letter incorrect");
			}
		} else if (choice == 'n') {
			for (int i = 0; i < char_arr.size(); i++) {
				if (char_arr.get(i).equals(ltr)) {
					char_arr.remove(i);
					System.out.println("Letter Removed.");
				}
			}
		}
			
	}
	
	public static void display(ArrayList<String> char_arr, String[] pos_arr) throws FileNotFoundException {
		ArrayList<String> word_arr = new ArrayList<String>();
		File inputFile = new File("english4.txt");
		Scanner Reader = new Scanner(inputFile);
		int x = 0;
		System.out.println("Reading...");
		while (Reader.hasNextLine()) {
			word_arr.add(x,Reader.nextLine());
			x++;
			System.out.println(x);
		}
		Reader.close();
		
		boolean keep = false;
		for (int i = 0; i < word_arr.size(); i++) {
			for(int g = 0; g < char_arr.size(); g++) {
				if (word_arr.get(i).contains(char_arr.get(g))) {
					keep = true;
					System.out.println("Word found");
				}
			}
			if (keep) {
				keep = false;
				
			} else
				word_arr.remove(i);
		}
		keep = false;
		for (int i = 0; i < word_arr.size(); i++) {
			for (int g = 0; g < 5; i++) {
				if (!(pos_arr[g].equals("-"))) {
			//	for (int h = 0; )// && (word_arr.get(i).substring(g-1,g)).equals(pos_arr[g])) {
			}
//					keep = true;
//					System.out.println("Word found 2");
//				}
			}

		}
		
	}
	
	

}
