package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link Files#exists(Path, LinkOption...)} checks a file or directory existence.
 * {@link Files#isRegularFile(Path, LinkOption...)} checks a file or directory is a regular file.
 * {@link Files#isReadable(Path)} checks a file or directory is readable.
 * {@link Files#isExecutable(Path)} checks a file is executable.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/check.html">check</a>
 * @since 2024/5/11
 */
public class FileCheck {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("usnumbers.txt");

		System.out.format("isExists: %b%n", Files.exists(file));
		System.out.format("notExists: %b%n", Files.notExists(file));
		System.out.format("isRegularFile: %b%n", Files.isRegularFile(file));
		System.out.format("isReadable: %b%n", Files.isReadable(file));
		System.out.format("isExecutable: %b%n", Files.isExecutable(file));

		Path file2 = Paths.get("usnumbers.txt");
		System.out.format("isSameFile: %b%n", Files.isSameFile(file, file2));
	}
}
