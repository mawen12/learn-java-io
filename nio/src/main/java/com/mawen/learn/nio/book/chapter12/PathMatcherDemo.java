package com.mawen.learn.nio.book.chapter12;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

/**
 * Demonstrating Path-Matching
 *
 * <pre>{@code
 * 	java PathMatcherDemo glob:*.txt usnumbers.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-50
 * @since 2024/7/8
 */
public class PathMatcherDemo {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java PathMatcherDemo syntax:pattern path");
			return;
		}

		FileSystem fsDefault = FileSystems.getDefault();
		PathMatcher pm = fsDefault.getPathMatcher(args[0]);
		if (pm.matches(fsDefault.getPath(args[1]))) {
			System.out.printf("%s matches pattern%n", args[1]);
		}
		else {
			System.out.printf("%s doesn't match pattern%n", args[1]);
		}
	}
}
