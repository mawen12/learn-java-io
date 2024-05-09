package com.mawen.learn.basic.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Use Character Streams read from {@code xanadu.txt} and write to {@code characteroutput.txt}.
 * Use {@link FileReader} replace {@link java.io.FileInputStream}, {@link FileWriter} replace {@link java.io.FileOutputStream}
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html">charstreams</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/9
 */
public class CopyCharacters {

	public static void main(String[] args) throws IOException {

		FileReader inputStream = null;
		FileWriter outputStream = null;

		try {
			inputStream = new FileReader("xanadu.txt");
			outputStream = new FileWriter("characteroutput.txt");
			int c;  // use 16 bits because byte streams read a character once time

			while ((c = inputStream.read()) != -1) {
				outputStream.write(c);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}

	}
}
