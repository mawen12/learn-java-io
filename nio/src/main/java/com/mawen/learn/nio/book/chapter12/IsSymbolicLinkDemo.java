package com.mawen.learn.nio.book.chapter12;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Determining if a Path is a Symbolic Link
 *
 * <pre>{@code
 * 	java IsSymbolicLinkDemo link.dat
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-41
 * @since 2024/7/8
 */
public class IsSymbolicLinkDemo {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("usage: java IsSymbolicLinkDemo path");
			return;
		}

		if (Files.isSymbolicLink(Paths.get(args[0]))) {
			System.out.println("is symbolic link");
		}
		else {
			System.out.println("is not symbolic link");
		}
	}
}
