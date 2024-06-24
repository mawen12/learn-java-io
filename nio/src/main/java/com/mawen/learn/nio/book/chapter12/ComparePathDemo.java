package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Demonstrating Additional Path Methods
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-6
 * @since 2024/6/21
 */
public class ComparePathDemo {

	public static void main(String[] args) throws IOException {
		Path path1 = Paths.get("a", "b", "c");
		Path path2 = Paths.get("a", "b", "c", "d");
		System.out.printf("path1: %s%n", path1);
		System.out.printf("path2: %s%n", path2);
		System.out.printf("path1.equals(path2): %b%n", path1.equals(path2));
		System.out.printf("path1.equals(path2.subpath(0, 3)): %s%n", path1.equals(path2.subpath(0, 3)));
		System.out.printf("path1.compareTo(path2): %d%n", path1.compareTo(path2));
		System.out.printf("path1.startsWith(\"x\"): %b%n", path1.startsWith("x"));
		System.out.printf("path1.startsWith(Paths.get(\"a\")): %b%n", path1.startsWith(Paths.get("a")));
		System.out.printf("path2.endsWith(\"d\"): %b%n", path2.endsWith("d"));
		System.out.printf("path2.endsWith(Paths.get(\"c\", \"d\")): %b%n", path2.endsWith(Paths.get("c", "d")));
		System.out.printf("path2.toUri(): %s%n", path2.toUri());

		Path path3 = Paths.get(".");
		System.out.printf("path3: %s%n", path3);
		System.out.printf("path3.toRealPath(): %s%n", path3.toRealPath());
	}
}
