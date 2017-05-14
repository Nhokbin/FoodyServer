package com.anhvu.foody.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class StringHelper {

	public static String ToUnsignString(String input) {
		input = input.trim();
		for (int i = 0x20; i < 0x30; i++) {
			input = input.replace(((char) i), ' ');
		}
		input = input.replace(".", "-");
		input = input.replace(" ", "-");
		input = input.replace(",", "-");
		input = input.replace(";", "-");
		input = input.replace(":", "-");
		input = input.replace("  ", "-");
		input = input.replace("<", "-");
		input = input.replace(">", "-");
		input = input.replace("@", "-");
		input = input.replace("!", "-");
		input = input.replace("#", "-");
		input = input.replace("*", "-");

		String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		String str2 = pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d")
				.replaceAll("Đ", "D");

		while (str2.indexOf("?") >= 0) {
			str2 = str2.substring(str2.indexOf("?"), 1);
		}
		while (str2.contains("--")) {
			str2 = str2.replace("--", "-").toLowerCase();
		}

		return str2;
	}

	public static String getStreetName(String input) {

		/*input = input.substring(0, input.indexOf(",")).replaceAll("[0-9]", "");*/
		if(input.contains(", P.")){
			input = input.substring(0,input.indexOf(". "));
			input = input.replace(", P", "");
		}
		
		System.out.println(input);
		input = input.replace("&", "");
		input = input.replace("-", "");
		input = input.replace("/", "");
		input = input.replace("  ", "");

		String[] array = input.split(",");
		System.out.println(array.length);
		String result;
		if(array.length == 5){
			result = array[2];
		}else if(array.length % 2 == 0){
			result= array[1];
		}else if(array.length % 2 !=0){
			result = array[0];
		}else{
			return null;
		}
		
		while (result.contains("[A-Za-z] ")) {
			result = result.replaceAll("[A-Za-z] ", "");
			result = result.replaceAll("  ", "");
		}
		return result.substring(result.indexOf(" "), result.length()).replaceAll("[0-9]", "");

	}
}
