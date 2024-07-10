package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/**
 * Getting and Setting Single POSIX File Attributes Values
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-16
 * @since 2024/6/26
 */
public class POSIXFileAttributeOperationDemo {

	public static void main(String[] args) throws IOException {
		if (args.length < 1 || args.length > 2) {
			System.err.println("usage: java POSIXFileAttributeOperationDemo path [group]");
			return;
		}

		Path path = Paths.get(args[0]);
		boolean setAttr = false;
		if (args.length == 2) {
			setAttr = true;
		}
		System.out.printf("Group: %b%n", Files.getAttribute(path, "posix:group"));

		Set<PosixFilePermission> perms = (Set<PosixFilePermission>) Files.getAttribute(path, "posix:permissions");
		for (PosixFilePermission perm : perms) {
			System.out.printf("Permission: %s%n", perm);
		}
		if (setAttr) {
			// obtain a new GroupPrincipal object that corresponds to the specified group name command-line argument
			GroupPrincipal gp = path.getFileSystem()
					.getUserPrincipalLookupService()
					.lookupPrincipalByGroupName(args[1]);
			// change the group attribute
			Files.setAttribute(path, "posix:group", gp);
			System.out.printf("Group: %s%n", Files.getAttribute(path, "posix:group"));
		}
	}
}
