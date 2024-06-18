package com.mawen.learn.nio.book.chapter10;

import java.io.UnsupportedEncodingException;

/**
 * Using Charsets with String
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 10-2
 * @since 2024/6/18
 */
public class CharsetStringDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] encodedMsg = {0x66, 0x61, (byte) 0xc3, (byte) 0xa7, 0x61, 0x64, 0x65, 0x20, 0x74,
				0x6f, 0x75, 0x63, 0x68, (byte) 0xc3, (byte) 0xa9};

		String s = new String(encodedMsg, "UTF-8");
		System.out.println(s);
		System.out.println();

		byte[] bytes = s.getBytes();
		for (byte _byte : bytes) {
			System.out.print(Integer.toHexString(_byte & 0xff) + " ");
		}
		System.out.println();
	}
}
