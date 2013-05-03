package com;

import java.security.Key;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import sun.misc.*;

public class Encrypt {
  private Key key;
	String base64,clear;
	StringBuffer sb = new StringBuffer();
	

	
	public static void main(String args[]) 
	{
		Encrypt e= new Encrypt();
		e.generateKey();
		String t;
		t=e.encryption("Hello how are you");
		e.decrypt(t);

	}

	private void generateKey()  
	{
		try
		{
			KeyGenerator generator;
			generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom());
			key = generator.generateKey();
		}
		catch(Exception e)
		{
			System.out.println("Error Occurred in Key GENERATION...");
		}
	}

	private String encryption(String message)
	{
		try
		{
			// Get a cipher object.
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
	
			// Gets the raw bytes to encrypt, UTF8 is needed for
			// having a standard character set
			byte[] stringBytes = message.getBytes("UTF8");
	
			// encrypt using the cypher
			byte[] raw = cipher.doFinal(stringBytes);
	
			
			// converts to base64 for easier display.
			BASE64Encoder encoder = new BASE64Encoder();
			 base64 = encoder.encode(raw);
		}
		catch(Exception e)
		{
			System.out.println("Error occured in ENRYPTION........");
		}
		return base64;
	}
	private String decrypt(String encrypted)  
	{
		try
		{
		

			// Get a cipher object.
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
		
			//decode the BASE64 coded message
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] raw = decoder.decodeBuffer(encrypted);
		
			//decode the message
			byte[] stringBytes = cipher.doFinal(raw);
			
			//converts the decoded message to a String
			 clear = new String(stringBytes, "UTF8");

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in DECRYPTION.........");
		}
		System.out.println("DECRYPTION........." + clear);
	return clear;
	
	}
	
	

}
