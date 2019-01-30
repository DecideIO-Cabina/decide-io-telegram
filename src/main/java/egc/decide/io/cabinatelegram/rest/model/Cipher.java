package egc.decide.io.cabinatelegram.rest.model;

import java.math.*;
import java.util.Random;

public class Cipher {
	
	public static BigInteger[] encrypt(BigInteger p,BigInteger g,BigInteger y,BigInteger m) {
		
		BigInteger max = BigInteger.valueOf(2).pow(256).subtract(BigInteger.valueOf(1));		
		BigInteger r;
		Random ran = new Random();
		
		do {
		    r = new BigInteger(max.bitLength(), ran);
		} while (r.compareTo(max) >= 0);
		
		BigInteger alpha = g.modPow(r, p);
		BigInteger beta = y.modPow(r, p).multiply(m).mod(p);
		BigInteger[] res = {alpha, beta};
		return res;	
	}

}
