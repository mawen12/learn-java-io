package com.mawen.learn.basic.io.book;

import java.io.File;
import java.util.Date;

/**
 * <pre>{@code
 * 	java FileDirectoryInfo xanadu.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/20
 */
public class FileDirectoryInfo {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("usage: java FileDirectoryInfo pathname");
			return;
		}

		File file = new File(args[0]);
		System.out.println("About " + file + ":");
		System.out.println("Exists = " + file.exists());
		System.out.println("Is directory = " + file.isDirectory());
		System.out.println("Is file = " + file.isFile());
		System.out.println("Is hidden = " + file.isHidden());
		System.out.println("Last modified = " + new Date(file.lastModified()));
		System.out.println("Length = " + file.length());
	}
}
