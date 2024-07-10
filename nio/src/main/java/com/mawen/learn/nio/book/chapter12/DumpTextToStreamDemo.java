package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Dumping a Text File to the Standard Output Stream
 *
 * <pre>{@code
 * 	java DumpTextDemo usnumbers.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-24
 * @since 2024/7/4
 */
public class DumpTextToStreamDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java DumpTextDemo textFilepath");
			return;
		}

		List<String> lines = Files.readAllLines(Paths.get(args[0]));
		for (String line : lines) {
			System.out.println(line);
		}
	}
}
