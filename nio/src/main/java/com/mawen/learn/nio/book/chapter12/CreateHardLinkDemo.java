package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creating a Hard Link
 *
 * <pre>{@code
 * 	java CreateHardLinkDemo myx x.dat
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-43
 * @since 2024/7/8
 */
public class CreateHardLinkDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("usage: java CreateHardLinkDemo linkpath existfilepath");
			return;
		}

		Files.createLink(Paths.get(args[0]), Paths.get(args[1]));
	}
}
