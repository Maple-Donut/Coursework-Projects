package coursework_2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SeatBooking {
	static Scanner console = new Scanner(System.in);
	private String seatNum;
	private String seatClass;
	private boolean isWindow;
	private boolean isAisle;
	private boolean isTable;
	private double seatPrice;
	private String eMail;
	
	/* This is a constructor used to form an objects throughout the code used in
	 * an object array called SeatInfo and an object called Seat to read the text from the
	 * text file "seats.txt" and put the information in the correct form for the array SeatInfo.
	 */
	public SeatBooking(String seatNum, String seatClass, boolean isWindow, boolean isAisle, boolean isTable, double seatPrice, String eMail) {
		this.seatNum = seatNum;
		this.seatClass = seatClass;
		this.isWindow = isWindow;
		this.isAisle = isAisle;
		this.isTable = isTable;
		this.seatPrice = seatPrice;
		this.eMail = eMail;
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		menu();

	}
	
	/* This menu is the looping main menu which it takes the user back to
	 * at the end of each finished interaction, it lets the book, cancel, look at the current seating reservations
	 * and quit from the menu.
	 * It can also accept invalid inputs and show the users what inputs are valid.
	 */
	public static void menu() throws FileNotFoundException {
		char choice;
		boolean end;
		do {
			end = true;
			System.out.printf("- - Seat Booking System - -%n- - MAIN MENU - -%n1 - Reserve Seat%n2 - Cancel Seat%n3 - View Seat Reservations%nQ - Quit%nPick : ");
			choice = console.next().charAt(0);
			
			if (choice == '1') {
				reserve();
			} else if (choice == '2') {
				cancel();
			} else if (choice == '3') {
				view();
			} else if (choice == 'Q') {
				end = false; // ends looping
				System.out.print("Exiting...");
			} else {
				System.out.println("Error. Not a selected option. Please try again and enter a selected option.");
				
			}
		} while (end == true );
			
	}
	
	public static void reserve() throws FileNotFoundException {
		int[] answer = new int[4];
		String eMail = "";
		int[] pointArray = new int[18];
		boolean error = false;
		SeatBooking[] SeatInfo = new SeatBooking[18];

		while (!error) {
			try {
				System.out.println("Please answer the following questions.");
				System.out.printf("Would you like:%nStandard [1]%nFirst Class [2]%nPick:  ");
				answer[0] = console.nextInt(); // stores the answer in the first slot of an answers array
				if (!(answer[0] == 1 ^ answer[0] == 2)) { // if the answer is not 1 or 2 the loop will be restarted and the user will have to re-enter their answers
					System.out.println("Please only select 1 or 2");
					continue;
				}
				System.out.printf("Would you like a window seat:%nYes [1]%nNo [2]%nPick: ");
				answer[1] = console.nextInt(); // refer to line 80 comments
				if (!(answer[1] == 1 ^ answer[1] == 2)) { // refer to line 81 comments
					System.out.println("Please only select 1 or 2"); 
					continue;
				}
				System.out.printf("Would you like an aisle seat:%nYes [1]%nNo [2]%nPick: ");
				answer[2] = console.nextInt(); // refer to line 80 comments
				if (!(answer[2] == 1 ^ answer[2] == 2)) { // refer to line 81 comments
					System.out.println("Please only select 1 or 2"); 
					continue;
				}
				System.out.printf("Would you like a table seat:%nYes [1]%nNo [2]%nPick: ");
				answer[3] = console.nextInt(); // refer to line 80 comments
				if (!(answer[3] == 1 ^ answer[3] == 2)) { // refer to line 81 comments
					System.out.println("Please only select 1 or 2"); 
					continue;
				}
				error = true;
			} catch (InputMismatchException e) {
				System.out.println("Error please input the answer in the correct format");
				console.next();
				/* if the users enters an input in the wrong format the exception will be caught
				 * here and an error message will be displayed. It will then go to the beginning of the questions and
				 * start the questions again until all questions have been answered correctly.
				 */
			}			
		}
	
		error = false;
		while (!error) {
			System.out.print("Finally, please input your email address: ");
			eMail = console.next();
			if (!(eMail.contains("@")) || eMail.contains("..") || !(eMail.contains(".")) || eMail.length() < 5 ) { 
				System.out.println("Please input the email in the correct format");
			} else {
				error = true;
			}
			/* This while loops checks the format of the email and will reject emails with the incorrect formatting 
			 * however this can only work to an extent and even though it will prevent very basic incorrect emails it will
			 * not be able to prevent something such as "@test.notadomain.dot".
			 */
		}
		read(eMail, answer, pointArray, SeatInfo); //jumps to the subroutine "read"
		
		int recommended = 0;
		int recSeat = 0; 
		String decision;
		for (int i = 0; i < pointArray.length; i++) {
			if (pointArray[i] > recommended) { // 
				recommended = pointArray[i];
				recSeat = i;
				/*  the recommended seat starts at 0, if the amount of points of the current seat is higher than  
				 *  the current recommended, the current seat will become the recommended one until the loop finishes.
				 */
			}
		}
		error = false;
		while (!error) {
			System.out.printf("The recommended seat available is seat number %s at £%.2f.%nWould you like to book this seat?: ", SeatInfo[recSeat].seatNum, SeatInfo[recSeat].seatPrice); //displays the recommended seat and the price of that seat.
			decision = console.next();
			decision.toLowerCase(); // changes the answer to lower case so easier to validate
			if (decision.equals("y") || (decision.equals("yes"))) { // if the user enters y or yes the program will continue
				SeatInfo[recSeat].eMail = eMail; // fills up the current "free" seat with their email.
				error = true; // stops looping
				write(SeatInfo); // jumps to the subroutine "write"
				System.out.println("Your seat has been booked.");
			} else if (decision.equals("n") || decision.equals("no")) { // if the user enters n or no the program will stop and loop back to the main menu
				error = true; // stops looping
				System.out.println("Booking cancelled."); // back to main menu
			} else {
				System.out.println("Please input 'yes' or 'no'. "); // loops back to question.
			}
		}
		

	}
	
	public static void cancel() throws FileNotFoundException {
		System.out.print("Please enter your seat number of the booking you wish to cancel: ");
		String seatSelected = console.next(); // makes the user enter the selected seat
		FileReader file = new FileReader("seats.txt");
		Scanner read = new Scanner(file);
		int count = 0;
		SeatBooking[] SeatInfo = new SeatBooking[18]; 
		while (read.hasNext()) {
			SeatBooking Seat = new SeatBooking(read.next(), read.next(), read.nextBoolean(), read.nextBoolean(), read.nextBoolean(), read.nextDouble(), read.next());
			SeatInfo[count] = Seat; // reads Seat object into the SeatInfo array
			count += 1;
		}
		read.close();
		System.out.print("Please enter your email: ");
		String eMail = console.next();
		boolean cancelled = false;
		for (int i = 0; i < SeatInfo.length; i++) { //check every seat in array
			if (seatSelected.equals(SeatInfo[i].seatNum)) { // if the seat is the seat they entered
				if (eMail.equals(SeatInfo[i].eMail)) { // if the emails match
					SeatInfo[i].eMail = "free"; //changed array so it says free instead of their email
					write(SeatInfo); // writes the new array to the file
					System.out.println("Booking cancellation complete.");
					cancelled = true;
				} else {
					System.out.println("Sorry that is the wrong email. Booking Cancellation denied."); // seat Exists but wrong email
					cancelled = true;
				}
			} else if (i == 17 && cancelled == false) { // seat doesn't exist
				console.nextLine();
				System.out.printf("Sorry. Invalid Seat Number inputted.%nPlease refer to the 'View Seat Reservations' Option on the main menu for the correct Seat Numbers.%nPress [ENTER] to return to the main menu.");
				seatSelected = console.nextLine();
				
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void view() throws FileNotFoundException {
		FileReader file = new FileReader("seats.txt");
		Scanner PrintReader = new Scanner(file);
		while (PrintReader.hasNextLine()) {
			String text = PrintReader.nextLine(); // reads each line
			System.out.println(text); // prints each line
		}
		PrintReader.close();
		console.nextLine();
		System.out.println("Press [ENTER] to go back to the main menu.");
		String back = console.nextLine(); // only used so the program pauses so the user can view the booking
	}
	
	public static void read(String email, int [] answer, int[] pointArray, SeatBooking[] SeatInfo) throws FileNotFoundException {
		FileReader file = new FileReader("seats.txt"); // designated the variable file to seats.txt
		Scanner read = new Scanner(file); //reads the file seat.txt
		int points = 0;
		int count = 0;
		while (read.hasNext()) {
			SeatBooking Seat = new SeatBooking(read.next(), read.next(), read.nextBoolean(), read.nextBoolean(), read.nextBoolean(), read.nextDouble(), read.next()); // forms an object of a seat in the file seats.txt sequentially
			points = 0; // resets points to 0 after every loop so points dont stack as more seats get ranked in favourability.

			if ((Seat.seatClass.equals("STD") && answer[0] == 1) || (Seat.seatClass.equals("1ST") && answer[0] == 2)) {
				points += 5; // this will check if the seat type matches the users answers
			}	// if so a point will be added to weigh the favourability of the seat
			if ((Seat.isWindow == true && answer[1] == 1) || (Seat.isWindow == false && answer[1] == 2)) {
				points += 1; // this will check if the seat is near a window and if it matches the answer of the user. 
			}   // if so a point will be added to weigh the favourability of the seat
			if ((Seat.isAisle == true && answer[1] == 1) || (Seat.isAisle == false && answer[1] == 2)) {
				points += 1; // this will check if the seat is near an aisle and if it matches the answer of the user. 
			}	// if so a point will be added to weigh the favourability of the seat
			if ((Seat.isTable == true && answer[1] == 1) || (Seat.isTable == false && answer[1] == 2)) {
				points += 1; // this will check if the seat is a table seat and if it matches the answer of the user. 
			}	// if so a point will be added to weigh the favourability of the seat
			if (!(Seat.eMail.equals("free"))) {
				points -= 100; // if the seat has already been booked, it will become the least favourable seat and not be able to be booked.
			}
			SeatInfo[count] = Seat; //stores the current seat information in the object array seat info
			pointArray[count] = points; //stores the amount of points of the seat in the point array to keep track of the amount of points
			count += 1;
		}
		read.close();
	}
	
	public static void write(SeatBooking[] SeatInfo) throws FileNotFoundException {
		PrintWriter write = new PrintWriter("seats.txt");
		for (int i = 0; i < SeatInfo.length; i++) {
			write.println(SeatInfo[i].seatNum + " " + SeatInfo[i].seatClass + " " + SeatInfo[i].isWindow + " " + SeatInfo[i].isAisle + " " + SeatInfo[i].isTable + " " + SeatInfo[i].seatPrice + " " + SeatInfo[i].eMail);	
		}
		write.close();
		/* writes over the current file with the SeatInfo array that may have been changed to cancel or book a seat
		 * in the correct format so the file is usable again for the next booking or cancellation.
		 */
	}
	
	}


