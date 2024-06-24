package com.mawen.learn.nio.book.chapter12;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;

/**
 * Determining Specific File Attribute View Support
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-10
 * @since 2024/6/24
 */
public class FileAttributeViewSupportDemo {

	public static void main(String[] args) {
		System.out.printf("Supports basic: %b%n", isSupported(BasicFileAttributeView.class));
		System.out.printf("Supports posix: %b%n", isSupported(PosixFileAttributeView.class));
		System.out.printf("Supports acl: %b%n", isSupported(AclFileAttributeView.class));
	}

	static boolean isSupported(Class<? extends FileAttributeView> clazz) {
		return Files.getFileAttributeView(Paths.get("."), clazz) != null;
	}

}
