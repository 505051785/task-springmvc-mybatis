package controller.tools;

import java.io.UnsupportedEncodingException;

public class StringTool {
	public static String ToUTF(String str) {
		String result = null;
		try {
			result = new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//public static void sub
}
