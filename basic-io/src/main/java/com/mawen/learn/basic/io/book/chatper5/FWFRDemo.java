package com.mawen.learn.basic.io.book.chatper5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Demonstrating the {@link FileWriter} and {@link FileReader} Classes.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 5-1
 * @since 2024/5/30
 */
public class FWFRDemo {

	final static String MSG = "Test message";

	public static void main(String[] args) throws IOException {
		try (FileWriter fw = new FileWriter("temp")) {
			fw.write(MSG, 0, MSG.length());
		}

		char[] buf = new char[MSG.length()];
		try (FileReader fr = new FileReader("temp")) {
			fr.read(buf, 0, buf.length);
			System.out.println(buf);
		}

		Files.deleteIfExists(Paths.get("temp"));
	}
}
