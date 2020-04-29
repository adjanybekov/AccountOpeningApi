package kg.demirbank.utils;

import java.util.HashMap;
import java.util.Map;

public class Common {
	
	public static String getValue(Object data, String defaultValue) {
		if (data == null)
			return defaultValue;

		return data.toString();
	}

	public static Integer getValue(Object data, Integer defaultValue) {
		if (data == null) {
			if (defaultValue < 0)
				return null;
			else
				return defaultValue;
		}

		try {
			return Integer.valueOf(data.toString());
		} catch (Exception e) {
			if (defaultValue < 0)
				return null;
			else
				return defaultValue;
		}
	}

	public static Number getValue(Object data, Number defaultValue) {
		try {
			if (data == null) {
				if (defaultValue.intValue() < 0)
					return null;
				else
					return defaultValue;
			}

			return Integer.valueOf(data.toString());
		} catch (Exception e) {
			if (defaultValue.intValue() < 0)
				return null;
			else
				return defaultValue;
		}
	}

	public static String Cyr2LatTranslit(String src) {

		Map<String, String> Cyr2Lat = new HashMap<>();
		Cyr2Lat.put("а", "a");
		Cyr2Lat.put("б", "b");
		Cyr2Lat.put("в", "v");
		Cyr2Lat.put("г", "g");
		Cyr2Lat.put("д", "d");
		Cyr2Lat.put("е", "e");
		Cyr2Lat.put("ж", "zh");
		Cyr2Lat.put("з", "z");
		Cyr2Lat.put("и", "i");
		Cyr2Lat.put("й", "i");
		Cyr2Lat.put("к", "k");
		Cyr2Lat.put("л", "l");
		Cyr2Lat.put("м", "m");
		Cyr2Lat.put("н", "n");
		Cyr2Lat.put("о", "o");
		Cyr2Lat.put("п", "p");
		Cyr2Lat.put("р", "r");
		Cyr2Lat.put("с", "s");
		Cyr2Lat.put("т", "t");
		Cyr2Lat.put("у", "u");
		Cyr2Lat.put("ф", "f");
		Cyr2Lat.put("х", "kh");
		Cyr2Lat.put("ц", "ts");
		Cyr2Lat.put("ч", "ch");
		Cyr2Lat.put("ш", "sh");
		Cyr2Lat.put("щ", "shch");
		Cyr2Lat.put("ь", "");
		Cyr2Lat.put("ы", "y");
		Cyr2Lat.put("ъ", "");
		Cyr2Lat.put("э", "e");
		Cyr2Lat.put("ю", "iu");
		Cyr2Lat.put("я", "ia");
		Cyr2Lat.put("ң", "n");
		Cyr2Lat.put("ү", "u");
		Cyr2Lat.put("ө", "o");

		Cyr2Lat.put("А", "A");
		Cyr2Lat.put("Б", "B");
		Cyr2Lat.put("В", "V");
		Cyr2Lat.put("Г", "G");
		Cyr2Lat.put("Д", "D");
		Cyr2Lat.put("Е", "E");
		Cyr2Lat.put("Ж", "ZH");
		Cyr2Lat.put("З", "Z");
		Cyr2Lat.put("И", "I");
		Cyr2Lat.put("Й", "I");
		Cyr2Lat.put("К", "K");
		Cyr2Lat.put("Л", "L");
		Cyr2Lat.put("М", "M");
		Cyr2Lat.put("Н", "N");
		Cyr2Lat.put("О", "O");
		Cyr2Lat.put("П", "P");
		Cyr2Lat.put("Р", "R");
		Cyr2Lat.put("С", "S");
		Cyr2Lat.put("Т", "T");
		Cyr2Lat.put("У", "U");
		Cyr2Lat.put("Ф", "F");
		Cyr2Lat.put("Х", "KH");
		Cyr2Lat.put("Ц", "TS");
		Cyr2Lat.put("Ч", "CH");
		Cyr2Lat.put("Ш", "SH");
		Cyr2Lat.put("Щ", "SHCH");
		Cyr2Lat.put("Ь", "");
		Cyr2Lat.put("Ы", "Y");
		Cyr2Lat.put("Ъ", "");
		Cyr2Lat.put("Э", "E");
		Cyr2Lat.put("Ю", "IU");
		Cyr2Lat.put("Я", "IA");
		Cyr2Lat.put("Ң", "N");
		Cyr2Lat.put("Ү", "U");
		Cyr2Lat.put("Ө", "O");

		String result = "";
		for (int i = 0; i < src.length(); i++)
			result += Cyr2Lat.get(src.substring(i, i + 1)) == null ? "" : Cyr2Lat.get(src.substring(i, i + 1));

		return result;
	}
}
