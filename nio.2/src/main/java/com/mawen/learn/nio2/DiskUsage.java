package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * Print out disk space information by {@link FileStore}.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/examples/DiskUsage.java">DiskUsage</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class DiskUsage {

	static final long K = 1024;
	static final long M = K * K;
	static final long G = M * K;

	static void printFileStore(FileStore store) throws IOException {

		long total = store.getTotalSpace() / K;
		long used = (store.getTotalSpace() - store.getUnallocatedSpace()) / K;
		long avail = store.getUsableSpace() / K;

		String s = store.toString();
		System.out.format("%-40s %12d %12d %12d\n", s, total, used, avail);
	}

	public static void main(String[] args) throws IOException {
		System.out.format("%-40s %12s %12s %12s\n", "FileSystem", "kbytes", "used", "avail");

		FileSystem fs = FileSystems.getDefault();
		for (FileStore store : fs.getFileStores()) {
			printFileStore(store);
		}
	}
}