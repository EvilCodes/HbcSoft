package com.hbcsoft.zdy.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class HbcsoftMD {
	
	public static String md5(final String str) {
		return md5(str.getBytes()).toUpperCase();
	}

	public static String md5(final byte[] bytes) {
		return byte2string(mdx(bytes, "MD5"));
	}

	public static String sha1(final String str) {
		return sha1(str.getBytes());
	}

	public static String sha1(final byte[] bytes) {
		return byte2string(mdx(bytes, "SHA-1"));
	}

	public static String sha256(final String str) {
		return sha256(str.getBytes());
	}

	public static String sha256(final byte[] bytes) {
		return byte2string(mdx(bytes, "SHA-256"));
	}

	private static byte[] mdx(final byte[] bs, final String algorithm) {
		try {
			final MessageDigest mess = MessageDigest.getInstance(algorithm);
			mess.reset();
			mess.update(bs);
			return mess.digest();
		} catch (NoSuchAlgorithmException e) {
			Logger.getLogger(HbcsoftMD.class).error(e);
		}
		return new byte[0];
	}

	public static String byte2string(final byte[] bs) {
		final StringBuilder sb = new StringBuilder();
		for (int index = 0; index < bs.length; index++) {
			sb.append(String.format("%02x", bs[index] & 0xff));
		}
		return sb.toString();
	}
}
