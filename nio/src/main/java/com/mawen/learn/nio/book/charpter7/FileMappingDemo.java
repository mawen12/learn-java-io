package com.mawen.learn.nio.book.charpter7;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * File Mapping
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-5
 * @since 2024/6/5
 */
public class FileMappingDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("usage: java FileMappingDemo filespec");
			return;
		}

		try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
			 FileChannel fc = raf.getChannel()) {
			long size = fc.size();
			System.out.println("Size: " + size);

			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, size);
			while (mbb.hasRemaining()) {
				System.out.print((char) mbb.get());
			}
			System.out.println();
			System.out.println();

			for (int i = 0; i < mbb.limit() / 2; i++) {
				byte b1 = mbb.get(i);
				byte b2 = mbb.get(mbb.limit() - i - 1);
				mbb.put(i, b2);
				mbb.put(mbb.limit() - i - 1, b1);
			}
			mbb.flip();
			while (mbb.hasRemaining()) {
				System.out.print((char) mbb.get());
			}
		}
	}

}
