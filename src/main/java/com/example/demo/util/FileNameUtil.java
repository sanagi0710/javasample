package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameUtil {

	private FileNameUtil() {

	}

	public static String removeDateFromFilename(String filename) {
		String regex = "^(?!.*\\b\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}\\b).+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(filename);

		if (matcher.find()) {
			return matcher.replaceAll("");
		}
		return filename;

	}

}
