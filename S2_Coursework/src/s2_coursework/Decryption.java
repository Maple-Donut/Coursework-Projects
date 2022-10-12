package s2_coursework;

import java.io.*;
import java.security.KeyStore;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;


public class Decryption {
	static Scanner console = new Scanner(System.in);
	//decryption menu
	public static void Dec_Menu(ArrayList<String> file_arr) throws FileNotFoundException {
		System.out.printf("Please select a type of decryption: %n1. AES%n2. DES %n3. XOR %n4. Back%nPlease select: ");
		int choice = console.nextInt();
		switch(choice) {
		case 1:
			//AES
			Dec_AES(file_arr);
			break;
		case 2:
			//DES
			Dec_DES(file_arr);
			break;
		case 3:
			//XOR
			Dec_XOR(file_arr);
			break;
		case 4:
			//Back
			break;
			
		}
	
	}
	
	public static void Dec_AES(ArrayList<String> file_arr) {
		//user selects keystore password, keystore file name and the output file name
		
		System.out.print("Please enter a key: ");
		String password = console.next();
		
		System.out.println("Please enter a keystore name: ");
		String keystore = console.next();
		
		System.out.println("Current test [note dont put .txt]: ");
		String output = console.next();
		
		
		try {
			//gets instance of AES Cipher from Cipher class
			Cipher chipher;
			chipher  = Cipher.getInstance("AES");
			PrintWriter write = new PrintWriter(output + ".txt"); //opens printwriter
			System.out.println("Writing...");
			SecretKey key = LoadFromKeyStore(keystore + ".keystore",password); //loads the key from the keystore and uses the keystore name and password
			
			String decryption;
			String[] decrypt;
			for(int i = 0; i < file_arr.size(); i++) {
				decryption = file_arr.get(i);
				decrypt = decryption.split(", ");
				byte[] test = new byte[decrypt.length];
				for(int j = 0; j < decrypt.length; j++) {
					test[j] = (byte)(Integer.parseInt(decrypt[j]));
					//reads file of int bytes into raw bytes
				}
				String decrypted = decryptString(test, key, chipher);
				//decryptes the bytes
				write.println(decrypted);
				//writes the decrypted text into the file
			}
			
			write.close();
			
			
		}  catch (Exception e) {
			System.out.println(e);
		}
		
	}
	//same comments as DEC_AES however the cipher used is from a different instance
	public static void Dec_DES(ArrayList<String> file_arr) {
		
		System.out.print("Please enter a key: ");
		String password = console.next();
		
		System.out.println("Please enter a keystore name: ");
		String keystore = console.next();
		
		System.out.println("Current test [note dont put .txt]: ");
		String output = console.next();
		
		
		try {
			Cipher chipher;
			chipher  = Cipher.getInstance("DES");
			
			PrintWriter write = new PrintWriter(output + ".txt");
			System.out.println("Writing...");
			SecretKey key = LoadFromKeyStore(keystore + ".keystore",password);
			
			String decryption;
			String[] decrypt;
			for(int i = 0; i < file_arr.size(); i++) {
				decryption = file_arr.get(i);
				decrypt = decryption.split(", ");
				byte[] test = new byte[decrypt.length];
				for(int j = 0; j < decrypt.length; j++) {
					test[j] = (byte)(Integer.parseInt(decrypt[j]));
				}
				String decrypted = decryptString(test, key, chipher);
				write.println(decrypted);
			}
			
			write.close();
			
			
		}  catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	
	public static SecretKey LoadFromKeyStore(String filepath, String password) {
		
		try {
			/*
			 * loads the keystore file that the user gave and, if the correct password is given,
			 * the key will be successfully loaded from the keystore
			 */
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			InputStream readStream = new FileInputStream(filepath);
			keyStore.load(readStream,password.toCharArray());
			SecretKey key = (SecretKey) keyStore.getKey("KeyAlias", password.toCharArray());
			return key;
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
		return null;
		
		
	}
	
	public static String decryptString(byte[] dataToDecrypt, SecretKey myKey, Cipher cipher) {
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, myKey);
			byte[] textDecrypted = cipher.doFinal(dataToDecrypt);
			String result = new String(textDecrypted);
			
			return result;
			
		} catch(Exception e) {
			
			
			System.out.println(e);
			return null;
			
		}
		
	}
	
	
	
	
	public static void Dec_XOR(ArrayList<String> file_arr) throws FileNotFoundException {
		
		System.out.print("Please enter a key: ");
		String key = console.next();
		String[] Decrypted = new String[file_arr.size()];
		
		for(int i = 0; i < file_arr.size(); i++) {
			Decrypted[i] = "";        
	        for(int j = 0; j < file_arr.get(i).length(); j++) {
	        	//reads each character and XORS the character with the given key
	            Decrypted[i] += (char) ((((int)file_arr.get(i).charAt(j)) - 48) ^ (int) key.charAt(j % (key.length() - 1)));
	        }
	        
		}
		
		System.out.println("Current test [note dont put .txt]: ");
		String output = console.next();
		
		
		PrintWriter write = new PrintWriter(output + ".txt");
		System.out.println("Writing...");
		for (int i = 0; i < Decrypted.length; i++ ) {
				write.println(Decrypted[i]);		
		}
		write.close();
		System.out.printf("Done!%n%n");
		
	}
		
}