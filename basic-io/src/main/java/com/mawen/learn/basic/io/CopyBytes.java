package com.mawen.learn.basic.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Use Byte Streams read from {@code xanadu.txt} and write to {@code outagain.txt}.
 * This is a kind of low-level I/O that we should avoid.
 * Because {@code xanadu.txt} only contains character data, the best approach is to use {@link java.io.FileReader character streams}.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html">bytestreams</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/9
 */
public class CopyBytes {

	public static void main(String[] args) throws IOException {

		FileInputStream in = null;
		FileOutputStream out = null;

		try {
			in = new FileInputStream("xanadu.txt");
			out = new FileOutputStream("outagain.txt");
			int c; // use 8 bits because byte streams read a byte once time

			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}

	}

}
