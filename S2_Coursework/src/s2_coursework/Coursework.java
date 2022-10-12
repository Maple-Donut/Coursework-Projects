package s2_coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Coursework {
	static Scanner console = new Scanner(System.in);
	
//	public static void main(String[] args) throws FileNotFoundException {
//		System.out.println("test");
//	}
//	

	public static void main(String[] args) throws FileNotFoundException {
		boolean stop = false;
		boolean file_select = false;
		boolean error = true;
		String file_name = "";
		ArrayList<String> file_arr = new ArrayList<String>();
		
		while(error) {
			/*
			 * this piece of code is to read the file chosen and store it in an
			 * array first before it is passed to other classes.
			 * This is so this same peice of code does not have to be repeat several times within different
			 * classes and subroutines.
			 */
			try {
				while(!stop) {
					if (file_select) {
						file_arr.clear();
						File inputFile = new File(file_name);
						Scanner Reader = new Scanner(inputFile);
						while (Reader.hasNextLine()) {
							file_arr.add(Reader.nextLine());
						}
						Reader.close();
					}

			
					//main menu
					error = true;
					System.out.printf("--- Encryption Program ---%n Please select an option%n(note: you have to select a file before trying to edit a file.)");
					System.out.printf("%n1. Encryption%n2. Decryption%n3. Metadata%n4. File Select%n5. Quit%n(Please select 1, 2, 3, 4 or 5.)%n%n--: ");
					int option = console.nextInt();
					if ((option != 4 && option != 5) && file_select == false) {
						System.out.printf("Please select a file before selecting that option.%n");
					} else {
						switch(option) {
						case 1:
							//encryption
							Encryption.Enc_Menu(file_arr);
							error = false;
							break;
						case 2:
							//decryption
							Decryption.Dec_Menu(file_arr);	
							error = false;
							break;
						case 3:
							//metadata
							Metadata.meta_menu(file_name);;
							error = false;
							break;
						case 4:
							//file
							error = false;
							file_name = file_read(file_name);
							file_select = true;
							break;
						case 5:
							//Quit
							error = false;
							stop = true;
							break;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Please select the given options.");
				console.nextLine();
			}
			
		}
		
	}


	
	
	
	public static String file_read(String file_name) throws FileNotFoundException {
		boolean error = true;
		while(error) {
			try {
				System.out.print("Please enter a file name: ");
				file_name = console.next();
				FileReader file = new FileReader(file_name); 
				Scanner read = new Scanner(file);
				read.close();
				System.out.println("file selected.");
				System.out.println("");
				error = false;
			} catch (Exception e) {
				/*
				 * if the file does not exist when the user enters the filename
				 * the user will then be prompted to enter it again until an exiting filename is selected
				 */
				System.out.println("File does not exist. Please try again.");
			}

		}
		return file_name;
		
		
	}


}
