package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

/**
 * Reading DOS File Attributes in Bulk
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-13
 * @since 2024/6/26
 */
public class DOSFileAttributeReadDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java DOSFileAttributeDemo path");
			return;
		}

		Path path = Paths.get(args[0]);
		DosFileAttributes dfa = Files.readAttributes(path, DosFileAttributes.class);
		System.out.printf("Is archive: %b%n", dfa.isArchive());
		System.out.printf("Is hidden: %b%n", dfa.isHidden());
		System.out.printf("Is readonly: %b%n", dfa.isReadOnly());
		System.out.printf("Is system: %b%n", dfa.isSystem());
	}
}
