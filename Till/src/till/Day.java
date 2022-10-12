package till;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Day {
	static int amount = 50;
	static Scanner console = new Scanner(System.in);
	static String[][] Orders = new String[amount][amount];
	static int x = 0;
	static int[] y = new int[amount];
	static double[] total = new double[amount];
	
	
	
	public static void mainmenu(String date) throws FileNotFoundException {
	boolean stop = false;	
		
		while(!stop) {
			try {
			if (y[x] == 0) {
				System.out.printf("-----Order %d-----[NEW ORDER]%n1: Start New Order%n2: drink%n3: snacks%n4: meal %n5: custom %n6: see orders %n7: change%n8: save%n9: exit%n : ", x);
			} else {
				System.out.printf("-----Order %d-----%n1: Start New Order%n2: drink%n3: snacks%n4: meal %n5: custom %n6: see orders %n7: change%n8: save%n9: exit%n : ", x);
			}
			int choice = console.nextInt();
			
			switch (choice) {
			case 1:
				neworder();
				break;
			case 2:
				drinks();
				break;
			case 3:
				food();
				break;	
			case 4:
				meal();
				break;	
			case 5:
				custom();
				break;
			case 6:
				seeorders();
				break;
			case 7:
				changeorders();
				break;
			case 8:
				save(date);
				break;
			case 9:
				stop = true;
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
			console.next();
		}
		}
	}


	@SuppressWarnings("resource")
	private static void save(String date) throws FileNotFoundException {
		PrintWriter write = new PrintWriter(date + ".txt");
		for(x = 0; x < amount-1; x++) {
			if (Orders[x][0] == null) {
			} else {	
				write.printf("Order %d:%n",(x));
				for(int j = 0; j < y[x]; j++) {
					write.println(Orders[x][j]);
				}
				write.println("£" + total[x]);
				write.println();
			}
		}
			
		write.println();
		double fulltotal = 0;
		for(int i = 0; i < amount-1; i++) {
			fulltotal += total[i];
		}
		write.printf("full total: %.2f",fulltotal);
		write.close();
		
	}


	private static void changeorders() {
		for(int i = 0; i < y[x]; i++) {
			System.out.println(i+1 + ". " + Orders[x][i]);
		}
		System.out.print("Please enter the number of the order you would like to remove: ");
		int choice = console.nextInt();
		System.out.println();
		System.out.print("Please enter the price of the item you want to remove: £");
		double price = console.nextDouble();
		if (choice == y[x]) {
			y[x] -= 1;
			Orders[x][choice-1] = null;
			total[x] -= price;
		} else if (choice < y[x]) {
			Orders[x][choice-1] = null;
			for(int i = choice; i < (y[x]); i++) {
				Orders[x][i-1] = Orders[x][i]; 
			}
			total[x]-= price;
			y[x] -= 1;
			Orders[x][y[x]] = null;
		}
		
	}


	private static void seeorders() {
		for(int i = 0; i < y[x]; i++) {
			System.out.println(Orders[x][i]);
		}
		System.out.printf("£%.2f%nEnter any key to continue%n",total[x]);
		@SuppressWarnings("unused")
		String cont = console.next();
		
	}
	
	private static void neworder() {
		boolean error = false;
		while(!error) {
			System.out.print("please enter new order number: ");
			x = console.nextInt();
			if (x > 49) {
				System.out.println("ERROR: Orders are from 0 - 49");
			} else {
				error = true;
			}
		}
	}


	private static void custom() {
		// TODO Auto-generated method stub
		
	}


	private static void meal() {
		System.out.printf("menu:%n1: Meal £2%n2: Meal + 1 £2.5%n3: Meal + 2 £3%n4: Foccacia £3.50%n5: custom %n6: back%n : ");
		int choice = console.nextInt();
		switch (choice) {
		case 1:
			Orders[x][y[x]] = "meal";
			total[x] += 2;
			break;
		case 2:
			Orders[x][y[x]] = "meal+1";
			total[x] += 2.5;
			break;
		case 3:
			Orders[x][y[x]] = "meal+2";
			total[x] += 3;
			break;	
		case 4:
			Orders[x][y[x]] = "foccacia";
			total[x] += 3.5;
			break;	
		case 5:
			System.out.println("please enter name: ");
			String name = console.next();
			System.out.println("please enter price: ");
			double price = console.nextDouble();
			Orders[x][y[x]] = name;
			total[x] +=	price;
			break;
		case 6:
			break;
		}
		y[x]+=1;
		
	}


	private static void food() {
		System.out.printf("menu:%n1: Toast £1%n2: Toasted Teacakes/Crumpet £1%n3: Victoria Sponge £1.50%n4: Lemon/AppleCaramel cake £1.00%n5: Scone (butter) £1.00%n6: Scone (Cream) £1.50%n7: raisins £0.20%n8: crisp £0.30%n9: custom %n0: back%n : ");
		int choice = console.nextInt();
		switch (choice) {
		case 1:
			Orders[x][y[x]] = "toast";
			total[x] += 1;
			break;
		case 2:
			Orders[x][y[x]] = "teacake/crumpet";
			total[x] += 1;
			break;
		case 3:
			Orders[x][y[x]] = "Vsponge";
			total[x] += 1.5;
			break;	
		case 4:
			Orders[x][y[x]] = "lemonapplecake";
			total[x] += 1;
			break;	
		case 5:
			Orders[x][y[x]] = "scone butter";
			total[x] += 1;
			break;
		case 6:
			Orders[x][y[x]] = "scone cream";
			total[x] += 1.5;
			break;
		case 7:
			Orders[x][y[x]] = "raisins";
			total[x] += 0.2;
			break;
		case 8:
			Orders[x][y[x]] = "crisp";
			total[x] += 0.3;
			break;
		case 9:
			System.out.println("please enter name: ");
			String name = console.next();
			System.out.println("please enter price: ");
			double price = console.nextDouble();
			Orders[x][y[x]] = name;
			total[x] +=	price;
			break;
		case 0:
			break;
		}
		y[x]+=1;
		
	}


	private static void drinks() {
		System.out.printf("menu:%n1: tea/coffee £1%n2: cappuccino £1.50%n3: latte £2.00%n4: mocha £2.50%n5: can £0.75%n6: cordial £0.50%n7: hot choc £2.00%n8: hot vimto £0.50%n9: custom %n0: back%n : ");
		int choice = console.nextInt();
		switch (choice) {
		case 1:
			Orders[x][y[x]] = "tea/coffee";
			total[x] += 1;
			break;
		case 2:
			Orders[x][y[x]] = "cappuccino";
			total[x] += 1.5;
			break;
		case 3:
			Orders[x][y[x]] = "latte";
			total[x] += 2;
			break;	
		case 4:
			Orders[x][y[x]] = "mocha";
			total[x] += 2.5;
			break;	
		case 5:
			Orders[x][y[x]] = "can";
			total[x] += 0.75;
			break;
		case 6:
			Orders[x][y[x]] = "cordial";
			total[x] += 0.5;
			break;
		case 7:
			Orders[x][y[x]] = "hot choc";
			total[x] += 2;
			break;
		case 8:
			Orders[x][y[x]] = "hot vimto";
			total[x] += 0.5;
			break;
		case 9:
			System.out.println("please enter name: ");
			String name = console.next();
			System.out.println("please enter price: ");
			double price = console.nextDouble();
			Orders[x][y[x]] = name;
			total[x] +=	price;
			break;
		case 0:
			break;
		}
		y[x]+=1;
	}
	
	
	
}
