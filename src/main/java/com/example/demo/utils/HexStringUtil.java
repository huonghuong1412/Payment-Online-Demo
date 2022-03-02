package com.example.demo.utils;

import java.util.Locale;

public class HexStringUtil {
	static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5',
			(byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e',
			(byte) 'f' };

	public static String byteArrayToHexString(byte[] raw) {
		byte[] hex = new byte[2 * raw.length];
		int index = 0;

		for (byte b : raw) {
			int v = b & 0xFF;
			hex[index++] = HEX_CHAR_TABLE[v >>> 4];
			hex[index++] = HEX_CHAR_TABLE[v & 0xF];
		}
		return new String(hex);
	}

	public static byte[] hexStringToByteArray(String hex) {
		String hexstandard = hex.toLowerCase(Locale.ENGLISH);
		int sz = hexstandard.length() / 2;
		byte[] bytesResult = new byte[sz];

		int idx = 0;
		for (int i = 0; i < sz; i++) {
			bytesResult[i] = (byte) (hexstandard.charAt(idx));
			++idx;
			byte tmp = (byte) (hexstandard.charAt(idx));
			++idx;

			if (bytesResult[i] > HEX_CHAR_TABLE[9]) {
				bytesResult[i] -= ((byte) ('a') - 10);
			} else {
				bytesResult[i] -= (byte) ('0');
			}
			if (tmp > HEX_CHAR_TABLE[9]) {
				tmp -= ((byte) ('a') - 10);
			} else {
				tmp -= (byte) ('0');
			}

			bytesResult[i] = (byte) (bytesResult[i] * 16 + tmp);
		}
		return bytesResult;
	}
}
