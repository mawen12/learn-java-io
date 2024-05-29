package com.mawen.learn.basic.io.book;

import java.io.File;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/20
 */
public class DumpRoots {

	public static void main(String[] args) {
		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println(root);
		}
	}
}
