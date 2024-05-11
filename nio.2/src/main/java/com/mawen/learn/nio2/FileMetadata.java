package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import static java.nio.file.StandardCopyOption.*;

/**
 *
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class FileMetadata {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("usnumbers.txt");

		// basic attrs
		BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

		System.out.println("createTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

		System.out.println("isDirectory: " + attr.isDirectory());
		System.out.println("isOther: " + attr.isOther());
		System.out.println("isRegularFile: " + attr.isRegularFile());
		System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
		System.out.println("size: " + attr.size());

		// set time stamp
		long currentTime = System.currentTimeMillis();
		FileTime ft = FileTime.fromMillis(currentTime);
		Files.setLastModifiedTime(file, ft);

		// DOS file attr
		try {
			DosFileAttributes dosAttr = Files.readAttributes(file, DosFileAttributes.class);

			System.out.println("isReadOnly is: " + dosAttr.isReadOnly());
			System.out.println("isHidden is: " + dosAttr.isHidden());
			System.out.println("isHidden is: " + dosAttr.isArchive());
			System.out.println("isSystem is: " + dosAttr.isSystem());
		}
		catch (UnsupportedOperationException e) {
			System.err.println("DOS file attributes not supported:" + e);
		}

		// POSIX file attr
		PosixFileAttributes posixAttr = Files.readAttributes(file, PosixFileAttributes.class);

		System.out.format("%s %s %s%n",
				posixAttr.owner().getName(),
				posixAttr.group().getName(),
				PosixFilePermissions.toString(posixAttr.permissions()));

		// set file group owner
		GroupPrincipal group = file.getFileSystem().getUserPrincipalLookupService()
				.lookupPrincipalByGroupName("staff");
		Path newFile = Files.createFile(Paths.get("usnumbers1.txt"));
		Files.getFileAttributeView(newFile, PosixFileAttributeView.class)
						.setGroup(group);
		Files.deleteIfExists(newFile);

		// set User-Defined file attr
		// https://bugs.openjdk.org/browse/JDK-8040830 macos is null
//		UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
//		view.write("user.mimetype", Charset.defaultCharset().encode("text/html"));
//
//		view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
//		String name = "user.mimetype";
//		ByteBuffer buf = ByteBuffer.allocate(view.size(name));
//		view.read(name, buf);
//		buf.flip();
//		System.out.println(Charset.defaultCharset().decode(buf));
	}
}
