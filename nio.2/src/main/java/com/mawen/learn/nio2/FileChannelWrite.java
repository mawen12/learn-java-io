package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

/**
 * Use {@link Files#newByteChannel(Path, OpenOption...)} opens a file for writing, return an {@link SeekableByteChannel}.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileChannelWrite {

	public static void main(String[] args) throws IOException {

		// Create the set of options for appending to the file
		Set<OpenOption> options = new HashSet<>();
		options.add(StandardOpenOption.APPEND);
		options.add(StandardOpenOption.CREATE);

		// Create the custom permissions attribute.
		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

		// Convert the string to a ByteBuffer.
		String s = "Hello World!";
		byte[] data = s.getBytes();
		ByteBuffer bb = ByteBuffer.wrap(data);

		Path file = Paths.get("xanadu1.txt");

		try (SeekableByteChannel sbc = Files.newByteChannel(file, options, attr)) {
			sbc.write(bb);
		}
		catch (IOException e) {
			System.out.println("Exception thrown: " + e);
		}

		Files.deleteIfExists(file);
	}
}
