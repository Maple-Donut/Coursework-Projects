package s2_coursework;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;



public class Practice_2 {

	private static final String UNICODE_FORMAT = "UTF-8";
	
	
//	public static void main(String[] args) {
//		System.out.println("test");
//	}
	
		
	public static void main(String[] args) {
		
		
		String text = "Some test text that I need to encrypt";
		
		
		try {
			SecretKey key = generateKey("AES");
	//		StoreToKeyStore(key,"45261873497","keystore.keystore");
	//		SecretKey key = LoadFromKeyStore("keystore.keystore","45261873497");
			Cipher chipher;
			chipher  = Cipher.getInstance("AES");
			
			byte[] encryptedData = encryptString(text, key, chipher);
			String encryptedString = new String(encryptedData);
			System.out.println(encryptedString);
			String decrypted = decryptString(encryptedData, key, chipher);
			System.out.println(decrypted);
		} catch(Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public static void StoreToKeyStore(SecretKey keyToStore, String password, String filepath) throws Exception {
		
		File file = new File(filepath);
		KeyStore javaKeyStore = KeyStore.getInstance("JCEKS");
		if(!file.exists()) {
			javaKeyStore.load(null,null);
		}
		javaKeyStore.setKeyEntry("keyAlias", keyToStore, password.toCharArray(), null);
		OutputStream writeStream = new FileOutputStream(filepath);
		javaKeyStore.store(writeStream, password.toCharArray());
	
	}
	
	public static SecretKey LoadFromKeyStore(String filepath, String password) {
		
		try {
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
	
	
	
	
	public static SecretKey generateKey(String encryptionType) {
		
		try {
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
			
			for (int i = 0; i < textEncrypted.length; i++) {
				System.out.print(textEncrypted[i] + ", ");
			}
			System.out.println();
			for (int i = 0; i < textEncrypted.length; i++) {
				System.out.print((char) textEncrypted[i] + ", ");
			}
			System.out.println();
			
			return textEncrypted;
			
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
	

}