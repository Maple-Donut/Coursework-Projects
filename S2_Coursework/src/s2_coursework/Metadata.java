package s2_coursework;



 
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

public class Metadata {
	static Scanner console = new Scanner(System.in);
	public static void meta_menu(String file_name) throws IOException {
		System.out.printf("Please select an option: %n1. Show Metadata%n2. Change Metadata %n3. Back%nPlease select: ");
		int choice = console.nextInt();
		switch(choice) {
		case 1:
			//Show
			show_meta(file_name);
			break;
		case 2:
			//Change
			change_meta(file_name);
			break;
		case 3:
			//Back
			break;
			
		}
		
	}
	
	public static void show_meta(String file_name) throws IOException {
		
		Path file = Paths.get(file_name);
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

		System.out.println("creationTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
		System.out.println("isDirectory: " + attr.isDirectory());
		System.out.println("isOther: " + attr.isOther());
		System.out.println("isRegularFile: " + attr.isRegularFile());
		System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
		System.out.println("size: " + attr.size());
		//shows metadata of the file
	}

	public static void change_meta(String file_name) throws IOException {
		System.out.printf("Please select an option: %n1. Last Modified Time%n2. Owner %n3. File Attributes%n4. back%nPlease select: ");
		int choice = console.nextInt();
		
		Path file = Paths.get(file_name);
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
		
		switch(choice) {
		case 1:
			//LMT;
			System.out.printf("Please enter the how much you would like to modify the time by (minutes)%n (put a negative number to take time and a positive to add time)%n: ");
			int LMT = console.nextInt();
			long currentMillis = (attr.lastModifiedTime()).toMillis(); //reads the files last modded time
			long NewTime = currentMillis + 1000 * 60 * LMT; //generates the new time
			Files.setLastModifiedTime(file, FileTime.fromMillis(NewTime)); //sets the file with the new time
			//Change the last modified time
			
			break;
		case 2:
			//Owner;
			System.out.println("Please enter your Username: ");
			String Owner = console.nextLine();
	        System.out.println("-- owner before --");
	        UserPrincipal owner = Files.getOwner(file);
	        System.out.println("Owner: " + owner);;
	        FileSystem fileSystem = file.getFileSystem();
	        UserPrincipalLookupService service = fileSystem.getUserPrincipalLookupService();
	        UserPrincipal userPrincipal = service.lookupPrincipalByName(Owner);
	        System.out.println("Found UserPrincipal: " + userPrincipal);

	        //changing owner
	        Files.setOwner(file, userPrincipal);
	        owner = Files.getOwner(file);
	        System.out.println("Owner: " + owner.getName());
			break;
		case 3:
			//File Attributes;
			System.out.println("Change to a read only file? [y/n]: ");
			String ReadOnly = (console.next()).toLowerCase();
			System.out.println("Change to a hidden file? [y/n]: ");
			String Hidden = (console.next()).toLowerCase();;
			System.out.println("Change to an archive file? [y/n]: ");
			String Archive = (console.next()).toLowerCase();;
			System.out.println("Change to a System file? [y/n]: ");
			String System = (console.next()).toLowerCase();;
			
			if (ReadOnly.charAt(0) == 'y') {
				Files.setAttribute(file, "dos:readonly", true);
			} else if (ReadOnly.charAt(0) == 'n') {
				Files.setAttribute(file, "dos:readonly", false);
			}
			if (Hidden.chrAt(0) == 'y') {
				Files.setAttribute(file, "dos:hidden", true);
			} else if (Hidden.charAt(0) == 'n') {
				Files.setAttribute(file, "dos:hidden", false);
			}
			if (Archive.charAt(0) == 'y') {
				Files.setAttribute(file, "dos:archive", true);
			} else if (Archive.charAt(0) == 'n') {
				Files.setAttribute(file, "dos:archive", false);
			}
			if (System.charAt(0) == 'y') {
				Files.setAttribute(file, "dos:system", true);
			} else if (System.charAt(0) == 'n') {
				Files.setAttribute(file, "dos:system", false);
			}
			break;
		case 4:
			//back
			break;
		}
	}
	

}
