package main.phonetic;

import java.util.regex.Pattern;

public class Soundex {
	
	private static final byte[] TABLE = new byte[] {0,1,2,3,0,1,2,0,0,2,2,4,5,5,0,1,2,6,2,3,0,1,0,2,0,2};
	private static final Pattern NOT_ATOZ = Pattern.compile("[^a-zA-Z]");

	public static String convertToSoundex(String s) {
		if (s.isEmpty() || NOT_ATOZ.matcher(s).find()) return "Z000";
		s = s.toUpperCase();
		String ret = String.valueOf(s.charAt(0));
		int i, lastVal = getVal(s.charAt(0));
		for (i = 1; i < s.length(); i++) {
			if (ret.length() == 4) break;
			char c = s.charAt(i);
			byte charVal = getVal(c);
			if (lastVal == charVal || c == 'W' || c == 'H') continue;
			if (charVal != 0) ret += charVal;
			lastVal = charVal;
		}
		return ret.concat("0".repeat(4 - ret.length()));
	}
	
	private static byte getVal(char c) {
		return TABLE[(int)c%65];
	}
	
}
