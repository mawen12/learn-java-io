package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.*;

/**
 * {@link Files#copy(Path, Path, CopyOption...)} copies a file or directory.
 * If directory is copies, files inside the directory are not copies.
 * If symbolic link is copies, the target of a link is copies.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/copy.html">copy</a>
 * @since 2024/5/11
 */
public class FileCopy {

	public static void main(String[] args) {

		Path src = Paths.get("usnumbers.txt");
		Path dst = Paths.get("usnumbers1.txt");

		try {
			Files.copy(src, dst, REPLACE_EXISTING);
			Files.deleteIfExists(dst);
		}
		catch (IOException e) {
			System.err.println(e);
		}

	}
}
