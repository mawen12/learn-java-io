package com.mawen.learn.nio.book.chapter12;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Demonstrating Root Directory Iteration and Absolute Path Creation
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-3
 * @since 2024/6/21
 */
public class AbsolutePathDemo {

	public static void main(String[] args) {
		FileSystem fsDefault = FileSystems.getDefault();
		Path path = fsDefault.getPath("a", "b", "c");
		System.out.println(path);
		System.out.printf("Absolute: %s%n", path.isAbsolute());
		System.out.printf("Root: %s%n", path.getRoot());

		for (Path root : fsDefault.getRootDirectories()) {
			path = fsDefault.getPath(root.toString(), "a", "b", "c");
			System.out.println(path);
			System.out.printf("Absolute: %s%n", path.isAbsolute());
			System.out.printf("Root: %s%n", path.getRoot());
		}
	}
}
