package com.mawen.learn.nio.book.chapter7;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * File Locking
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-4
 * @since 2024/6/4
 */
public class FileLockDemo {

	final static int MAX_QUERIES = 150000;
	final static int MAX_UPDATES = 150000;

	final static int REC_LEN = 16;

	static ByteBuffer buffer = ByteBuffer.allocate(REC_LEN);
	static IntBuffer intBuffer = buffer.asIntBuffer();

	static int counter = 1;


	public static void main(String[] args) throws IOException {
		boolean writer = false;
		if (args.length != 0) {
			writer = true;
		}

		try (RandomAccessFile raf = new RandomAccessFile("temp", (writer) ? "rw" : "r")) {
			FileChannel fc = raf.getChannel();
			if (writer) {
				update(fc);
			}
			else {
				query(fc);
			}
		}
	}

	static void query(FileChannel fc) throws IOException {
		for (int i = 0; i < MAX_QUERIES; i++) {
			System.out.println("acquiring shared lock");
			try (FileLock lock = fc.lock(0, REC_LEN, true)){
				buffer.clear();
				fc.read(buffer, 0);
				int a = intBuffer.get(0);
				int b = intBuffer.get(1);
				int c = intBuffer.get(2);
				int d = intBuffer.get(3);
				System.out.println("Reading: " + a + " " + b + " " + c + " " + d);
				if (a * 2 != b || a * 3 != c || a * 4 != d) {
					System.out.println("error");
					return;
				}
			}
		}
	}

	static void update(FileChannel fc) throws IOException {
		for (int i = 0; i < MAX_UPDATES; i++) {
			System.out.println("acquiring exclusive lock");
			try (FileLock lock = fc.lock(0, REC_LEN, false)) {
				intBuffer.clear();
				int a = counter;
				int b = counter * 2;
				int c = counter * 3;
				int d = counter * 4;
				System.out.println("Writing: " + a + " " + b + " " + c + " " + d);

				intBuffer.put(a);
				intBuffer.put(b);
				intBuffer.put(c);
				intBuffer.put(d);
				counter++;
				buffer.clear();
				fc.write(buffer, 0);
			}
		}
	}
}
