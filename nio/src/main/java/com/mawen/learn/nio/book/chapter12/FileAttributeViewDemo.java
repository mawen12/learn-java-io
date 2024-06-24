package com.mawen.learn.nio.book.chapter12;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * Outputting the Names of Default File System-Supported File Attribute Views
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-9
 * @since 2024/6/24
 */
public class FileAttributeViewDemo {

	public static void main(String[] args) {
		FileSystem fsDefault = FileSystems.getDefault();
		for (String view : fsDefault.supportedFileAttributeViews()) {
			System.out.println(view);
		}
	}
}
