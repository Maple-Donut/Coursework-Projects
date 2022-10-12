package Coursework_1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Discount {
	static Scanner console = new Scanner(System.in);
	/** A Seat discount interface designated for admin use only
	 * this is able to change the discount rate of which is being used and
	 * applies the discount to the current values in the text file
	 */
	public static void main(String[] args) throws FileNotFoundException {
		double discount; //specified discount
		final double def = 20.0; //default discount (20%)
		double finaldiscount;
		double income;
		double used = 0;
		double totald = 0;
		double totali = 0;
		boolean error = false;
		double Taxrate = 0.2;
		String YN; // Y/N question variable
		String Stype;
		double Sprice;
		int Sbook;
		System.out.printf("       -- Seat Discount Interface --%n%n%n%nDo you want to specify a new discount value [Y/N] : "); // displays what the program is and asks a question to the user
		YN = console.next(); // user inputs if specific discount is wanted
		
		if (YN.equals("Y") || YN.equals("y") || YN.equals("yes") || YN.equals("Yes"))  { //to check if the user input is valid leaving a bit of room for error on what they enter instead of it only being Y or N
			while (!error) { // starts a while loop to ask the same question again if the user enters an invalid input
				try {
					System.out.print("What is the new discount rate (%)?: ");
					discount = console.nextDouble(); // reads the discount value that the user entered
					// if Y is inputted the user specifies a double value for the new discount
					used = discount;
					error = true; //ends the current while loop by changing the value of error
				} catch (InputMismatchException e){
					System.out.println("Error. Try again Number not entered. Please enter a Discount rate as a number only."); // displays an error message to the user to enter a different value which has to be a number only.
					console.next();
				}
			} 
			} else if (YN.equals("N") || YN.equals("n") || YN.equals("no") || YN.equals("No")) { //to check if the user input is valid leaving a bit of room for error on what they enter instead of it only being Y or N
				System.out.println("Default discount rate (20%) applied "); // if N is inputted the default of 20% discount is applied
				used = def;
			} else {
				System.out.println("Error: Wrong Format, input Y or N. Setting discount to default (20%)");
				used = def; // if the user inputs something else which isn't Y or N, an error message is displayed and the default discount is set
			}
		
		FileReader file = new FileReader("seats.txt"); // designated the variable file to seats.txt
		Scanner read = new Scanner(file); //reads the file seat.txt
		while (read.hasNext()) { // starts a while loop that reads until the end of the file "seats.txt"
			  Stype = read.next(); //reads Seat type
			  Sprice = read.nextDouble(); //reads Seat price
			  Sbook = read.nextInt(); //reads Seat bookings 
			  finaldiscount = Sbook * Sprice * (used / 100); //calculates the final discount amount
			  income = (Sprice * Sbook) - finaldiscount; // calculates the income
			  totali += income; // adds the income to the total to be displayed at the end
			  totald += finaldiscount; // adds the discount to the total to be displayed at the end
			  System.out.printf("Seat Type : %s, Seat Price : £%.2f, Bookings : %d, Discount : £%.2f, Income : £%.2f %n", Stype, Sprice, Sbook, finaldiscount, income); // displays info to the user
			}
		read.close();
		System.out.printf("Total income = £%.2f %n", totali); // prints total income
		System.out.printf("Total discount = £%.2f%n", totald); // prints total discount 
		double totaltax = calculateTax(totali, Taxrate);
		System.out.printf("Total tax payable on income is: £%.2f", totaltax);
	}
	
	/**
	 * Calculate the amount of tax payable on income.
	 *
	 * @param income    the income.
	 * @param taxRate   the tax rate, between 0.0-1.0.
	 * @return          the tax payable on the income.
	 */
	public static double calculateTax(double Income, double Taxrate) {
		return (Income * Taxrate);

	}


		
	
}
