package com.mawen.learn.nio.book.chapter12;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Constructing a Path and Accessing its Name Elements.
 * This is relative paths.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-2
 * @since 2024/6/21
 */
public class RelativePathDemo {

	public static void main(String[] args) {
		FileSystem fsDefault = FileSystems.getDefault();
		Path path = fsDefault.getPath("a", "b", "c");
		System.out.println(path);
		System.out.printf("File name: %s%n", path.getFileName());

		for (int i = 0; i < path.getNameCount(); i++) {
			System.out.println(path.getName(i));
		}
		System.out.printf("Parent: %s%n", path.getParent());
		System.out.printf("Root: %s%n", path.getRoot());
		System.out.printf("SubPath [0, 2): %s%n", path.subpath(0, 2));
	}
}
