package com.mawen.learn.basic.io.book;

import java.io.File;
import java.io.FilenameFilter;

/**
 * {@link File#list(FilenameFilter)} only return current path, not  path.
 * <pre>{@code
 * 	java Dir /Users/mawen/Documents/learn/Java/learn-java-io .txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/20
 */
public class Dir {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.print("usage: java Dir dirpath ext");
			return;
		}

		File file = new File(args[0]);
		FilenameFilter fnf = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(args[1]);
			}
		};
		String[] names = file.list(fnf);
		for (String name : names) {
			System.out.println(name);
		}
	}
}
