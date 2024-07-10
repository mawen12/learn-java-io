package com.mawen.learn.nio.book.chapter12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Dumping a Text File to the Standard Output Stream, Revisited
 *
 * <pre>{@code
 * 	java DumpTextToReaderDemo usnumbers.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-25
 * @since 2024/7/4
 */
public class DumpTextToReaderDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java WriteTextDemo textfilepath");
			return;
		}

		BufferedReader br = Files.newBufferedReader(Paths.get(args[0]));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
}
