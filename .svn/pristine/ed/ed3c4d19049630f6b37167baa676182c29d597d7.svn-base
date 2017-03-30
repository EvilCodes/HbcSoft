package com.hbcsoft.zdy.util;

import java.util.ArrayList;
import java.util.List;

import com.yaja.common.util.YAJAUtil;

public class PubTools extends YAJAUtil {

	public static String[] split(final String str) {
		return split(str, null, -1);
	}

	public static String[] split(final String str, final char separatorChar) {
		return splitWorker(str, separatorChar, false);
	}

	public static String[] split(final String str, final String separatorChars) {
		return splitWorker(str, separatorChars, -1, false);
	}

	public static String[] split(final String str, final String separatorChars, final int max) {
		return splitWorker(str, separatorChars, max, false);
	}

	public static boolean isBlank(final String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(final String str) {
		return !isBlank(str);
	}

	public static boolean equals(final String str1, final String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}
	
	public static String getStatus(final String status, final int sort)
	{
		
		return status + toString((sort - 1), 2, "0");
	}
	
	public static boolean chkEmpty(final String[] arr)
	{
		if(arr.length < 1)
		{
			return true;
		}
		for(int index = 0; index < arr.length; index++)
		{
			if("-1".equals(arr[index]))
			{
				return true;
			}
		}
		return false;
	}

	private static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {
		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		final List<String> list = new ArrayList<String>();
		int index = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (index < len) {
			if (str.charAt(index) == separatorChar) {
				if (match || preserveAllTokens) {
					list.add(str.substring(start, index));
					match = false;
					lastMatch = true;
				}
				start = ++index;
				continue;
			}
			lastMatch = false;
			match = true;
			index++;
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, index));
		}
		return list.toArray(new String[list.size()]);
	}

	private static String[] splitWorker(final String str, final String separatorChars, final int max, 
			final boolean preserveAllTokens) {
		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		final List<String> list = new ArrayList<String>();
		if (separatorChars == null) {
			spliSub1(list, str, max, preserveAllTokens);
		} else if (separatorChars.length() == 1) {
			spliSub2(list, str, separatorChars, max, preserveAllTokens);
		} else {
			spliSub3(list, str, separatorChars, max, preserveAllTokens);
		}
		return list.toArray(new String[list.size()]);
	}
	
	private static void spliSub1(final List<String> list,final String str, final int max, 
			final boolean preserveAllTokens)
	{
		int sizePlus1 = 1;
		int index = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		final int len = str.length();
		while (index < len) {
			if (Character.isWhitespace(str.charAt(index))) {
				if (match || preserveAllTokens) {
					lastMatch = true;
					if (sizePlus1 == max) {
						index = len;
						lastMatch = false;
					}
					sizePlus1++;
					list.add(str.substring(start, index));
					match = false;
				}
				start = ++index;
				continue;
			}
			lastMatch = false;
			match = true;
			index++;
		}

		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, index));
		}
	}
	
	private static void spliSub2(final List<String> list,final String str, final String separatorChars, final int max, 
			final boolean preserveAllTokens)
	{
		int sizePlus1 = 1;
		int index = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		final int len = str.length();
		final char sep = separatorChars.charAt(0);
		while (index < len) {
			if (str.charAt(index) == sep) {
				if (match || preserveAllTokens) {
					lastMatch = true;
					if (sizePlus1 == max) {
						index = len;
						lastMatch = false;
					}
					sizePlus1++;
					list.add(str.substring(start, index));
					match = false;
				}
				start = ++index;
				continue;
			}
			lastMatch = false;
			match = true;
			index++;
		}

		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, index));
		}
	}
	
	private static void spliSub3(final List<String> list,final String str, final String separatorChars, final int max, 
			final boolean preserveAllTokens)
	{
		int sizePlus1 = 1;
		int index = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		final int len = str.length();
		while (index < len) {
			if (separatorChars.indexOf(str.charAt(index)) >= 0) {
				if (match || preserveAllTokens) {
					lastMatch = true;
					if (sizePlus1 == max) {
						index = len;
						lastMatch = false;
					}
					sizePlus1++;
					list.add(str.substring(start, index));
					match = false;
				}
				start = ++index;
				continue;
			}
			lastMatch = false;
			match = true;
			index++;
		}

		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, index));
		}
	}
}
