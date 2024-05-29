package com.mawen.learn.basic.io.book;

import java.io.File;
import java.io.IOException;

/**
 * Comparing Files
 *
 * <pre>{@code
 * 	java CompareFile xanadu.txt ./xanadu.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 2-8
 * @since 2024/5/28
 */
public class CompareFile {

	public static void main(String[] args) throws IOException {

		if (args.length != 2) {
			System.err.println("usage: java CompareFile filespec1 filespec2");
			return;
		}

		File file1 = new File(args[0]);
		File file2 = new File(args[1]);
		System.out.println(file1.compareTo(file2));
		System.out.println(file1.getCanonicalFile().compareTo(file2.getCanonicalFile()));
	}
}
