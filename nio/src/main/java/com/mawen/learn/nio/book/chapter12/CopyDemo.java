package com.mawen.learn.nio.book.chapter12;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Copying from a Source Path to a File Output Stream
 *
 * <pre>{@code
 * 	java CopyDemo poem.txt poem.txt.bak
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-36
 * @since 2024/7/8
 */
public class CopyDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("usage: java CopyDemo src dst");
			return;
		}

		Files.copy(Paths.get(args[0]), new FileOutputStream(args[1]));
	}
}
