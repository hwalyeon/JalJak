package kr.co.seculink.util;

import java.util.Random;

import kr.co.seculink.exception.SysException;
import kr.co.seculink.util.seed256.Seed256;

public class Seed256EncUtil {

	public static String encrypt(String plainData, String key) throws SysException {
		
		Seed256 seed = new Seed256();
		
		try {
			seed.setKey(key);
		} catch (Exception e) {
			throw new SysException(e, "ENC ERROR", null);
		}
		
		return seed.encrypt(plainData);
	}
	
	public static String decrypt(String encryptedData, String key) throws SysException {
		
		Seed256 seed = new Seed256();
		
		try {
			seed.setKey(key);
		} catch (Exception e) {
			throw new SysException(e, "ENC ERROR", null);
		}
		
		return seed.decrypt(encryptedData);
	}
	
	public static String getRandomKey() {
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder();
		
		for (int idx = 0 ; idx < 32 ; idx ++) {
		
			int rCase = rnd.nextInt(3);
			
			switch (rCase) {
				case 0:
					sb.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
				case 1:
					sb.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
				case 2:
					sb.append(rnd.nextInt(10));
				break;
			}
		}
		
		return sb.toString();
	}
}
