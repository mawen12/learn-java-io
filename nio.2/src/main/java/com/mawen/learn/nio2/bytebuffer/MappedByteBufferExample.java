package com.mawen.learn.nio2.bytebuffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mawen.learn.nio2.bytebuffer.Helper.*;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/13
 */
public class MappedByteBufferExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		Path file = Paths.get("example.txt");
		Files.write(file, "Hello World".getBytes());

		FileChannel fc = new RandomAccessFile(file.toFile(), "r").getChannel();

		MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, Math.min(fc.size(), 8));
		showStatus("map from channel", buffer); // position to 0, limit to 8, remaining to 8, capacity to 8

		System.out.println(1 << 16); // 2^16 = 65536
		System.out.println(2 >>> 16); // 0
		System.out.println(9 << 16); // 589824

		Files.deleteIfExists(file);
	}
}
