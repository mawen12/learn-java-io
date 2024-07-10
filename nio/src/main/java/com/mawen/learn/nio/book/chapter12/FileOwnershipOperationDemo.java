package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;

/**
 * Getting and Setting File Ownership
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-17
 * @since 2024/6/26
 */
public class FileOwnershipOperationDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java FileOwnershipOperationDemo path");
			return;
		}

		Path path = Paths.get(args[0]);
		System.out.printf("Owner: %s%n", Files.getOwner(path));
		UserPrincipal up = path.getFileSystem()
				.getUserPrincipalLookupService()
				.lookupPrincipalByName("mawen");
		System.out.println(up);
		Files.setOwner(path, up);
		System.out.printf("Owner: %s%n", Files.getOwner(path));
	}
}
