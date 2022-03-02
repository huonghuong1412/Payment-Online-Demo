package com.example.demo.utils;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class RSAUtil {
	private static KeyFactory keyFactory;
	private static Cipher cipher;

	static {
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			cipher = Cipher.getInstance("RSA");
		} catch (Exception ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static PublicKey stringToPublicKey(String pubkeyStr) throws InvalidKeySpecException {
		byte[] bytesKey = Base64.getDecoder().decode(pubkeyStr.getBytes());
		return keyFactory.generatePublic(new X509EncodedKeySpec(bytesKey));
	}

	public static PrivateKey stringToPrivateKey(String prikeyStr) throws InvalidKeySpecException {
		byte[] bytesKey = Base64.getDecoder().decode(prikeyStr.getBytes());
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(bytesKey));
	}

	public static String encrypt(PublicKey pubkey, String message)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		cipher.init(Cipher.ENCRYPT_MODE, pubkey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
	}

	public static String encrypt(String pubkeyStr, String message)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
		return encrypt(stringToPublicKey(pubkeyStr), message);
	}

	public static byte[] decrypt(PrivateKey prikey, String message)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		cipher.init(Cipher.ENCRYPT_MODE, prikey);
		return cipher.doFinal(message.getBytes());
	}

	public static byte[] decrypt(String prikeyStr, String message)
			throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
		return decrypt(stringToPrivateKey(prikeyStr), message);
	}
}
