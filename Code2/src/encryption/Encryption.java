package encryption;

import java.math.BigInteger;
import java.util.Scanner;

public class Encryption {
	static Scanner console = new Scanner(System.in);
	public static void main(String[] args) {
		// Variables Declared
		String CipherStr = "";
		int Lock[] = new int[2];
		int Key[] = new int[2];
		//user enters prime numbers and plain text as inputs for RSA Encryption
		System.out.print("Please enter a prime number:");
		int q = console.nextInt();  
		System.out.print("Please enter a second prime number:");
		int p = console.nextInt();
		console.nextLine();
		System.out.print("Please enter your plain text:");
		String plain = console.nextLine();
		int x = plain.length();
		//Array Variable Declared that rely on length of plain text
		int ASCII[] = new int[x];
		
		
		
		//Key Generation 
		int N = p*q;
		//Generate φ(N)(Number of Co-Primes left)
		int phi = (p-1)*(q-1);
		boolean loop = false;
		int e = 0;
		int d = 0;
		int h = 1;
		int y;
		do {
			y = (phi * h) + 1;
		    for (int g = 1; g <= y; g++) {
		        if ((y % g == 0) && g != 1 && g != y) {
		        	if (!(y % Math.pow(g, 2) == 0)) {
		        		if (loop == false) {
			        		e = g;
			        		loop = true;
			        	} else {
			        		d = g;
			        		loop = false;
			        	}
		        	}
		        }
		     }
		    if (loop == true) {
		    	d = e;
		    	loop = false;
		    }
		    h+=1;
		} while(gcd(e,phi) != 1 && gcd(d,phi) != 1 ||( e == 0 || d == 0));
		//Define Lock
		Lock[0] = e;
		Lock[1] = N;
		
		//Define Key
		Key[0] = d;
		Key[1] = N;
	
			
		/* For RSA Encryption to work A=1,B=2....Z=26
		 * ASCII needd to be converted to this when the modular function
		 * with the lock is used so "(int) plain.charAt(i) - (int)'A' + 1"
		 * is used to accomplish this.
		 * 
		 * A config array is also used to remember is the character was a
		 * Uppercase, Lowercase or a Special Character for decryption.
		 */
		BigInteger BigLock = BigInteger.valueOf(Lock[1]);
		BigInteger reallyBig = new BigInteger("12345678901234567890");
		BigInteger reallyBig2 = new BigInteger("12345678901234567890");
		
		for (int i = 0; i < x; i++) {
			ASCII[i]= (int) plain.charAt(i);
			reallyBig = BigInteger.valueOf((long) ASCII[i]).pow(Lock[0]);
			reallyBig2 = reallyBig.mod(BigLock);
			CipherStr += (char) reallyBig2.intValue(); //Cipher text in caps
			
		}
		
		System.out.println(CipherStr);
		
		Decryption.Decrypt(CipherStr, Key); //link to decryption
	}
		
	    static int gcd(int a, int b)
	    {
	        // stores minimum(a, b)
	        int x;
	        if (a < b)
	            x = a;
	        else
	            x = b;
	 
	        // take a loop iterating through smaller number to 1
	        for (int i = x ;i > 1; i--) {
	 
	            // check if the current value of i divides both
	            // numbers with remainder 0 if yes, then i is
	            // the GCD of a and b
	            if (a % i == 0 && b % i == 0)
	                return i;
	        }
	 
	        // if there are no common factors for a and b other
	        // than 1, then GCD of a and b is 1
	        return 1;
		
	}
	
}
	


	

