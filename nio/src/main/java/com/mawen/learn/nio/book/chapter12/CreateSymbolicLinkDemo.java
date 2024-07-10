package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creating a Symbolic Link
 *
 * <pre>{@code
 * 	java CreateSymbolicLinkDemo link.dat x.dat
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-40
 * @since 2024/7/8
 */
public class CreateSymbolicLinkDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("usage: java CreateSymbolicLinkDemo linkpath targetpath");
			return;
		}

		Files.createSymbolicLink(Paths.get(args[0]), Paths.get(args[1]));
	}
}
