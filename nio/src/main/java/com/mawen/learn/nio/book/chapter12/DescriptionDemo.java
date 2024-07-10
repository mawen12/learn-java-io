package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * Associating a Description with a File
 *
 * <ul>
 *     <li>w: Write the file description with the value sample</li>
 *     <li>l: List all user-defined attributes</li>
 *     <li>r: Read the value of the file.description attribute</li>
 *     <li>d: Delete the file.description attribute</li>
 * </ul>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-19
 * @since 2024/6/26
 */
public class DescriptionDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("usage: java DescriptionDemo path w | l | r | d");
			return;
		}

		Path path = Paths.get(args[0]);
		UserDefinedFileAttributeView udfav = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
		switch (args[1].charAt(0)) {
			case 'W':
			case 'w':
				udfav.write("file.description", Charset.defaultCharset().encode("sample"));
				break;
			case 'l':
			case 'I':
				for (String name : udfav.list()) {
					System.out.printf(name);
				}
				break;
			case 'R':
			case 'r':
				int size = udfav.size("file.description");
				ByteBuffer buf = ByteBuffer.allocateDirect(size);
				udfav.read("file.description", buf);
				buf.flip();
				System.out.println(Charset.defaultCharset().decode(buf));
				break;
			case 'D':
			case 'd':
				udfav.delete("file.description");
		}
	}
}
