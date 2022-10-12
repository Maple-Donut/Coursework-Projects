package wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Decode {
	
	public static void decrypt() throws FileNotFoundException {
		ArrayList<String> word_arr = new ArrayList<String>();
		
		File inputFile = new File("english3.txt");
		Scanner Reader = new Scanner(inputFile);
		int x = 0;
		System.out.println("Reading...");
		while (Reader.hasNextLine()) {
			word_arr.add(x,Reader.nextLine());
			x++;
			System.out.println(x);
		}
		Reader.close();
		System.out.println("Subtracting...");
		System.out.println(word_arr.size());
		for(int i = 0; i < word_arr.size(); i++) {
			if (word_arr.get(i).length() != 5) {
				word_arr.remove(i);
				i--;
			}
		}
		System.out.println(word_arr.size());
		PrintWriter write = new PrintWriter("english4.txt");
		System.out.println("Writing...");
		for (int i = 0; i < word_arr.size(); i++ ) {
			if (!(word_arr.get(i).equals(" "))) {
				write.println(word_arr.get(i));
				System.out.println(i);
				
			}
		}
		write.close();
	
	}
	

	
}
