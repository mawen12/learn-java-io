package com.mawen.learn.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/10
 */
public class PathsExample {

	public static void main(String[] args) {

		// Absolute Path Solaris syntax
		Path path = Paths.get("/home/joe/foo");

		System.out.format("toString: %s%n", path.toString());
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(0));
		System.out.format("getNameCount(): %d%n", path.getNameCount());
		System.out.format("subpath(0, 2): %s%n", path.subpath(0, 2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());

		// Relative Path Solaris syntax
		Path path1 = Paths.get("sally/bar");

		System.out.format("toString: %s%n", path1.toString());
		System.out.format("getFileName: %s%n", path1.getFileName());
		System.out.format("getName(0): %s%n", path1.getName(0));
		System.out.format("getNameCount(): %d%n", path1.getNameCount());
		System.out.format("subpath(0, 2): %s%n", path1.subpath(0, 2));
		System.out.format("getParent: %s%n", path1.getParent());
		System.out.format("getRoot: %s%n", path1.getRoot());

	}
}
