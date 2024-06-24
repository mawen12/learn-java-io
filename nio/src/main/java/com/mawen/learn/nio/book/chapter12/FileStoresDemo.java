package com.mawen.learn.nio.book.chapter12;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * Iterating Over the Default File System's File Stores
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-8
 * @since 2024/6/21
 */
public class FileStoresDemo {

	public static void main(String[] args) {
		FileSystem fsDefault = FileSystems.getDefault();
		for (FileStore fileStore : fsDefault.getFileStores()) {
			System.out.printf("FileStore: %s%n", fileStore);
		}
	}
}
