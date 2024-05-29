package com.mawen.learn.basic.io.book;

import java.io.File;

/**
 * Checking a File's or Directory's Permissions
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 2-7
 * @since 2024/5/28
 */
public class Permissions {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.print("usage: java Permissions filespec");
			return;
		}

		File file = new File(args[0]);
		System.out.println("Checking permissions for " + args[0]);
		System.out.println(" Execute = " + file.canExecute());
		System.out.println(" Read = " + file.canRead());
		System.out.println(" Write = " + file.canWrite());
	}
}
