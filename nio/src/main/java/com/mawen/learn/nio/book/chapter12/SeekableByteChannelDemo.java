package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;

import static java.nio.file.StandardOpenOption.*;

/**
 * Demonstrating {@link java.nio.channels.SeekableByteChannel}
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-28
 * @since 2024/7/5
 */
public class SeekableByteChannelDemo {

	final static int RECLEN = 50;

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("emp.txt");
		FileChannel fc = FileChannel.open(path, CREATE, WRITE, SYNC).position(RECLEN * 2);
		ByteBuffer buffer = ByteBuffer.wrap("John Doe".getBytes(StandardCharsets.UTF_8));
		fc.write(buffer);
		fc.close();
		buffer.clear();

		SeekableByteChannel sbc = Files.newByteChannel(path, EnumSet.of(READ)).position(RECLEN * 2);
		sbc.read(buffer);
		sbc.close();
		System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));

		path.toFile().deleteOnExit();
	}
}
