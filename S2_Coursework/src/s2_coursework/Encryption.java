package s2_coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.KeyStore;


public class Encryption {
	static Scanner console = new Scanner(System.in);
	
	private static final String UNICODE_FORMAT = "UTF-8";
	
	
	public static void Enc_Menu(ArrayList<String> file_arr) throws FileNotFoundException {
		System.out.printf("Please select a type of encryption: %n1. AES%n2. DES %n3. XOR %n4. Back%nPlease select: ");
		int choice = console.nextInt();
		switch(choice) {
		case 1:
			//AES
			Enc_AES(file_arr);
			break;
		case 2:
			//DES
			Enc_DES(file_arr);
			break;
		case 3:
			//XOR
			Enc_XOR(file_arr);
			break;
		case 4:
			//Back
			break;
			
		}
	
	}
	
	public static void Enc_AES(ArrayList<String> file_arr) {
		//user selects keystore password, keystore file name and the output file name
		
		System.out.print("Please enter a key: ");
		String password = console.next();
		
		System.out.println("Please enter a keystore name: ");
		String keystore = console.next();
		
		System.out.println("Current test [note dont put .txt]: ");
		String output = console.next();
		
		try {
			//generates a key for AES to be stored
			SecretKey key = generateKey("AES");
			//stores the key into a keystore protected with the password given by the user
			StoreToKeyStore(key, password, keystore + ".keystore");
			Cipher chipher;
			//gets instance of AES Cipher from Cipher class
			chipher  = Cipher.getInstance("AES");
			
			PrintWriter write = new PrintWriter(output + ".txt");
			System.out.println("Writing...");
			for(int i = 0; i < file_arr.size(); i++) {
				byte[] encryptedData = encryptString(file_arr.get(i), key, chipher);		
				for (int j = 0; j < encryptedData.length; j++) {
					write.print(encryptedData[j] + ", ");
					//writes the encrypted byetes into the given file name from the user
				}
				write.println();
			}
			write.close();
			
			System.out.printf("Done!%n%n");
		} catch(Exception e) {		
			System.out.println(e);		
		}	
	}
	
	//same comments as DEC_AES however the cipher used is from a different instance
	public static void Enc_DES(ArrayList<String> file_arr) {
		System.out.print("Please enter a key: ");
		String password = console.next();
		
		System.out.println("Please enter a keystore name: ");
		String keystore = console.next();
		
		System.out.println("Current test [note dont put .txt]: ");
		String output = console.next();
		
		try {
			SecretKey key = generateKey("DES");
			System.out.println(key);
			StoreToKeyStore(key, password, keystore + ".keystore");
			Cipher chipher;
			chipher  = Cipher.getInstance("DES");
			
			PrintWriter write = new PrintWriter(output + ".txt");
			System.out.println("Writing...");
			for(int i = 0; i < file_arr.size(); i++) {
				byte[] encryptedData = encryptString(file_arr.get(i), key, chipher);		
				for (int j = 0; j < encryptedData.length; j++) {
					write.print(encryptedData[j] + ", ");
				}
				write.println();
			}
			write.close();
			
			System.out.printf("Done!%n%n");
		} catch(Exception e) {		
			System.out.println(e);		
		}	
	}
		
	
	
	
	
	
	public static SecretKey generateKey(String encryptionType) {
		
		try {
			//generates key from the specified encryption type
			KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionType);
			SecretKey myKey = keyGenerator.generateKey();
			return myKey;
			
			
		} catch(Exception e) {
			
			return null;
			
		}
		
		
	}
	
	public static byte[] encryptString(String dataToEncrypt, SecretKey myKey, Cipher cipher) {
		
		try {
			byte[] text = dataToEncrypt.getBytes(UNICODE_FORMAT);
			cipher.init(Cipher.ENCRYPT_MODE, myKey);
			byte[] textEncrypted = cipher.doFinal(text);
			
			return textEncrypted;
			
		} catch(Exception e) {
			
			System.out.println(e);
		}
		return null;
	}
	
	public static void StoreToKeyStore(SecretKey keyToStore, String password, String filepath) throws Exception {
		/*
		 * loads and writes the key generated into a keystore file which is
		 * protected by the password the user gave
		 */
		File file = new File(filepath);
		KeyStore javaKeyStore = KeyStore.getInstance("JCEKS");
		if(!file.exists()) {
			javaKeyStore.load(null,null);
		}
		javaKeyStore.setKeyEntry("keyAlias", keyToStore, password.toCharArray(), null);
		OutputStream writeStream = new FileOutputStream(filepath);
		javaKeyStore.store(writeStream, password.toCharArray());
	
	}
	
	
	
	
	
	public static void Enc_XOR(ArrayList<String> file_arr) throws FileNotFoundException {
		
		//user gives a key
		System.out.print("Please enter a key: ");
		String key = console.next();
		String[] Encrypted = new String[file_arr.size()];
		
		for(int i = 0; i < file_arr.size(); i++) {
			Encrypted[i] = "";
			 for(int j = 0; j < file_arr.get(i).length(); j++) {
				 //encrypts each char seperately with the given key and forms an ecrypted array
		            int enc_int = (Integer.valueOf(file_arr.get(i).charAt(j)) ^ Integer.valueOf(key.charAt(j % (key.length() - 1)))) + '0';
		            Encrypted[i] += (char)enc_int;
		        }
		}
		
		System.out.println("Current test [note dont put .txt]: ");
		String output = console.next();
		
		PrintWriter write = new PrintWriter(output + ".txt");
		System.out.println("Writing...");
		for (int i = 0; i < Encrypted.length; i++ ) {
				write.println(Encrypted[i]);		
		}
		//writes the ecrypted array into a file
		write.close();
		System.out.printf("Done!%n%n");
		
	}
	
	
}
