import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

public class Encrypt_StaticKey {
  String base64,clear;
	StringBuffer sb = new StringBuffer();
	SecretKeySpec skey;

	
	public static void main(String args[]) 
	{
		Encrypt_StaticKey e= new Encrypt_StaticKey();
		//e.generateKey();
		String t;
		t=e.encryption("Hello how are you");
		e.decrypt(t);
		

	}

	private String encryption(String message)
	{
		try
		{
			//Key GENERATION////////////////
			String t1="12345678";
			byte[] b1=t1.getBytes();
			SecretKeySpec sk1= new SecretKeySpec(b1, "DES");
			
			// Get a cipher object.
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, sk1);
	
			// Gets the raw bytes to encrypt, UTF8 is needed for
			// having a standard character set
			byte[] stringBytes = message.getBytes("UTF8");
	
			// encrypt using the cypher
			byte[] raw = cipher.doFinal(stringBytes);
	
			
			// converts to base64 for easier display.
			BASE64Encoder encoder = new BASE64Encoder();
			 base64 = encoder.encode(raw);
			 System.out.println("Encrypted data " + base64);
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
			//Key GENERATION////////////////
			String t1="12345678";
			byte[] b1=t1.getBytes();
			SecretKeySpec sk1= new SecretKeySpec(b1, "DES");
			
			// Get a cipher object.
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, sk1);
		
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
