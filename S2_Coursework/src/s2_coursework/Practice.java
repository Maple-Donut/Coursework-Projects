package s2_coursework;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;



public class Practice {

	private static final String UNICODE_FORMAT = "UTF-8";
		
	public static void main(String[] args) {
		
		
		String text = "Some test text that I need to encrypt";
		
		
		try {
			SecretKey key = generateKey("AES");
			Cipher chipher;
			chipher  = Cipher.getInstance("AES");
			
			byte[] encryptedData = encryptString(text, key, chipher);
			String encryptedString = new String(encryptedData);
			System.out.println(encryptedString);
			String decrypted = decryptString(encryptedData, key, chipher);
			System.out.println(decrypted);
		} catch(Exception e) {
			
			
			
		}
		
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
			
			return textEncrypted;
			
		} catch(Exception e) {
			
			return null;
			
		}
		
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
