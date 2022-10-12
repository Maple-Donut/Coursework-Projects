/*
 * Decompiled with CFR 0.152.
 */
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
        SeatBooking.menu();
    }

    public static void menu() throws FileNotFoundException {
        boolean end;
        do {
            end = true;
            System.out.printf("- - Seat Booking System - -%n- - MAIN MENU - -%n1 - Reserve Seat%n2 - Cancel Seat%n3 - View Seat Reservations%nQ - Quit%nPick : ", new Object[0]);
            char choice = console.next().charAt(0);
            if (choice == '1') {
                SeatBooking.reserve();
                continue;
            }
            if (choice == '2') {
                SeatBooking.cancel();
                continue;
            }
            if (choice == '3') {
                SeatBooking.view();
                continue;
            }
            if (choice == 'Q') {
                end = false;
                System.out.print("Exiting...");
                continue;
            }
            System.out.println("Error. Not a selected option. Please try again and enter a selected option.");
        } while (end);
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
                System.out.printf("Would you like:%nStandard [1]%nFirst Class [2]%nPick:  ", new Object[0]);
                answer[0] = console.nextInt();
                if (!(answer[0] == 1 ^ answer[0] == 2)) {
                    System.out.println("Please only select 1 or 2");
                    continue;
                }
                System.out.printf("Would you like a window seat:%nYes [1]%nNo [2]%nPick: ", new Object[0]);
                answer[1] = console.nextInt();
                if (!(answer[1] == 1 ^ answer[1] == 2)) {
                    System.out.println("Please only select 1 or 2");
                    continue;
                }
                System.out.printf("Would you like an aisle seat:%nYes [1]%nNo [2]%nPick: ", new Object[0]);
                answer[2] = console.nextInt();
                if (!(answer[2] == 1 ^ answer[2] == 2)) {
                    System.out.println("Please only select 1 or 2");
                    continue;
                }
                System.out.printf("Would you like a table seat:%nYes [1]%nNo [2]%nPick: ", new Object[0]);
                answer[3] = console.nextInt();
                if (!(answer[3] == 1 ^ answer[3] == 2)) {
                    System.out.println("Please only select 1 or 2");
                    continue;
                }
                error = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Error please input the answer in the correct format");
                console.next();
            }
        }
        error = false;
        while (!error) {
            System.out.print("Finally, please input your email address: ");
            eMail = console.next();
            if (!eMail.contains("@") || eMail.contains("..") || !eMail.contains(".") || eMail.length() < 5) {
                System.out.println("Please input the email in the correct format");
                continue;
            }
            error = true;
        }
        SeatBooking.read(eMail, answer, pointArray, SeatInfo);
        int recommended = 0;
        int recSeat = 0;
        int i = 0;
        while (i < pointArray.length) {
            if (pointArray[i] > recommended) {
                recommended = pointArray[i];
                recSeat = i;
            }
            ++i;
        }
        error = false;
        while (!error) {
            System.out.printf("The recommended seat available is seat number %s at \u00a3%.2f.%nWould you like to book this seat?: ", SeatInfo[recSeat].seatNum, SeatInfo[recSeat].seatPrice);
            String decision = console.next();
            decision.toLowerCase();
            if (decision.equals("y") || decision.equals("yes")) {
                SeatInfo[recSeat].eMail = eMail;
                error = true;
                SeatBooking.write(SeatInfo);
                System.out.println("Your seat has been booked.");
                continue;
            }
            if (decision.equals("n") || decision.equals("no")) {
                error = true;
                System.out.println("Booking cancelled.");
                continue;
            }
            System.out.println("Please input 'yes' or 'no'. ");
        }
    }

    public static void cancel() throws FileNotFoundException {
        System.out.print("Please enter your seat number of the booking you wish to cancel: ");
        String seatSelected = console.next();
        FileReader file = new FileReader("seats.txt");
        Scanner read = new Scanner(file);
        int count = 0;
        SeatBooking[] SeatInfo = new SeatBooking[18];
        while (read.hasNext()) {
            SeatBooking Seat;
            SeatInfo[count] = Seat = new SeatBooking(read.next(), read.next(), read.nextBoolean(), read.nextBoolean(), read.nextBoolean(), read.nextDouble(), read.next());
            ++count;
        }
        read.close();
        System.out.print("Please enter your email: ");
        String eMail = console.next();
        boolean cancelled = false;
        int i = 0;
        while (i < SeatInfo.length) {
            if (seatSelected.equals(SeatInfo[i].seatNum)) {
                if (eMail.equals(SeatInfo[i].eMail)) {
                    SeatInfo[i].eMail = "free";
                    SeatBooking.write(SeatInfo);
                    System.out.println("Booking cancellation complete.");
                    cancelled = true;
                } else {
                    System.out.println("Sorry that is the wrong email. Booking Cancellation denied.");
                    cancelled = true;
                }
            } else if (i == 17 && !cancelled) {
                console.nextLine();
                System.out.printf("Sorry. Invalid Seat Number inputted.%nPlease refer to the 'View Seat Reservations' Option on the main menu for the correct Seat Numbers.%nPress [ENTER] to return to the main menu.", new Object[0]);
                seatSelected = console.nextLine();
            }
            ++i;
        }
    }

    public static void view() throws FileNotFoundException {
        FileReader file = new FileReader("seats.txt");
        Scanner PrintReader = new Scanner(file);
        while (PrintReader.hasNextLine()) {
            String text = PrintReader.nextLine();
            System.out.println(text);
        }
        PrintReader.close();
        console.nextLine();
        System.out.println("Press [ENTER] to go back to the main menu.");
        String back = console.nextLine();
    }

    public static void read(String email, int[] answer, int[] pointArray, SeatBooking[] SeatInfo) throws FileNotFoundException {
        FileReader file = new FileReader("seats.txt");
        Scanner read = new Scanner(file);
        int points = 0;
        int count = 0;
        while (read.hasNext()) {
            SeatBooking Seat = new SeatBooking(read.next(), read.next(), read.nextBoolean(), read.nextBoolean(), read.nextBoolean(), read.nextDouble(), read.next());
            points = 0;
            if (Seat.seatClass.equals("STD") && answer[0] == 1 || Seat.seatClass.equals("1ST") && answer[0] == 2) {
                points += 5;
            }
            if (Seat.isWindow && answer[1] == 1 || !Seat.isWindow && answer[1] == 2) {
                ++points;
            }
            if (Seat.isAisle && answer[1] == 1 || !Seat.isAisle && answer[1] == 2) {
                ++points;
            }
            if (Seat.isTable && answer[1] == 1 || !Seat.isTable && answer[1] == 2) {
                ++points;
            }
            if (!Seat.eMail.equals("free")) {
                points -= 100;
            }
            SeatInfo[count] = Seat;
            pointArray[count] = points;
            ++count;
        }
        read.close();
    }

    public static void write(SeatBooking[] SeatInfo) throws FileNotFoundException {
        PrintWriter write = new PrintWriter("seats.txt");
        int i = 0;
        while (i < SeatInfo.length) {
            write.println(String.valueOf(SeatInfo[i].seatNum) + " " + SeatInfo[i].seatClass + " " + SeatInfo[i].isWindow + " " + SeatInfo[i].isAisle + " " + SeatInfo[i].isTable + " " + SeatInfo[i].seatPrice + " " + SeatInfo[i].eMail);
            ++i;
        }
        write.close();
    }
}
