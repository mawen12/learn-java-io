package com.mawen.learn.basic.io.book;

import java.io.File;
import java.io.IOException;

/**
 * Experimenting with Temporary Files
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 2-6
 * @since 2024/5/28
 */
public class TempFileDemo {

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("java.io.tmpdir"));
		File temp = File.createTempFile("text", ".txt");
		System.out.println(temp);
		temp.deleteOnExit();
	}
}
