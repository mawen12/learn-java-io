package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Obtaining and Outputting All Directory Entries That Match an Extension
 *
 * <pre>{@code
 * 	java DirectoryStreamFilterDemo . *io
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-34
 * @since 2024/7/5
 */
public class DirectoryStreamFilterGlobDemo {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java DirectoryStreamFilterDemo dirpath ext");
			return;
		}

		Path path = Paths.get(args[0]);

		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, args[1])) {
			for (Path p : ds) {
				System.out.println(p);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
