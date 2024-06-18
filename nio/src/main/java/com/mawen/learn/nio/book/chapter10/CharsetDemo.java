package com.mawen.learn.nio.book.chapter10;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Using Charsets to Encode Characters into Byte Sequences
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 10-1
 * @since 2024/6/18
 */
public class CharsetDemo {

	public static void main(String[] args) {
		String msg = "Hello World";
		String[] csNames = {
				StandardCharsets.US_ASCII.name(),
				StandardCharsets.ISO_8859_1.name(),
				StandardCharsets.UTF_8.name(),
				StandardCharsets.UTF_16BE.name(),
				StandardCharsets.UTF_16LE.name(),
				StandardCharsets.UTF_16.name()
		};

		encode(msg, Charset.defaultCharset());

		for (String csName : csNames) {
			encode(msg, Charset.forName(csName));
		}
	}

	static void encode(String msg, Charset cs) {
		System.out.println("Charset: " + cs.toString());
		System.out.println("Message: " + msg);

		ByteBuffer buffer = cs.encode(msg);
		System.out.println("Encoded: ");

		for (int i = 0, end = buffer.remaining(); i < end; i++) {
			int _byte = buffer.get() & 255;
			char ch = (char) _byte;
			if (Character.isWhitespace(ch) || Character.isISOControl(ch)) {
				ch = '\u0000';
			}
			System.out.printf("%2d: %02x (%c)%n", i, _byte, ch);
		}
		System.out.println();
	}
}
