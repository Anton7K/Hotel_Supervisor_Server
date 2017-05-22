package nure.kaplun.HotelSupervisor.controller;
 
import java.io.UnsupportedEncodingException;
import java.security.*;

public class Encryption {
	private static Encryption instance;

	public static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		byte[] bytesOfMessage = password.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		return  new String(thedigest, "UTF-8");

	}
}
