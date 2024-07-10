package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Moving a Source Path to a Target Path
 *
 * <pre>{@code
 * 	java MoveDemo usnumbers.txt usnumbers.bak
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-38
 * @since 2024/7/8
 */
public class MoveDemo {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java MoveDemo source target");
			return;
		}

		Path source = Paths.get(args[0]);
		Path target = Paths.get(args[1]);

		try {
			Files.move(source, target);
		}
		catch (FileAlreadyExistsException e) {
			System.err.printf("%s: file already exists%n", target);
		}
		catch (DirectoryNotEmptyException e) {
			System.err.printf("%s: not empty%n", target);
		}
		catch (IOException e) {
			System.err.printf("I/O error: %s%n", e.getMessage());
		}
	}
}
