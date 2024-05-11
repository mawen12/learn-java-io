package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Delete files, directories or links.
 * With symbolic links, the link is deleted and not the target of the link.
 * {@link Files#delete(Path)} deletes the file or throws an exception if the deletion fails.
 * {@link Files#deleteIfExists(Path)} deletes the file, if the file does not exist, no exception is thrown.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/delete.html">delete</a>
 * @since 2024/5/11
 */
public class FileDelete {

	public static void main(String[] args) {

		Path file = Paths.get("abc.txt");

		try {
			Files.delete(file);
		}
		catch (NoSuchFileException e) {
			System.err.format("%s: no such file on directory%n", file);
		}
		catch (DirectoryNotEmptyException e) {
			System.err.format("%s not empty%n", file);
		}
		catch (IOException e) {
			// File permission problems are caught here.
			System.err.println(e);
		}

		try {
			Files.deleteIfExists(file);
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
}
