package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Reading a Symbolic Link
 *
 * <pre>{@code
 * 	java ReadSymbolicLinkDemo link.dat
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-42
 * @since 2024/7/8
 */
public class ReadSymbolicLinkDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java ReadSymbolicLinkDemo linkpath");
			return;
		}

		if (!Files.isSymbolicLink(Paths.get(args[0]))) {
			System.out.println("is not symbolic link");
		}
		else {
			Path targetPath = Files.readSymbolicLink(Paths.get(args[0]));
			System.out.println(targetPath);
		}
	}
}
