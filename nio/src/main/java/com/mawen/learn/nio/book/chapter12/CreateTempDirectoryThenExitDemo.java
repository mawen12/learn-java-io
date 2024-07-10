package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Using a Shutdown Hook to Remove a Temporary Directory on JVM Exit
 *
 * <pre>{@code
 * 	java CreateTempDirectoryThenExitDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-31
 * @since 2024/7/5
 */
public class CreateTempDirectoryThenExitDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java CreateTempDirectoryDemo path");
			return;
		}

		Path path = Files.createTempDirectory(Paths.get(args[0]), "images");
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				Files.delete(path);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}));
	}
}
