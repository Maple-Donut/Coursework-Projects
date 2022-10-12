package till;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Till {

	static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.printf("-----Menu-----%n1: Start Day%n2: Access Log%n3: Exit%n : ");
		int choice = console.nextInt();
		
		switch (choice) {
		case 1:
			startday();
			break;
		case 2:
			accesslog();
			break;
		case 3:
			break;	
		}

	}
	
	public static void startday() throws FileNotFoundException {
		System.out.printf("Please enter the date (dd_mm_yy)%n  : ");
		String date = console.next();
		Day.mainmenu(date);
		
	}
	
	public static void accesslog() throws FileNotFoundException {
		System.out.print("Please enter the date of the log (dd_mm_yy): ");
		String date = console.next();
		File inputFile = new File(date + ".txt");
		Scanner Reader = new Scanner(inputFile);
		while (Reader.hasNextLine()) {
			System.out.println(Reader.nextLine());
		}
		Reader.close();
	}

}
