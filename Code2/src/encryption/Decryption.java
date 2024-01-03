package encryption;

import java.math.BigInteger;
import java.util.Scanner;


public class Decryption {
static Scanner console = new Scanner(System.in);
	public static void Decrypt(String CipherStr, int Key[]) {
		//define variables for decrypting
		int x = CipherStr.length();
		int ASCII[] = new int[x];
		String plain = "";
		BigInteger BigKey = BigInteger.valueOf(Key[1]);
		BigInteger reallyBig = new BigInteger("12345678901234567890");
		
		for (int i = 0; i < x; i++) {
			ASCII[i] = (int) CipherStr.charAt(i);
			reallyBig = BigInteger.valueOf((long) ASCII[i]).pow(Key[0]);
			reallyBig = reallyBig.mod(BigKey);
			plain += (char) reallyBig.intValue();	
		}
		System.out.println(plain);
		
		
		
		
		
	}
}
