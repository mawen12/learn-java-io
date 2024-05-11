package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link java.nio.file.Files#move(Path, Path, CopyOption...)} moves a file or directory.
 * If a directory is not empty, the move is allowed when the directory can be moved without moving the contents of that directory.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/move.html">move</a>
 * @since 2024/5/11
 */
public class FileMove {

	public static void main(String[] args) {

		Path source = Paths.get("usnumbers.txt");
		Path target = Paths.get("usnumbers1.txt");

		try {
			Files.move(source, target);
			Files.move(target, source);
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
}
