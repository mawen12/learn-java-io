package com.mawen.learn.nio.book.chapter12;

import java.nio.file.spi.FileSystemProvider;
import java.util.List;

/**
 * Identifying Installed File System Providers
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-1
 * @since 2024/6/21
 */
public class ListProviders {

	public static void main(String[] args) {
		List<FileSystemProvider> providers = FileSystemProvider.installedProviders();
		for (FileSystemProvider provider : providers) {
			System.out.println(provider);
		}
	}
}
