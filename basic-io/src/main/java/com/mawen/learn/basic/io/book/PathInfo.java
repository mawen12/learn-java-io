package com.mawen.learn.basic.io.book;

import java.io.File;
import java.io.IOException;

/**
 * <pre>{@code
 * 	javac PathInfo .
 *
 * 	java PathInfo /opt/redis/redis-6.2.13/../redis-latest/
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/20
 */
public class PathInfo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java PathInfo path");
			return;
		}

		File file = new File(args[0]);
		System.out.println("Absolute path =" + file.getAbsolutePath());
		System.out.println("Canonical path = " + file.getCanonicalPath());
		System.out.println("Name = " + file.getName());
		System.out.println("Parent = " + file.getParent());
		System.out.println("Path = " + file.getPath());
		System.out.println("Is absolute = " + file.isAbsolute());
	}
}
