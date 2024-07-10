package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.util.List;

/**
 * Reading and Outputting a File's Owner and ACL Information
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-18
 * @since 2024/6/26
 */
public class ACLReadDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java ACLReadDemo path");
			return;
		}

		Path path = Paths.get(args[0]);
		System.out.printf("Owner: %s%n", Files.getAttribute(path, "acl:owner"));
		List<AclEntry> aclentries = (List<AclEntry>) Files.getAttribute(path, "acl:acl");
		for (AclEntry aclentry : aclentries) {
			System.out.printf("%s%n%n", aclentry);
		}
	}
}
