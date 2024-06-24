package com.mawen.learn.nio.book.chapter12;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Converting a Relative Path to an Absolute Path
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-4
 * @since 2024/6/21
 */
public class PathConvertDemo {

	public static void main(String[] args) {
		Path path = Paths.get("a", "b", "c");
		System.out.printf("Path: %s%n", path);
		System.out.printf("Absolute: %s%n", path.isAbsolute());
		path = path.toAbsolutePath();
		System.out.printf("Path: %s%n", path);
		System.out.printf("Absolute: %s%n", path.isAbsolute());
	}
}
