package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Getting and Setting Single DOS File Attribute Values
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-14
 * @since 2024/6/26
 */
public class DOSFileAttributeOperationDemo {

	public static void main(String[] args) throws IOException {
		if (args.length < 1 || args.length > 2) {
			System.err.println("usage: java DOSFileAttributeOperationDemo path [set]");
			return;
		}

		Path path = Paths.get(args[0]);
		boolean setAttr = false;
		if (args.length == 2) {
			setAttr = true;
		}

		System.out.printf("Is archive: %b%n", Files.getAttribute(path, "dos:archive"));
		System.out.printf("Is hidden: %b%n", Files.getAttribute(path, "dos:hidden"));
		System.out.printf("Is readonly: %b%n", Files.getAttribute(path, "dos:readonly"));
		System.out.printf("Is system: %b%n", Files.getAttribute(path, "dos:system"));

		if (setAttr) {
			Files.setAttribute(path, "dos:system", true);
			System.out.printf("Is system: %b%n", Files.getAttribute(path, "dos:system"));
		}
	}
}
