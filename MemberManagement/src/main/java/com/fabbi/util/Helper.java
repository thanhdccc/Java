package com.fabbi.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {
	
	private static Helper instance;
	
	private Helper() {
		
	}
	
	public static Helper getInstance() {
		if (instance == null) {
			instance = new Helper();
		}
		return instance;
	}
	
	public String encrypt(String text) {
		String result = null;
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
			byte[] digest = messageDigest.digest();
			BigInteger bigInteger = new BigInteger(1, digest);
			result = bigInteger.toString(16);
			
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
